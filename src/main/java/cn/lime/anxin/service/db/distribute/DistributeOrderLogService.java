package cn.lime.anxin.service.db.distribute;

import cn.lime.anxin.model.entity.DistributeOrderLog;
import cn.lime.mall.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author riang
 * @description 针对表【Distribute_Order_Log】的数据库操作Service
 * @createDate 2024-08-23 15:20:04
 */
public interface DistributeOrderLogService extends IService<DistributeOrderLog> {
    void appendLog(Long relateUserId, Long orderId,Integer orderPrice, Integer percent);
    void withdraw(Long userId,Integer price);
}
