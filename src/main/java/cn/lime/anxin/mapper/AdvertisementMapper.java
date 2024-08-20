package cn.lime.anxin.mapper;

import cn.lime.anxin.model.entity.Advertisement;
import cn.lime.anxin.model.vo.AdDetailVo;
import cn.lime.anxin.model.vo.AdListVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author riang
* @description 针对表【Advertisement】的数据库操作Mapper
* @createDate 2024-08-20 15:37:08
* @Entity cn.lime.anxin.model.entity.Advertisement
*/
public interface AdvertisementMapper extends BaseMapper<Advertisement> {
    List<AdListVo> listAds();
    AdDetailVo getAdDetail(Integer id);
}




