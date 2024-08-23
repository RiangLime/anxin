package cn.lime.anxin.service.consumer;

import cn.lime.anxin.service.db.base.DetectorderService;
import cn.lime.core.constant.RedisDb;
import cn.lime.core.constant.RedisKeyName;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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


    @Scheduled(fixedRate = 1000)
    public void consumer(){
        String orderIdStr = redisTemplateMap.get(RedisDb.BIZ_DB.getVal()).opsForList()
                .leftPop(RedisKeyName.BIZ_NOTICE_SUCCESS_FINISH_ORDER.getVal());
        if (StringUtils.isNotEmpty(orderIdStr)) {
            try {
                dealOrderId(orderIdStr);
            }catch (Exception e){
                log.error("[NOTICE]",e);
                redisTemplateMap.get(RedisDb.BIZ_DB.getVal()).opsForList()
                        .rightPush(RedisKeyName.BIZ_NOTICE_SUCCESS_FINISH_ORDER.getVal(),orderIdStr);
            }
        }
    }

    private void dealOrderId(String orderIdStr) throws Exception {


    }
}
