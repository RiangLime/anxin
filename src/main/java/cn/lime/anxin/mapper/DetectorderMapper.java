package cn.lime.anxin.mapper;

import cn.lime.anxin.model.entity.Detectorder;
import cn.lime.anxin.model.vo.DetectOrderDetailVo;
import cn.lime.anxin.model.vo.DetectOrderPageVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author riang
* @description 针对表【DetectOrder】的数据库操作Mapper
* @createDate 2024-08-20 18:37:58
* @Entity cn.lime.anxin.model.entity.Detectorder
*/
public interface DetectorderMapper extends BaseMapper<Detectorder> {
    Page<DetectOrderPageVo> page(Long bindUserId, String bindUserName, String productName, String code, Integer state,Page<?> page);
    DetectOrderDetailVo detail(Long id);
}




