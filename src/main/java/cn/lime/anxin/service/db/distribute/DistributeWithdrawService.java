package cn.lime.anxin.service.db.distribute;

import cn.lime.anxin.model.entity.DistributeWithdraw;
import cn.lime.anxin.model.vo.distribute.DistributeWithdrawVo;
import cn.lime.core.common.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author riang
 * @description 针对表【Distribute_Withdraw】的数据库操作Service
 * @createDate 2024-08-26 12:18:11
 */
public interface DistributeWithdrawService extends IService<DistributeWithdraw> {
    void applyWithdraw(Long userId, Integer price);

    void reviewWithdraw(Long id, Boolean isApprove);

    PageResult<DistributeWithdrawVo> pageWithDraw(Long userId, Integer state, Integer priceStart, Integer priceEnd,
                                                  Long createTimeStart, Long createTimeEnd,
                                                  Integer current, Integer pageSize, String sortField, String sortOrder);
}
