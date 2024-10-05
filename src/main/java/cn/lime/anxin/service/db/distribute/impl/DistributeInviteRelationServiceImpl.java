package cn.lime.anxin.service.db.distribute.impl;

import cn.lime.anxin.model.entity.DistributeUser;
import cn.lime.anxin.model.vo.distribute.DistributeRelatorVo;
import cn.lime.anxin.service.db.distribute.DistributeUserService;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.module.entity.User;
import cn.lime.core.service.db.UserService;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeInviteRelation;
import cn.lime.anxin.service.db.distribute.DistributeInviteRelationService;
import cn.lime.anxin.mapper.DistributeInviteRelationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author riang
 * @description 针对表【Distribute_Invite_Relation】的数据库操作Service实现
 * @createDate 2024-08-23 14:26:06
 */
@Service
public class DistributeInviteRelationServiceImpl extends ServiceImpl<DistributeInviteRelationMapper, DistributeInviteRelation>
        implements DistributeInviteRelationService {
    @Resource
    private SnowFlakeGenerator ids;
    @Resource
    private DistributeUserService distributeUserService;
    @Resource
    private UserService userService;

    @Override
    public void inviteeRegister(Long inviteeId, Long inviterId) {
        DistributeInviteRelation relation = new DistributeInviteRelation();
        relation.setInviterId(inviterId);
        relation.setUserId(inviteeId);
        relation.setId(ids.nextId());
        ThrowUtils.throwIf(!save(relation), ErrorCode.INSERT_ERROR);
    }

    @Override
    public DistributeRelatorVo queryUpstream(Long userId) {
        Optional<DistributeInviteRelation> relationOptional = lambdaQuery().eq(DistributeInviteRelation::getUserId, userId).oneOpt();
        if (relationOptional.isEmpty()) {
            return null;
        } else {
            DistributeUser inviter = distributeUserService.getById(relationOptional.get().getInviterId());
            User user = userService.getById(relationOptional.get().getInviterId());
            DistributeRelatorVo vo = new DistributeRelatorVo();
            vo.setUserId(inviter.getUserId());
            vo.setLevel(inviter.getLevelId());
            vo.setNickname(user.getName());
            return vo;
        }
    }

    @Override
    public List<DistributeRelatorVo> queryDownstream(Long userId) {
        // 迭代查询相关下游人员
        List<DistributeRelatorVo> relateUserIds = new ArrayList<>();
        DistributeUser distributeUser = distributeUserService.getById(userId);
        iteratorQueryDownstream(userId, distributeUser.getLevelId(), relateUserIds);
        relateUserIds.sort(Comparator.comparing(DistributeRelatorVo::getLevel).reversed());
        return relateUserIds;
    }

    private void iteratorQueryDownstream(Long userId, Integer currentLevel, List<DistributeRelatorVo> res) {
        List<DistributeRelatorVo> directDownstream = baseMapper.queryDirectDownstream(userId,currentLevel);
        res.addAll(directDownstream);
        for (DistributeRelatorVo distributeRelatorVo : directDownstream) {
            // 如果是分销商
            if (distributeUserService.lambdaQuery().eq(DistributeUser::getUserId,distributeRelatorVo.getUserId()).exists()){
                iteratorQueryDownstream(distributeRelatorVo.getUserId(),distributeRelatorVo.getLevel(),res);
            }
        }
    }
}




