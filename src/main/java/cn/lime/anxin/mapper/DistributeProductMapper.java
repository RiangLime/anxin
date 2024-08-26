package cn.lime.anxin.mapper;

import cn.lime.anxin.model.entity.DistributeProduct;
import cn.lime.anxin.model.vo.distribute.ProductWithDistributeTagPageVo;
import cn.lime.mall.model.vo.ProductPageVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* @author riang
* @description 针对表【Distribute_Product】的数据库操作Mapper
* @createDate 2024-08-23 14:52:20
* @Entity cn.lime.anxin.model.entity.DistributeProduct
*/
public interface DistributeProductMapper extends BaseMapper<DistributeProduct> {
    Page<ProductWithDistributeTagPageVo> pageProduct(String productName, List<Long> tagIds, String productType, Integer state,
                                                     Integer distributeState, Page<?> page);
}




