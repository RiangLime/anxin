package cn.lime.anxin.service.db.base;

import cn.lime.anxin.model.entity.Detectorder;
import cn.lime.anxin.model.vo.DetectOrderDetailVo;
import cn.lime.anxin.model.vo.DetectOrderPageVo;
import cn.lime.anxin.model.vo.QrCodeVo;
import cn.lime.core.common.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author riang
 * @description 针对表【DetectOrder】的数据库操作Service
 * @createDate 2024-08-20 15:37:08
 */
public interface DetectorderService extends IService<Detectorder> {
    QrCodeVo createDetectOrder(Long productId, Long skuId, Long orderId);

    QrCodeVo copyFromDetectOrder(String oldCode, Long updateProductId, Long updateSkuId, Long updateOrderId);

    void bind(String code);

    void confirmReadyToReturn(String code);

    void setReturnDeliverInfo(String code, String deliverCompany, String deliverCode);

    void confirmReceiveReturn(String code);

    void uploadReport(String code, String title, String name, Integer isNormal, Integer canUpdate, Long proId, Long skuId, List<String> reportUrls, List<String> contactorUrls);

    PageResult<DetectOrderPageVo> pageDetectOrders(Long bindUserId, String userName, String productName, String code,
                                                   Integer state, Integer canUpdate, Integer isUpdated,
                                                   Integer current, Integer pageSize);

    DetectOrderDetailVo getDetectOrderDetail(Long id);
}
