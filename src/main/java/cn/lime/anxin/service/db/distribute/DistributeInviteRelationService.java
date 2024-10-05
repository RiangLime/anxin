package cn.lime.anxin.service.db.distribute;

import cn.lime.anxin.model.entity.DistributeInviteRelation;
import cn.lime.anxin.model.vo.distribute.DistributeRelatorVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author riang
* @description 针对表【Distribute_Invite_Relation】的数据库操作Service
* @createDate 2024-08-23 14:26:06
*/
public interface DistributeInviteRelationService extends IService<DistributeInviteRelation> {
    /**
     * 通过邀请的新用户注册
     */
    void inviteeRegister(Long inviteeId,Long inviterId);

    DistributeRelatorVo queryUpstream(Long userId);
    List<DistributeRelatorVo> queryDownstream(Long userId);
}
