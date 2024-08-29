package cn.lime.anxin.model.vo;

import cn.lime.anxin.constants.DetectOrderState;
import cn.lime.anxin.constants.DistributeWithdrawState;
import cn.lime.anxin.model.entity.Detectorder;
import cn.lime.anxin.model.entity.DistributeApplication;
import cn.lime.anxin.model.entity.DistributeWithdraw;
import cn.lime.mall.constant.OrderStatus;
import cn.lime.mall.model.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PollingInfoVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 18:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollingInfoVo implements Serializable {
    // 待发货订单ID
    @Schema(description = "待发货订单ID")
    private List<String> orderIdStrings;
    // 待回寄样本ID
    @Schema(description = "待回寄样本ID")
    private List<String> detectOrderIdStrings;
    // 申请成为代理提示
    @Schema(description = "申请成为代理提示")
    private List<String> distributorApplyIdStrings;
    // 申请提现提示
    @Schema(description = "申请提现提示")
    private List<String> distributorWithdrawApplyIdStrings;
    @Schema(description = "退款申请提示")
    private List<String> refundOrderIdStrings;
}
