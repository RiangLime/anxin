package cn.lime.anxin.model.vo;

import cn.lime.anxin.model.vo.statistic.DateCntVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticVo implements Serializable {
    @Schema(description = "近N天 访客量统计")
    private List<DateCntVo> visitorStatistic;
    @Schema(description = "近N天 订单数统计")
    private List<DateCntVo> orderNumberStatistic;
    @Schema(description = "近N天 订单价格统计")
    private List<DateCntVo> orderPriceStatistic;
    @Schema(description = "近N天 新用户统计")
    private List<DateCntVo> newVisitorStatistic;

}
