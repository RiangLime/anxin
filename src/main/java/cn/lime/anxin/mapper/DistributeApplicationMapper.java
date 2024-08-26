package cn.lime.anxin.mapper;

import cn.lime.anxin.model.entity.DistributeApplication;
import cn.lime.anxin.model.vo.distribute.ApplicationPageVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author riang
* @description 针对表【Distribute_Application】的数据库操作Mapper
* @createDate 2024-08-23 14:26:06
* @Entity cn.lime.anxin.model.entity.DistributeApplication
*/
public interface DistributeApplicationMapper extends BaseMapper<DistributeApplication> {
    Page<ApplicationPageVo> pageApplys(Long userId, String region, Long applyTimeStart, Long applyTimeEnd,
                                       Integer state, String queryField,Page<?> page);
}




