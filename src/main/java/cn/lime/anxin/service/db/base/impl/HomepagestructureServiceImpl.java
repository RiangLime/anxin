package cn.lime.anxin.service.db.base.impl;

import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.Homepagestructure;
import cn.lime.anxin.service.db.base.HomepagestructureService;
import cn.lime.anxin.mapper.HomepagestructureMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author riang
* @description 针对表【HomePageStructure】的数据库操作Service实现
* @createDate 2024-08-20 15:37:08
*/
@Service
public class HomepagestructureServiceImpl extends ServiceImpl<HomepagestructureMapper, Homepagestructure>
    implements HomepagestructureService{
    @Override
    public void addVersion(String version, String content) {
        Homepagestructure bean = new Homepagestructure();
        bean.setVersion(version);
        bean.setStructure(content);
        ThrowUtils.throwIf(!save(bean), ErrorCode.INSERT_ERROR);
    }

    @Override
    public List<Homepagestructure> getAll() {
        return lambdaQuery().list().stream().sorted((a, b) -> b.getId() - a.getId()).toList();
    }

    @Override
    public Homepagestructure getLatest() {
        List<Homepagestructure> list =  getAll();
        return list.isEmpty()?null:list.get(0);
    }
}




