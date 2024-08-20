package cn.lime.anxin.service.db.base;

import cn.lime.anxin.model.entity.Adviewlog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author riang
* @description 针对表【AdViewLog】的数据库操作Service
* @createDate 2024-08-20 15:37:08
*/
public interface AdviewlogService extends IService<Adviewlog> {
    void appendLog(Integer id);
}
