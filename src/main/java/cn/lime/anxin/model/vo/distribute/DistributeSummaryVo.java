package cn.lime.anxin.model.vo.distribute;

import cn.lime.core.common.PageResult;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DistributeSummaryVo implements Serializable {

    // 个人佣金信息
    private Integer canWithdrawNumber;
    private Integer alreadyWithdrawNumber;

    // 上下级信息
    private DistributeRelatorVo upstreamUserInfo;
    private List<DistributeRelatorVo> downstreamUserInfo;

    // 分销订单信息
    private PageResult<UserDistributeOrderLogVo> distributeOrderPage;

}
