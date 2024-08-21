package cn.lime.anxin.service.db.base.impl;

import cn.lime.anxin.model.dto.structure.AdStructureDto;
import cn.lime.anxin.model.vo.AdDetailVo;
import cn.lime.anxin.model.vo.AdListVo;
import cn.lime.anxin.service.db.base.AdstructureService;
import cn.lime.anxin.service.db.base.AdviewlogService;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.Advertisement;
import cn.lime.anxin.service.db.base.AdvertisementService;
import cn.lime.anxin.mapper.AdvertisementMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author riang
* @description 针对表【Advertisement】的数据库操作Service实现
* @createDate 2024-08-20 15:37:08
*/
@Service
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper, Advertisement>
    implements AdvertisementService{
    @Resource
    private AdstructureService adstructureService;
    @Resource
    private AdviewlogService adviewlogService;

    @Override
    @Transactional
    public void addAd(String title, String picture, List<AdStructureDto> adStructures) {

        // 广告
        Advertisement ad = new Advertisement();
        ad.setTitle(title);
        ad.setPicture(picture);
        ThrowUtils.throwIf(!save(ad), ErrorCode.INSERT_ERROR,"保存广告信息失败");
        // 广告结构
        adstructureService.addForAd(ad.getId(),adStructures);
    }

    @Override
    public void updateAd(Integer adId, String title, String picture, List<AdStructureDto> adStructures) {
        if (StringUtils.isNotEmpty(title) || StringUtils.isNotEmpty(picture)){
            LambdaUpdateWrapper<Advertisement> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(Advertisement::getId,adId);
            if (StringUtils.isNotEmpty(title)){
                wrapper.set(Advertisement::getTitle,title);
            }
            if (StringUtils.isNotEmpty(picture)){
                wrapper.set(Advertisement::getPicture,picture);
            }
            ThrowUtils.throwIf(!update(wrapper),ErrorCode.UPDATE_ERROR,"更新广告信息失败");
        }
        // 广告结构
        adstructureService.addForAd(adId,adStructures);
    }

    @Override
    public void deleteAd(Integer adId) {
        ThrowUtils.throwIf(!lambdaUpdate().eq(Advertisement::getId,adId).remove(),ErrorCode.DELETE_ERROR);
    }

    @Override
    public List<AdListVo> listAds() {
        return baseMapper.listAds();
    }

    @Override
    public AdDetailVo getAdDetail(Integer adId) {
        AdDetailVo vo = baseMapper.getAdDetail(adId);
        adviewlogService.appendLog(adId);
        return vo;
    }
}




