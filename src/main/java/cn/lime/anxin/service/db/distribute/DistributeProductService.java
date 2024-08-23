package cn.lime.anxin.service.db.distribute;

import cn.lime.anxin.model.entity.DistributeProduct;
import cn.lime.anxin.model.vo.distribute.ProductWithDistributeTagPageVo;
import cn.lime.core.common.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author riang
* @description 针对表【Distribute_Product】的数据库操作Service
* @createDate 2024-08-23 14:52:20
*/
public interface DistributeProductService extends IService<DistributeProduct> {
    /**
     * 添加分销商品
     * @param productId
     * @param skuId
     */
    void addDistributeProducts(Long productId,Long skuId);

    /**
     * 商品取消分销
     * @param productId
     * @param skuId
     */
    void deleteDistributeProducts(Long productId,Long skuId);

    /**
     * 分页请求查询商品
     * @param productName
     * @param tagIds
     * @param productType
     * @param productState
     * @param current
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @return
     */
    PageResult<ProductWithDistributeTagPageVo> pageDistributeProducts(String productName, List<Long> tagIds,
                                                                      String productType, Integer productState,
                                                                      Integer distributeType,
                                                                      Integer current, Integer pageSize,
                                                                      String sortField, String sortOrder);
}
