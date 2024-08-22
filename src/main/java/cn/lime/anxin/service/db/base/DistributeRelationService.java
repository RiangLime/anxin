package cn.lime.anxin.service.db.base;

import cn.lime.anxin.model.entity.DistributeRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author riang
* @description 针对表【Distribute_Relation】的数据库操作Service
* @createDate 2024-08-22 16:44:32
*/
public interface DistributeRelationService extends IService<DistributeRelation> {
    void addRelation(Long inviter,Long userId);
}
