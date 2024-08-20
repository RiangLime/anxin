package cn.lime.anxin.service.db.base;

import cn.lime.anxin.model.entity.Homepagestructure;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author riang
* @description 针对表【HomePageStructure】的数据库操作Service
* @createDate 2024-08-20 15:37:08
*/
public interface HomepagestructureService extends IService<Homepagestructure> {
    void addVersion(String version,String content);

    List<Homepagestructure> getAll();

    Homepagestructure getLatest();
}
