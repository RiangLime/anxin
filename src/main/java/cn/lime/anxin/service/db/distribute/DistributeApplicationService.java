package cn.lime.anxin.service.db.distribute;

import cn.lime.anxin.model.entity.DistributeApplication;
import cn.lime.anxin.model.vo.distribute.ApplicationPageVo;
import cn.lime.core.common.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author riang
 * @description 针对表【Distribute_Application】的数据库操作Service
 * @createDate 2024-08-23 14:26:06
 */
public interface DistributeApplicationService extends IService<DistributeApplication> {
    /**
     * 用户申请成为分销商
     *
     * @param userId
     * @param realName
     * @param phone
     * @param region
     * @param reason
     */
    void apply(Long userId, String realName, String phone, String region, String reason);

    /**
     * 管理员审批
     *
     * @param applyId
     * @param isApprove
     */
    void reviewApplication(Long applyId, Integer isApprove,String remark);

    /**
     * 分页查询请求
     * @param region
     * @param applyTimeStart
     * @param applyTimeEnd
     * @param state
     * @param queryField  用户昵称/用户账号/用户真实姓名
     * @return
     */
    PageResult<ApplicationPageVo> pageApplications(String region, Long applyTimeStart, Long applyTimeEnd,
                                                   Integer state, String queryField,Integer current,Integer pageSize);
}
