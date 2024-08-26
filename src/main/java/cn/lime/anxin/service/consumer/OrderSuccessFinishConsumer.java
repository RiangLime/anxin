package cn.lime.anxin.service.consumer;

import cn.lime.anxin.model.entity.DistributeProduct;
import cn.lime.anxin.service.db.base.DetectorderService;
import cn.lime.anxin.service.db.distribute.DistributeOrderService;
import cn.lime.anxin.service.db.distribute.DistributeProductService;
import cn.lime.core.constant.RedisDb;
import cn.lime.core.constant.RedisKeyName;
import cn.lime.mall.model.entity.Order;
import cn.lime.mall.model.entity.OrderItem;
import cn.lime.mall.service.db.OrderItemService;
import cn.lime.mall.service.db.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: OrderSuccessFinishConsumer
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/23 15:55
 */
@Service
@Slf4j
public class OrderSuccessFinishConsumer {
    @Resource
    private Map<Integer, StringRedisTemplate> redisTemplateMap;
    @Resource
    private DistributeOrderService distributeOrderService;
    @Resource
    private OrderService orderService;
    @Resource
    private DistributeProductService distributeProductService;
    @Resource
    private OrderItemService orderItemService;

    @Scheduled(fixedRate = 1000)
    public void consumer() {
        String orderIdStr = redisTemplateMap.get(RedisDb.BIZ_DB.getVal()).opsForList()
                .leftPop(RedisKeyName.BIZ_NOTICE_SUCCESS_FINISH_ORDER.getVal());
        if (StringUtils.isNotEmpty(orderIdStr)) {
            try {
                dealOrderId(orderIdStr);
            } catch (Exception e) {
                log.error("[NOTICE]", e);
                redisTemplateMap.get(RedisDb.BIZ_DB.getVal()).opsForList()
                        .rightPush(RedisKeyName.BIZ_NOTICE_SUCCESS_FINISH_ORDER.getVal(), orderIdStr);
            }
        }
    }

    private void dealOrderId(String orderIdStr) throws Exception {
        Long orderId = Long.parseLong(orderIdStr);
        Order order = orderService.getById(orderId);
        Integer distributeAmount = 0;
        Integer allAmount = 0;
        List<OrderItem> orderItems = orderItemService.lambdaQuery().eq(OrderItem::getOrderId, order).list();
        for (OrderItem orderItem : orderItems) {
            Long productId = orderItem.getProductId();
            Long skuId = orderItem.getSkuId();
            if (distributeProductService.isDistributeProduct(productId, skuId)) {
                distributeAmount += orderItem.getItemPrice();
            }
            allAmount += orderItem.getItemPrice();
        }
        // 商品总价等于订单价格 即未使用优惠券
        if (allAmount.equals(order.getRealOrderPrice())) {
            distributeOrderService.orderCheckInDistributeSystem(Long.parseLong(orderIdStr), distributeAmount);
        }
        // 使用了优惠券 按比例算
        else {
            Integer relateAmount = order.getRealOrderPrice() * (distributeAmount / allAmount);
            distributeOrderService.orderCheckInDistributeSystem(Long.parseLong(orderIdStr), relateAmount);
        }

    }
}
