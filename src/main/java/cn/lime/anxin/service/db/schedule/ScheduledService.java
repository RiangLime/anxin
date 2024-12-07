package cn.lime.anxin.service.db.schedule;

import cn.lime.anxin.service.db.base.DetectorderService;
import cn.lime.mall.service.db.OrderService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ScheduledService {

    @Resource
    private DetectorderService detectorderService;
    @Resource
    private OrderService orderService;

    @Transactional
    @Scheduled(fixedRate = 2000)
    public void updateUpdatedOrderStatus(){
        List<Long> ids = detectorderService.getUpdateWaitingSendOrderIds();
        if (!CollectionUtils.isEmpty(ids)){
            for (Long id : ids) {
                orderService.updateOrderStatusFromPayedToFinish(id);
            }
        }
    }

}
