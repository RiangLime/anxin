package cn.lime.anxin.service.db.extend;

import cn.lime.anxin.config.AnXinConfig;
import cn.lime.anxin.config.AnXinParams;
import cn.lime.anxin.mapper.StatisticMapper;
import cn.lime.anxin.model.bean.VisitorDateVo;
import cn.lime.anxin.model.vo.StatisticVo;
import cn.lime.anxin.model.vo.statistic.DateCntVo;
import cn.lime.core.token.AccessTokenHandler;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StatisticService {

    @Resource
    private StatisticMapper statisticMapper;
    @Resource
    private AnXinParams anXinParams;
    @Resource
    private AccessTokenHandler accessTokenHandler;

    public StatisticVo getStatistic() {
        StatisticVo vo = new StatisticVo();
        vo.setVisitorStatistic(getDailyVisitors());
        vo.setNewVisitorStatistic(getDailyNewUser());
        vo.setOrderNumberStatistic(getDailyOrders());
        vo.setOrderPriceStatistic(getDailyOrderPrices());
        return vo;
    }

    /**
     * 获取近N天访客
     */
    private List<DateCntVo> getDailyVisitors() {
        List<DateCntVo> data = new ArrayList<>();
        for (int i = 0; i <= anXinParams.getStatisticDays(); i++) {
            List<VisitorDateVo> single = statisticMapper.getDayTokens(i);
            DateCntVo singleDayVisitorVo = new DateCntVo();
            singleDayVisitorVo.setDate(single.get(0).getDate());
            if (CollectionUtils.isEmpty(single.get(0).getTokens())) {
                singleDayVisitorVo.setCount(0);
            } else {
                Set<Long> userIds = new HashSet<>();
                for (String token : single.get(0).getTokens()) {
                    if (StringUtils.isNotEmpty(token)) {
                        userIds.add(accessTokenHandler.checkJwtAccessToken(token).getUserId());
                    }
                }
                singleDayVisitorVo.setCount(userIds.size());
            }
            data.add(singleDayVisitorVo);
        }
        return data;
    }

    private List<DateCntVo> getDailyOrders() {
        return statisticMapper.getDailyOrders(anXinParams.getStatisticDays());
    }

    private List<DateCntVo> getDailyNewUser(){
        return statisticMapper.getDailyNewUsers(anXinParams.getStatisticDays());
    }

    private List<DateCntVo> getDailyOrderPrices(){
        return statisticMapper.getDailyOrderPrices(anXinParams.getStatisticDays());
    }

}
