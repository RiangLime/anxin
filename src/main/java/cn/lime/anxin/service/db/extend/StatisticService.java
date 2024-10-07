package cn.lime.anxin.service.db.extend;

import cn.lime.anxin.config.AnXinParams;
import cn.lime.anxin.mapper.StatisticMapper;
import cn.lime.anxin.model.vo.StatisticVo;
import cn.lime.anxin.model.vo.statistic.DateCntVo;
import cn.lime.core.token.AccessTokenHandler;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return statisticMapper.getDayVisitors(anXinParams.getStatisticDays());
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
