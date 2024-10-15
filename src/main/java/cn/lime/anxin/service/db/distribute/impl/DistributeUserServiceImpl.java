package cn.lime.anxin.service.db.distribute.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.lime.anxin.model.entity.DistributeInviteRelation;
import cn.lime.anxin.model.entity.DistributeOrderLog;
import cn.lime.anxin.model.vo.distribute.DistributeRelatorVo;
import cn.lime.anxin.model.vo.distribute.DistributeSummaryVo;
import cn.lime.anxin.model.vo.distribute.LevelDistributorInfo;
import cn.lime.anxin.model.vo.distribute.UserDistributeOrderLogVo;
import cn.lime.anxin.service.db.distribute.DistributeInviteRelationService;
import cn.lime.anxin.service.db.distribute.DistributeOrderLogService;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.PageResult;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.constant.YesNoEnum;
import cn.lime.core.service.db.UserService;
import cn.lime.mall.model.vo.OrderDetailVo;
import cn.lime.mall.service.db.OrderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeUser;
import cn.lime.anxin.service.db.distribute.DistributeUserService;
import cn.lime.anxin.mapper.DistributeUserMapper;
import jakarta.annotation.Nullable;
import jakarta.annotation.Resource;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

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
    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;

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
        val distributeUser = lambdaQuery().eq(DistributeUser::getUserId, userId).oneOpt();
        if (distributeUser.isEmpty()){
            return;
        }
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
        logService.withdraw(userId, price);
    }

    @Override
    public DistributeSummaryVo getUserInfo(Long userId, Integer current, Integer pageSize) {
        DistributeSummaryVo summaryVo = new DistributeSummaryVo();
        // 用户本身信息
        DistributeUser userInfo = getById(userId);
        if (ObjectUtils.isEmpty(userInfo)) {
            summaryVo.setIsDistributor(YesNoEnum.NO.getVal());
            return summaryVo;
        }
        summaryVo.setIsDistributor(YesNoEnum.YES.getVal());
        summaryVo.setCanWithdrawNumber(userInfo.getAssetsRemain());
        summaryVo.setAlreadyWithdrawNumber(userInfo.getAssetsGet());

        // 上下级信息
        DistributeRelatorVo upstreamInfo = relationService.queryUpstream(userId);
        summaryVo.setUpstreamUserInfo(upstreamInfo);
        List<DistributeRelatorVo> downstreamInfo = relationService.queryDownstream(userId);
        summaryVo.setDownstreamUserInfo(downstreamInfo);

        // 分销订单信息
        Page<DistributeOrderLog> orderLogs = logService.pages(userId, current, pageSize);
        List<UserDistributeOrderLogVo> logs = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orderLogs.getRecords())) {
            for (DistributeOrderLog record : orderLogs.getRecords()) {
                UserDistributeOrderLogVo vo = new UserDistributeOrderLogVo();
                vo.setOpType(record.getOpType());
                vo.setAmount(record.getAmount());
                OrderDetailVo orderDetailVo = orderService.getOrderDetail(record.getOrderId());
                BeanUtil.copyProperties(orderDetailVo, vo);
                logs.add(vo);
            }
        }
        PageResult<UserDistributeOrderLogVo> distributeOrdersPageInfo = new PageResult<>(orderLogs, logs);
        summaryVo.setDistributeOrderPage(distributeOrdersPageInfo);

        return summaryVo;
    }

    @Override
    public List<LevelDistributorInfo> getAllDistributorInfo() {
        List<DistributeUser> rootList = lambdaQuery().eq(DistributeUser::getLevelId, 1).list();
        if (CollectionUtils.isEmpty(rootList)) return Collections.emptyList();
        List<LevelDistributorInfo> vo = new LinkedList<>();
        for (DistributeUser distributeUser : rootList) {
            LevelDistributorInfo info = fullFillDistributorInfo(distributeUser.getUserId());
            vo.add(info);
        }
        return vo;
    }

    public LevelDistributorInfo fullFillDistributorInfo(Long userId) {
        LevelDistributorInfo info = new LevelDistributorInfo(userId);
        info.setUserVo(userService.detail(userId));
        val distributeUser = lambdaQuery().eq(DistributeUser::getUserId, userId).oneOpt();
        info.setIsDistributor(distributeUser.isEmpty() ? YesNoEnum.NO.getVal() : YesNoEnum.YES.getVal());
        // 直接下级
        List<DistributeInviteRelation> relations = relationService.lambdaQuery()
                .eq(DistributeInviteRelation::getInviterId, userId).list();
        if (!CollectionUtils.isEmpty(relations)) {
            List<LevelDistributorInfo> downstreamInfos = new ArrayList<>();
            for (DistributeInviteRelation relation : relations) {
                LevelDistributorInfo downstreamInfo = fullFillDistributorInfo(relation.getUserId());
                downstreamInfos.add(downstreamInfo);
            }
            info.setDownstreamDistributor(downstreamInfos);
        } else {
            info.setDownstreamDistributor(Collections.emptyList());
        }
        return info;
    }

    @Override
    @Transactional
    public void updateDistributorUpstream(Long userId, Long inviterId) {
        val distributeUser = lambdaQuery().eq(DistributeUser::getUserId, inviterId).oneOpt();
        ThrowUtils.throwIf(distributeUser.isEmpty(), ErrorCode.PARAMS_ERROR, "该邀请人不是分销商");
        // 找到修改和上级的关系信息
        val relation = relationService.lambdaQuery().eq(DistributeInviteRelation::getUserId, userId).oneOpt();
        if (relation.isPresent()) {
            ThrowUtils.throwIf(!relationService.lambdaUpdate().eq(DistributeInviteRelation::getId, relation.get().getId())
                            .set(DistributeInviteRelation::getInviterId, inviterId).update()
                    , ErrorCode.UPDATE_ERROR, "修改用户上级信息关联表异常");
        } else {
            relationService.inviteeRegister(userId, inviterId);
        }
        updateDistributorLevel(userId, distributeUser.get().getLevelId() + 1);
    }
}




