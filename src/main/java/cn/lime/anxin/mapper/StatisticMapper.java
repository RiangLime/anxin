package cn.lime.anxin.mapper;

import cn.lime.anxin.model.bean.VisitorDateVo;
import cn.lime.anxin.model.vo.statistic.DateCntVo;

import java.util.List;

public interface StatisticMapper {

    List<VisitorDateVo> getDayTokens(Integer days);
    List<DateCntVo> getDailyOrders(Integer days);
    List<DateCntVo> getDailyOrderPrices(Integer days);
    List<DateCntVo> getDailyNewUsers(Integer days);

}
