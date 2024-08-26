package cn.lime.anxin.service.db.distribute.impl;

import cn.lime.anxin.model.vo.distribute.DistributeLevelVo;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.ThrowUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeLevel;
import cn.lime.anxin.service.db.distribute.DistributeLevelService;
import cn.lime.anxin.mapper.DistributeLevelMapper;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author riang
 * @description 针对表【Distribute_Level】的数据库操作Service实现
 * @createDate 2024-08-23 14:26:06
 */
@Service
public class DistributeLevelServiceImpl extends ServiceImpl<DistributeLevelMapper, DistributeLevel>
        implements DistributeLevelService {

    @Override
    public DistributeLevel getById(Serializable id) {
        Optional<DistributeLevel> l = lambdaQuery().eq(DistributeLevel::getLevelId, id).oneOpt();
        return l.orElseGet(this::getDefault);
    }

    private DistributeLevel getDefault() {
        Optional<DistributeLevel> l = lambdaQuery().eq(DistributeLevel::getLevelId, 100).oneOpt();
        if (l.isEmpty()) {
            DistributeLevel b = new DistributeLevel();
            b.setLevelId(100);
            b.setRate1(0);
            b.setRate2(0);
            b.setRate3(0);
            return b;
        } else {
            return l.get();
        }
    }

    @Override
    public void addLevel(Integer level, String name, Integer rate1, Integer rate2, Integer rate3) {
        DistributeLevel bean = new DistributeLevel();
        bean.setLevelId(level);
        bean.setLevelName(name);
        bean.setRate1(rate1);
        bean.setRate2(rate2);
        bean.setRate3(rate3);
        ThrowUtils.throwIf(!save(bean), ErrorCode.INSERT_ERROR);
    }

    @Override
    public void updateLevel(@NotNull Integer level, String name, Integer rate1, Integer rate2, Integer rate3) {
        LambdaUpdateWrapper<DistributeLevel> wrapper = new LambdaUpdateWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.set(DistributeLevel::getLevelName, name);
        }
        if (ObjectUtils.isNotEmpty(rate1)) {
            wrapper.set(DistributeLevel::getRate1, rate1);
        }
        if (ObjectUtils.isNotEmpty(rate2)) {
            wrapper.set(DistributeLevel::getRate2, rate2);
        }
        if (ObjectUtils.isNotEmpty(rate3)) {
            wrapper.set(DistributeLevel::getRate3, rate3);
        }
        ThrowUtils.throwIf(!update(wrapper), ErrorCode.UPDATE_ERROR);
    }

    @Override
    public void deleteLevel(Integer level) {
        ThrowUtils.throwIf(!lambdaUpdate().eq(DistributeLevel::getLevelId, level).remove(), ErrorCode.DELETE_ERROR);
    }

    @Override
    public List<DistributeLevelVo> listLevels() {
        return lambdaQuery().list().stream().map(DistributeLevelVo::fromBean).toList();
    }
}




