package cn.lime.anxin.service.db.distribute.impl;

import cn.lime.anxin.constants.DistributeOrderOpType;
import cn.lime.anxin.model.entity.DistributeUser;
import cn.lime.anxin.service.db.distribute.DistributeUserService;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import cn.lime.mall.model.entity.Order;
import cn.lime.mall.service.db.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeOrderLog;
import cn.lime.anxin.service.db.distribute.DistributeOrderLogService;
import cn.lime.anxin.mapper.DistributeOrderLogMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.awt.image.ImageFilter;

/**
 * @author riang
 * @description 针对表【Distribute_Order_Log】的数据库操作Service实现
 * @createDate 2024-08-23 15:20:04
 */
@Service
public class DistributeOrderLogServiceImpl extends ServiceImpl<DistributeOrderLogMapper, DistributeOrderLog>
        implements DistributeOrderLogService {
    @Resource
    private SnowFlakeGenerator ids;

    @Override
    public void appendLog(Long relateUserId, Long orderId,Integer relatePrice, Integer percent) {
        DistributeOrderLog log = new DistributeOrderLog();
        log.setId(ids.nextId());
        log.setAmount(relatePrice);
        log.setOrderId(orderId);
        log.setUserId(relateUserId);
        // 固定是收入
        log.setOpType(DistributeOrderOpType.ORDER_FINISH.getVal());
        ThrowUtils.throwIf(!save(log), ErrorCode.INSERT_ERROR);

    }

    @Override
    public void withdraw(Long userId, Integer price) {
        DistributeOrderLog log = new DistributeOrderLog();
        log.setId(ids.nextId());
        log.setAmount(price);
        log.setUserId(userId);
        // 固定是收入
        log.setOpType(DistributeOrderOpType.WITHDRAW.getVal());
        ThrowUtils.throwIf(!save(log), ErrorCode.INSERT_ERROR);
    }
}




