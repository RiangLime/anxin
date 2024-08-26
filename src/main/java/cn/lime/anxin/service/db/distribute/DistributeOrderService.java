package cn.lime.anxin.service.db.distribute;

import cn.lime.anxin.model.entity.DistributeOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author riang
* @description 针对表【Distribute_Order】的数据库操作Service
* @createDate 2024-08-26 10:55:55
*/
public interface DistributeOrderService extends IService<DistributeOrder> {
    /**
     * 订单进入分销系统
     * @param orderId 订单ID
     */
    void orderCheckInDistributeSystem(Long orderId,Integer relatePrice);

}
