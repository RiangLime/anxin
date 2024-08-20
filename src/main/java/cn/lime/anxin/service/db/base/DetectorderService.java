package cn.lime.anxin.service.db.base;

import cn.lime.anxin.model.entity.Detectorder;
import cn.lime.anxin.model.vo.QrCodeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author riang
* @description 针对表【DetectOrder】的数据库操作Service
* @createDate 2024-08-20 15:37:08
*/
public interface DetectorderService extends IService<Detectorder> {
    QrCodeVo createDetectOrder(Long productId, Long skuId);
    void bind(String code);
    void confirmReadyToReturn(String code);
    void setReturnDeliverInfo(String code, String deliverCompany,String deliverCode);
    void confirmReceiveReturn(String code);
    void uploadReport(String code, String title, Integer isNormal, List<String> reportUrls, List<String> contactorUrls);

}
