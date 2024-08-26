package cn.lime.anxin.service.db.distribute.impl;

import cn.lime.anxin.model.vo.distribute.ProductWithDistributeTagPageVo;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.PageResult;
import cn.lime.core.common.PageUtils;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeProduct;
import cn.lime.anxin.service.db.distribute.DistributeProductService;
import cn.lime.anxin.mapper.DistributeProductMapper;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
* @author riang
* @description 针对表【Distribute_Product】的数据库操作Service实现
* @createDate 2024-08-23 14:52:20
*/
@Service
public class DistributeProductServiceImpl extends ServiceImpl<DistributeProductMapper, DistributeProduct>
    implements DistributeProductService{
    @Resource
    private SnowFlakeGenerator ids;
    @Override
    public void addDistributeProducts(@NotNull Long productId, Long skuId) {
        DistributeProduct product = new DistributeProduct();
        product.setId(ids.nextId());
        product.setProductId(productId);
        if (ObjectUtils.isNotEmpty(skuId)){
            product.setSkuId(skuId);
        }
        ThrowUtils.throwIf(!save(product), ErrorCode.INSERT_ERROR);
    }

    @Override
    public void deleteDistributeProducts(Long productId, Long skuId) {
        LambdaUpdateWrapper<DistributeProduct> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(DistributeProduct::getProductId,productId);
        if (!ObjectUtils.isEmpty(skuId)){
            wrapper.set(DistributeProduct::getSkuId,skuId);
        }
        ThrowUtils.throwIf(!remove(wrapper),ErrorCode.DELETE_ERROR);
    }

    @Override
    public PageResult<ProductWithDistributeTagPageVo> pageDistributeProducts(String productName, List<Long> tagIds,
                                                                             String productType, Integer productState,
                                                                             Integer distributeType,
                                                                             Integer current, Integer pageSize,
                                                                             String sortField, String sortOrder) {
        Page<?> page = PageUtils.build(current,pageSize,sortField,sortOrder);
        Page<ProductWithDistributeTagPageVo> res = baseMapper.pageProduct(
                productName,tagIds,productType,productState,distributeType,page);
        return new PageResult<>(res);
    }

    @Override
    public boolean isDistributeProduct(Long productId, Long skuId) {
        if (lambdaQuery().eq(DistributeProduct::getProductId,productId).eq(DistributeProduct::getSkuId,skuId).exists()){
            return true;
        }else {
            return lambdaQuery().eq(DistributeProduct::getProductId, productId).exists();
        }
    }
}




