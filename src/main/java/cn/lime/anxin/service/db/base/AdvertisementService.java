package cn.lime.anxin.service.db.base;

import cn.lime.anxin.model.dto.AdStructureDto;
import cn.lime.anxin.model.entity.Advertisement;
import cn.lime.anxin.model.vo.AdDetailVo;
import cn.lime.anxin.model.vo.AdListVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author riang
* @description 针对表【Advertisement】的数据库操作Service
* @createDate 2024-08-20 15:37:08
*/
public interface AdvertisementService extends IService<Advertisement> {
    void addAd(String title, String picture, List<AdStructureDto> adStructures);
    void updateAd(Integer adId,String title, String picture, List<AdStructureDto> adStructures);
    void deleteAd(Integer adId);
    List<AdListVo> listAds();
    AdDetailVo getAdDetail(Integer adId);
}
