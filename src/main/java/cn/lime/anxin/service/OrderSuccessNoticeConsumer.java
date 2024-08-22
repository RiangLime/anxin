package cn.lime.anxin.service;

import cn.lime.anxin.constants.DetectOrderState;
import cn.lime.anxin.model.entity.Detectorder;
import cn.lime.anxin.service.db.base.DetectorderService;
import cn.lime.core.constant.RedisDb;
import cn.lime.core.constant.RedisKeyName;
import cn.lime.core.constant.YesNoEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @ClassName: OrderSuccessNoticeConsumer
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/22 15:42
 */
@Service
@Slf4j
public class OrderSuccessNoticeConsumer {

    @Resource
    private Map<Integer, StringRedisTemplate> redisTemplateMap;
    @Resource
    private DetectorderService detectorderService;

    @Scheduled(fixedRate = 1000)
    public void consumer(){
        String orderIdStr = redisTemplateMap.get(RedisDb.BIZ_DB.getVal()).opsForList()
                .leftPop(RedisKeyName.BIZ_NOTICE_SUCCESS_PAID_ORDER.getVal());
        if (StringUtils.isNotEmpty(orderIdStr)) {
            try {
                dealOrderId(orderIdStr);
            }catch (Exception e){
                log.error("[NOTICE]",e);
                redisTemplateMap.get(RedisDb.BIZ_DB.getVal()).opsForList()
                        .rightPush(RedisKeyName.BIZ_NOTICE_SUCCESS_PAID_ORDER.getVal(),orderIdStr);
            }
        }
    }

    private void dealOrderId(String orderIdStr) throws Exception{
        Long orderId = Long.parseLong(orderIdStr);
        Optional<Detectorder> detectorderOpt = detectorderService
                .lambdaQuery().eq(Detectorder::getOrderId,orderId).oneOpt();
        if (detectorderOpt.isPresent()){
            Detectorder detectorder = detectorderOpt.get();
            // 是升级报告的订单且处于付款未完成状态
            if (detectorder.getIsUpdated().equals(YesNoEnum.YES.getVal())
                    && detectorder.getDetectState().equals(DetectOrderState.UPDATE_WAIT_PAID.getVal())){
                boolean res = detectorderService.lambdaUpdate()
                        .eq(Detectorder::getId,detectorder.getId())
                        .set(Detectorder::getDetectState,DetectOrderState.TO_BE_DETECT.getVal())
                        .update();
                if (!res){
                    throw new Exception("消费者任务更新升级报告订单状态异常,id:"+orderIdStr);
                }
            }
        }
    }
}
