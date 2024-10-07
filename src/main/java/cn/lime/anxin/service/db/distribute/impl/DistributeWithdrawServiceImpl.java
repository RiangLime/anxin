package cn.lime.anxin.service.db.distribute.impl;

import cn.lime.anxin.constants.DistributeWithdrawState;
import cn.lime.anxin.model.entity.DistributeUser;
import cn.lime.anxin.model.vo.distribute.DistributeWithdrawVo;
import cn.lime.anxin.service.db.distribute.DistributeOrderLogService;
import cn.lime.anxin.service.db.distribute.DistributeUserService;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.PageResult;
import cn.lime.core.common.PageUtils;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.constant.YesNoEnum;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import cn.lime.core.utils.TimeStampUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeWithdraw;
import cn.lime.anxin.service.db.distribute.DistributeWithdrawService;
import cn.lime.anxin.mapper.DistributeWithdrawMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author riang
 * @description 针对表【Distribute_Withdraw】的数据库操作Service实现
 * @createDate 2024-08-26 12:18:11
 */
@Service
public class DistributeWithdrawServiceImpl extends ServiceImpl<DistributeWithdrawMapper, DistributeWithdraw>
        implements DistributeWithdrawService {
    @Resource
    private SnowFlakeGenerator ids;
    @Resource
    private DistributeUserService userService;
    @Resource
    private DistributeOrderLogService logService;

    @Override
    public void applyWithdraw(Long userId, Integer price) {
        DistributeUser user = userService.getById(userId);
        ThrowUtils.throwIf(!ObjectUtils.isEmpty(user), ErrorCode.NOT_FOUND_ERROR, "该用户不是分销商");
        ThrowUtils.throwIf(price > user.getAssetsRemain(), ErrorCode.PARAMS_ERROR, "该分销商可提现余额不足");
        DistributeWithdraw bean = new DistributeWithdraw();
        bean.setId(ids.nextId());
        bean.setNumber(price);
        bean.setState(DistributeWithdrawState.APPLY.getVal());
        bean.setUserId(userId);
        ThrowUtils.throwIf(!save(bean), ErrorCode.INSERT_ERROR);
    }

    @Override
    @Transactional
    public void reviewWithdraw(Long id, Integer isApprove) {
        DistributeWithdraw withdraw = getById(id);
        ThrowUtils.throwIf(!ObjectUtils.isEmpty(withdraw), ErrorCode.NOT_FOUND_ERROR);
        LambdaUpdateWrapper<DistributeWithdraw> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(DistributeWithdraw::getId, id);
        if (isApprove == YesNoEnum.YES.getVal()) {
            wrapper.set(DistributeWithdraw::getState, DistributeWithdrawState.APPROVE.getVal());
            userService.userWithdraw(withdraw.getUserId(), withdraw.getNumber());
        } else {
            wrapper.set(DistributeWithdraw::getState, DistributeWithdrawState.REFUSE.getVal());
        }
        wrapper.set(DistributeWithdraw::getReviewTime, TimeStampUtils.getUnixTimestamp());
        ThrowUtils.throwIf(!update(wrapper), ErrorCode.UPDATE_ERROR);
    }

    @Override
    public PageResult<DistributeWithdrawVo> pageWithDraw(Long userId, Integer state, Integer priceStart, Integer priceEnd,
                                                         Long createTimeStart, Long createTimeEnd,
                                                         Integer current, Integer pageSize, String sortField, String sortOrder) {
        Page<?> page = PageUtils.build(current, pageSize, sortField, sortOrder);
        Page<DistributeWithdrawVo> res = baseMapper.pageWithdraws(userId, state, priceStart, priceEnd, createTimeStart,
                createTimeEnd, page);
        return new PageResult<>(res);
    }
}




