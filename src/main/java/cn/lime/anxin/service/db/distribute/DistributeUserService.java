package cn.lime.anxin.service.db.distribute;

import cn.lime.anxin.model.entity.DistributeUser;
import cn.lime.anxin.model.vo.distribute.DistributeSummaryVo;
import cn.lime.anxin.model.vo.distribute.LevelDistributorInfo;
import cn.lime.anxin.model.vo.distribute.UserRelateDistributeOrderVo;
import cn.lime.core.common.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author riang
* @description 针对表【Distribute_User】的数据库操作Service
* @createDate 2024-08-23 14:26:06
*/
public interface DistributeUserService extends IService<DistributeUser> {
    void addDistributor(Long userId);
    void updateDistributorLevel(Long userId,Integer level);
    void userWithdraw(Long userId,Integer price);
    DistributeSummaryVo getUserInfo(Long userId, Integer current, Integer pageSize);
    List<LevelDistributorInfo> getAllDistributorInfo();
    void updateDistributorUpstream(Long userId,Long inviterId);

    void freezeDistributor(Long userId,Integer state);
    void freezeDistributorRelation(Long userId,Integer state);

    PageResult<UserRelateDistributeOrderVo> getRelatedDistributeOrders(Long userId, Integer current, Integer pageSize);
}
