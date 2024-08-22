package cn.lime.anxin.service.db.base.impl;

import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeRelation;
import cn.lime.anxin.service.db.base.DistributeRelationService;
import cn.lime.anxin.mapper.DistributeRelationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author riang
* @description 针对表【Distribute_Relation】的数据库操作Service实现
* @createDate 2024-08-22 16:44:32
*/
@Service
public class DistributeRelationServiceImpl extends ServiceImpl<DistributeRelationMapper, DistributeRelation>
    implements DistributeRelationService{
    @Resource
    private SnowFlakeGenerator ids;
    @Override
    public void addRelation(Long inviter, Long userId) {
        DistributeRelation relation = new DistributeRelation();
        relation.setId(ids.nextId());
        relation.setUpperUserId(inviter);
        relation.setLowerUserId(userId);
        ThrowUtils.throwIf(!save(relation), ErrorCode.INSERT_ERROR);
    }
}




