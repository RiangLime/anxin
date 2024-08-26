package cn.lime.anxin.service.db.distribute.impl;

import cn.lime.anxin.model.entity.*;
import cn.lime.anxin.service.db.distribute.*;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import cn.lime.mall.model.entity.Order;
import cn.lime.mall.service.db.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.mapper.DistributeOrderMapper;
import jakarta.annotation.Resource;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author riang
 * @description 针对表【Distribute_Order】的数据库操作Service实现
 * @createDate 2024-08-26 10:55:55
 */
@Service
public class DistributeOrderServiceImpl extends ServiceImpl<DistributeOrderMapper, DistributeOrder>
        implements DistributeOrderService {
    @Resource
    private SnowFlakeGenerator ids;
    @Resource
    private OrderService orderService;
    @Resource
    private DistributeUserService distributeUserService;
    @Resource
    private DistributeInviteRelationService relationService;
    @Resource
    private DistributeLevelService levelService;
    @Resource
    private DistributeOrderLogService logService;

    @Override
    @Transactional
    public void orderCheckInDistributeSystem(Long orderId,Integer orderRelatePrice) {
        DistributeOrder bean = new DistributeOrder();
        bean.setOrderId(orderId);
        bean.setId(ids.nextId());
        ThrowUtils.throwIf(!save(bean), ErrorCode.INSERT_ERROR);
        Order order = orderService.getById(orderId);
        Long buyerId = order.getUserId();
        Optional<DistributeUser> buyerDistributorOpt = distributeUserService
                .lambdaQuery().eq(DistributeUser::getUserId, buyerId).oneOpt();
        // 自己是分销商
        if (buyerDistributorOpt.isPresent()) {
            // 自身分销等级
            DistributeLevel level = levelService.getById(buyerDistributorOpt.get().getLevelId());
            int relatePrice = orderRelatePrice * level.getRate1() / 100;
            if (relatePrice != 0) {
                ThrowUtils.throwIf(!distributeUserService.lambdaUpdate().eq(DistributeUser::getUserId, buyerId)
                        .set(DistributeUser::getAssetsRemain, buyerDistributorOpt.get().getAssetsRemain() + relatePrice)
                        .update(), ErrorCode.UPDATE_ERROR, "更新分销商待结算佣金异常");
            }
            // 留痕
            logService.appendLog(buyerId, orderId, relatePrice, level.getLevelId());
        }
        // 上级分销
        val distributeInviteRelation = relationService.lambdaQuery().eq(DistributeInviteRelation::getUserId, buyerId).oneOpt();
        if (distributeInviteRelation.isPresent()){
            Long upperId = distributeInviteRelation.get().getInviterId();
            val upper = distributeUserService.lambdaQuery().eq(DistributeUser::getUserId, upperId).one();
            val upperLevel = levelService.getById(upper.getLevelId());
            int relatePrice = orderRelatePrice * upperLevel.getRate2() / 100;
            if (relatePrice != 0) {
                ThrowUtils.throwIf(!distributeUserService.lambdaUpdate().eq(DistributeUser::getUserId, upperId)
                        .set(DistributeUser::getAssetsRemain, upper.getAssetsRemain() + relatePrice)
                        .update(), ErrorCode.UPDATE_ERROR, "更新分销商待结算佣金异常");
            }
            // 留痕
            logService.appendLog(upperId, orderId, relatePrice, upperLevel.getLevelId());
            // 二级分销结算
            Optional<DistributeInviteRelation> secRelation = relationService.lambdaQuery()
                    .eq(DistributeInviteRelation::getUserId, upperId).oneOpt();
            if (secRelation.isPresent()){
                Long secUserId = secRelation.get().getInviterId();
                DistributeUser secUser = distributeUserService.lambdaQuery().eq(DistributeUser::getUserId,secUserId).one();
                DistributeLevel secLevel = levelService.getById(secUser.getLevelId());
                relatePrice = orderRelatePrice * secLevel.getRate3() / 100;
                if (relatePrice != 0) {
                    ThrowUtils.throwIf(!distributeUserService.lambdaUpdate().eq(DistributeUser::getUserId, secUserId)
                            .set(DistributeUser::getAssetsRemain, secUser.getAssetsRemain() + relatePrice)
                            .update(), ErrorCode.UPDATE_ERROR, "更新分销商待结算佣金异常");
                }
                // 留痕
                logService.appendLog(secUserId, orderId, relatePrice, upperLevel.getLevelId());
            }
        }
    }
}




