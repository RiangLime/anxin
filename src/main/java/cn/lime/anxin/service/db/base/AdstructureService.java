package cn.lime.anxin.service.db.base;

import cn.lime.anxin.model.dto.AdStructureDto;
import cn.lime.anxin.model.entity.Adstructure;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author riang
* @description 针对表【AdStructure】的数据库操作Service
* @createDate 2024-08-20 15:37:07
*/
public interface AdstructureService extends IService<Adstructure> {
    void addForAd(Integer adId, List<AdStructureDto> adStructures);
}
