package cn.lime.anxin.service.db.base.impl;

import cn.lime.anxin.model.dto.structure.AdStructureDto;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.Adstructure;
import cn.lime.anxin.service.db.base.AdstructureService;
import cn.lime.anxin.mapper.AdstructureMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author riang
* @description 针对表【AdStructure】的数据库操作Service实现
* @createDate 2024-08-20 15:37:07
*/
@Service
public class AdstructureServiceImpl extends ServiceImpl<AdstructureMapper, Adstructure>
    implements AdstructureService{

    @Override
    @Transactional
    public void addForAd(Integer adId, List<AdStructureDto> adStructures) {
        if (lambdaQuery().eq(Adstructure::getAdId,adId).exists()){
            ThrowUtils.throwIf(!lambdaUpdate().eq(Adstructure::getAdId,adId).remove(), ErrorCode.DELETE_ERROR);
        }
        List<Adstructure> beans = new ArrayList<>();
        for (int i = 0; i < adStructures.size(); i++) {
            Adstructure bean = new Adstructure();
            bean.setAdId(adId);
            bean.setSerialId(i);
            bean.setStructType(adStructures.get(i).getStructureType());
            bean.setStructContent(adStructures.get(i).getStructureContent());
            beans.add(bean);
        }
        ThrowUtils.throwIf(!saveBatch(beans),ErrorCode.INSERT_ERROR);
    }
}




