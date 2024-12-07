package cn.lime.anxin.mapper;

import cn.lime.anxin.model.entity.DistributeOrderLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author riang
* @description 针对表【Distribute_Order_Log】的数据库操作Mapper
* @createDate 2024-08-23 15:20:04
* @Entity cn.lime.anxin.model.entity.DistributeOrderLog
*/
public interface DistributeOrderLogMapper extends BaseMapper<DistributeOrderLog> {
    Page<DistributeOrderLog> pages(Long userId,Page<?> page);
}




