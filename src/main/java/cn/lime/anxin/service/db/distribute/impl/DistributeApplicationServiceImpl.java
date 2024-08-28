package cn.lime.anxin.service.db.distribute.impl;

import cn.lime.anxin.model.vo.distribute.ApplicationPageVo;
import cn.lime.anxin.service.db.distribute.DistributeUserService;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.PageResult;
import cn.lime.core.common.PageUtils;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.constant.YesNoEnum;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeApplication;
import cn.lime.anxin.service.db.distribute.DistributeApplicationService;
import cn.lime.anxin.mapper.DistributeApplicationMapper;
import com.stripe.model.Application;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static cn.lime.core.utils.TimeStampUtils.getUnixTimestamp;

/**
 * @author riang
 * @description 针对表【Distribute_Application】的数据库操作Service实现
 * @createDate 2024-08-23 14:26:06
 */
@Service
public class DistributeApplicationServiceImpl extends ServiceImpl<DistributeApplicationMapper, DistributeApplication>
        implements DistributeApplicationService {
    @Resource
    private SnowFlakeGenerator ids;
    @Resource
    private DistributeUserService distributeUserService;

    @Override
    public DistributeApplication getById(Serializable id) {
        DistributeApplication bean = super.getById(id);
        ThrowUtils.throwIf(ObjectUtils.isEmpty(bean), ErrorCode.NOT_FOUND_ERROR);
        return bean;
    }

    @Override
    public void apply(Long userId, String realName, String phone, String region, String reason) {
        DistributeApplication apply = new DistributeApplication();
        apply.setId(ids.nextId());
        apply.setUserId(userId);
        apply.setRealName(realName);
        apply.setPhone(phone);
        apply.setReason(reason);
        apply.setRegion(region);
        ThrowUtils.throwIf(!save(apply), ErrorCode.INSERT_ERROR);
    }

    @Override
    @Transactional
    public void reviewApplication(Long applyId, Integer isApprove, String remark) {
        DistributeApplication bean = getById(applyId);
        ThrowUtils.throwIf(!lambdaUpdate().eq(DistributeApplication::getId, applyId)
                .set(DistributeApplication::getState, isApprove)
                .set(DistributeApplication::getApproveTime, getUnixTimestamp())
                .set(DistributeApplication::getRemark, remark)
                .update(), ErrorCode.UPDATE_ERROR, "审批失败");
        // 审批通过 添加分销商信息
        if (isApprove.equals(YesNoEnum.YES.getVal())) {
            distributeUserService.addDistributor(bean.getUserId());
        }
    }

    @Override
    public PageResult<ApplicationPageVo> pageApplications(Long userId, String region, Long applyTimeStart, Long applyTimeEnd,
                                                          Integer state, String queryField, Integer current, Integer pageSize) {
        Page<?> page = PageUtils.build(current, pageSize, null, null);
        Page<ApplicationPageVo> vo = baseMapper.pageApplys(userId, region, applyTimeStart, applyTimeEnd, state, queryField, page);
        return new PageResult<>(vo);
    }
}




