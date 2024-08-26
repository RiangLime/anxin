package cn.lime.anxin.service.db.distribute.impl;

import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeInviteRelation;
import cn.lime.anxin.service.db.distribute.DistributeInviteRelationService;
import cn.lime.anxin.mapper.DistributeInviteRelationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author riang
* @description 针对表【Distribute_Invite_Relation】的数据库操作Service实现
* @createDate 2024-08-23 14:26:06
*/
@Service
public class DistributeInviteRelationServiceImpl extends ServiceImpl<DistributeInviteRelationMapper, DistributeInviteRelation>
    implements DistributeInviteRelationService{
    @Resource
    private SnowFlakeGenerator ids;
    @Override
    public void inviteeRegister(Long inviteeId, Long inviterId) {
        DistributeInviteRelation relation = new DistributeInviteRelation();
        relation.setInviterId(inviterId);
        relation.setUserId(inviteeId);
        relation.setId(ids.nextId());
        ThrowUtils.throwIf(!save(relation), ErrorCode.INSERT_ERROR);
    }
}




