package cn.lime.anxin.mapper;

import cn.lime.anxin.model.entity.DistributeInviteRelation;
import cn.lime.anxin.model.vo.distribute.DistributeRelatorVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author riang
* @description 针对表【Distribute_Invite_Relation】的数据库操作Mapper
* @createDate 2024-08-23 14:26:06
* @Entity cn.lime.anxin.model.entity.DistributeInviteRelation
*/
public interface DistributeInviteRelationMapper extends BaseMapper<DistributeInviteRelation> {
    List<DistributeRelatorVo> queryDirectDownstream(Long inviterId,Integer currentLevel);
}




