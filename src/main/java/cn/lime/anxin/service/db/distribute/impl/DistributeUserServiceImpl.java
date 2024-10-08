package cn.lime.anxin.service.db.distribute.impl;

import cn.lime.anxin.model.entity.DistributeInviteRelation;
import cn.lime.anxin.service.db.distribute.DistributeInviteRelationService;
import cn.lime.anxin.service.db.distribute.DistributeOrderLogService;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeUser;
import cn.lime.anxin.service.db.distribute.DistributeUserService;
import cn.lime.anxin.mapper.DistributeUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author riang
 * @description 针对表【Distribute_User】的数据库操作Service实现
 * @createDate 2024-08-23 14:26:06
 */
@Service
public class DistributeUserServiceImpl extends ServiceImpl<DistributeUserMapper, DistributeUser>
        implements DistributeUserService {
    @Resource
    private DistributeInviteRelationService relationService;
    @Resource
    private DistributeOrderLogService logService;

    @Override
    @Transactional
    public void addDistributor(Long userId) {
        Optional<DistributeInviteRelation> relation = relationService.lambdaQuery()
                .eq(DistributeInviteRelation::getUserId, userId).oneOpt();
        DistributeUser or = new DistributeUser();
        or.setUserId(userId);
        // 有上级 -> Level延续
        if (relation.isPresent()) {
            DistributeUser inviter = getById(relation.get().getInviterId());
            or.setLevelId(inviter.getLevelId() + 1);
        } else {
            or.setLevelId(1);
        }
        ThrowUtils.throwIf(!save(or), ErrorCode.INSERT_ERROR);
    }

    @Override
    @Transactional
    public void updateDistributorLevel(Long userId, Integer level) {
        ThrowUtils.throwIf(!lambdaUpdate().eq(DistributeUser::getUserId, userId)
                .set(DistributeUser::getLevelId, level).update(), ErrorCode.UPDATE_ERROR);
        List<DistributeInviteRelation> relations = relationService.lambdaQuery()
                .eq(DistributeInviteRelation::getInviterId, userId).list();
        if (!CollectionUtils.isEmpty(relations)) {
            for (DistributeInviteRelation relation : relations) {
                updateDistributorLevel(relation.getUserId(), level + 1);
            }
        }
    }

    @Override
    @Transactional
    public void userWithdraw(Long userId, Integer price) {
        DistributeUser user = lambdaQuery().eq(DistributeUser::getUserId, userId).one();
        ThrowUtils.throwIf(ObjectUtils.isEmpty(user), ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!lambdaUpdate().eq(DistributeUser::getUserId, user.getUserId())
                .set(DistributeUser::getAssetsRemain, user.getAssetsRemain() - price)
                .set(DistributeUser::getAssetsGet, user.getAssetsGet() + price)
                .update(), ErrorCode.UPDATE_ERROR, "更新分销商余额异常");
        logService.withdraw(userId,price);
    }
}




