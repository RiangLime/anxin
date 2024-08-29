package cn.lime.anxin.service.db.extend;

import cn.lime.anxin.constants.DetectOrderState;
import cn.lime.anxin.constants.DistributeWithdrawState;
import cn.lime.anxin.model.entity.Detectorder;
import cn.lime.anxin.model.entity.DistributeApplication;
import cn.lime.anxin.model.entity.DistributeWithdraw;
import cn.lime.anxin.model.vo.PollingInfoVo;
import cn.lime.anxin.service.db.base.DetectorderService;
import cn.lime.anxin.service.db.distribute.DistributeApplicationService;
import cn.lime.anxin.service.db.distribute.DistributeWithdrawService;
import cn.lime.mall.constant.OrderStatus;
import cn.lime.mall.constant.RefundStatus;
import cn.lime.mall.model.entity.Order;
import cn.lime.mall.service.db.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: PollingService
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 18:05
 */
@Service
public class PollingService {

    // 待发货提示
    @Resource
    private OrderService orderService;
    // 待回寄提示
    @Resource
    private DetectorderService detectorderService;
    // 申请成为代理提示
    @Resource
    private DistributeApplicationService distributeApplicationService;
    // 申请提现提示
    @Resource
    private DistributeWithdrawService distributeWithdrawService;

    public PollingInfoVo pollingInfo() {
        // 待发货订单ID
        List<String> orderIdStrings = orderService.lambdaQuery()
                .eq(Order::getOrderStatus, OrderStatus.WAITING_SEND.getVal())
                .list()
                .stream()
                .map(Order::getOrderId)
                .map(String::valueOf)
                .toList();
        // 待回寄样本ID
        List<String> detectOrderIdStrings = detectorderService.lambdaQuery()
                .eq(Detectorder::getDetectState, DetectOrderState.READY_TO_RETURN.getVal())
                .list()
                .stream()
                .map(Detectorder::getId)
                .map(String::valueOf)
                .toList();
        // 申请成为代理提示
        List<String> distributorApplyIdStrings = distributeApplicationService.lambdaQuery()
                .eq(DistributeApplication::getState, DistributeWithdrawState.APPLY.getVal())
                .list()
                .stream()
                .map(DistributeApplication::getId)
                .map(String::valueOf)
                .toList();
        // 申请提现提示
        List<String> distributorWithdrawApplyIdStrings = distributeWithdrawService.lambdaQuery()
                .eq(DistributeWithdraw::getState, DistributeWithdrawState.APPLY.getVal())
                .list()
                .stream()
                .map(DistributeWithdraw::getId)
                .map(String::valueOf)
                .toList();
        // 待退款提示
        List<String> refundIdStrings = orderService.lambdaQuery()
                .eq(Order::getRefundStatus, RefundStatus.APPLY.getVal())
                .list()
                .stream()
                .map(Order::getOrderId)
                .map(String::valueOf)
                .toList();
        return new PollingInfoVo(orderIdStrings,detectOrderIdStrings,distributorApplyIdStrings,
                distributorWithdrawApplyIdStrings,refundIdStrings);
    }

}
