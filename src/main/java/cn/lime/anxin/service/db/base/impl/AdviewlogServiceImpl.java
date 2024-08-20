package cn.lime.anxin.service.db.base.impl;

import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import cn.lime.core.threadlocal.ReqThreadLocal;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.Adviewlog;
import cn.lime.anxin.service.db.base.AdviewlogService;
import cn.lime.anxin.mapper.AdviewlogMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author riang
* @description 针对表【AdViewLog】的数据库操作Service实现
* @createDate 2024-08-20 15:37:08
*/
@Service
public class AdviewlogServiceImpl extends ServiceImpl<AdviewlogMapper, Adviewlog>
    implements AdviewlogService{
    @Resource
    private SnowFlakeGenerator ids;
    @Override
    public void appendLog(Integer id) {
        Adviewlog bean = new Adviewlog();
        bean.setId(ids.nextId());
        bean.setAdId(id);
        bean.setUserId(ReqThreadLocal.getInfo().getUserId());
        ThrowUtils.throwIf(!save(bean), ErrorCode.INSERT_ERROR,"用户阅读广告页留痕失败");
    }
}




