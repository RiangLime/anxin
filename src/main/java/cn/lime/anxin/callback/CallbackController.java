package cn.lime.anxin.callback;

import cn.lime.mall.constant.OrderStatus;
import cn.lime.mall.constant.PayCallBackUrl;
import cn.lime.mall.constant.RefundStatus;
import cn.lime.mall.model.entity.Order;
import cn.lime.mall.service.db.OrderService;
import cn.lime.mall.service.wx.payment.BaseWxPayServiceImpl;
import cn.lime.mall.service.wx.payment.JsApiPayServiceImpl;
import com.alibaba.fastjson.JSON;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.Refund;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class CallbackController {

    @Resource(name = "baseWxPayServiceImpl")
    private BaseWxPayServiceImpl wxPayService;
    @Resource
    private JsApiPayServiceImpl jsApiPayService;
    @Resource
    private OrderService orderService;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @RequestMapping(PayCallBackUrl.ORDER_CALLBACK_URL_WX)
    public void payNotice(javax.servlet.http.HttpServletRequest request) throws Exception {
        log.info("Get wx Notice {}", sdf.format(new Date()));
        wxPayService.dealNotice(request, Transaction.class);
    }

    @RequestMapping(PayCallBackUrl.ORDER_REFUND_CALLBACK_URL)
    public void refundNotice(javax.servlet.http.HttpServletRequest request) throws Exception {
        log.info("""
                                
                Get wx Notice {}""", sdf.format(new Date()));
        wxPayService.dealNotice(request, RefundNotification.class);
    }

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void scanPayingOrder() {
        List<Long> payingIds = orderService.lambdaQuery().eq(Order::getOrderStatus, OrderStatus.PAYING.getVal()).list().stream().map(Order::getOrderId).toList();
        if (!CollectionUtils.isEmpty(payingIds)) {
            log.info("[SCAN] payingOrders:{}", JSON.toJSONString(payingIds));
            for (Long payingId : payingIds) {
                Transaction transaction = jsApiPayService.queryOrderById(payingId);
                log.info("[SCAN] transaction:{}", JSON.toJSONString(transaction));
                wxPayService.dealTransaction(transaction);
            }
        }
        List<Long> refundIds = orderService.lambdaQuery().eq(Order::getRefundStatus, RefundStatus.PROCESSING.getVal()).list().stream().map(Order::getRefundId).toList();
        if (!CollectionUtils.isEmpty(refundIds)) {
            log.info("[SCAN] refundingRefundIds:{}", JSON.toJSONString(refundIds));
            for (Long refundId : refundIds) {
                try {
                    Refund refund = jsApiPayService.queryRefundByRefundId(refundId);
                    log.info("[SCAN] refund:{}", JSON.toJSONString(refund));
                    wxPayService.dealRefund(refund);
                }catch (Exception e){
                    log.error("[SCAN] query {} error ,message={}",refundId,e.getMessage());
                }
            }
        }

    }


}
