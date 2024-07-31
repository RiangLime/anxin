package cn.lime.anxin.service.db;

import cn.lime.anxin.model.vo.DailyNewUserCount;
import cn.lime.core.module.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: BizUserService
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/7/31 14:42
 */
public interface BizUserService extends IService<User> {
    int getNewUserNum();
    int getUserNum();
    List<DailyNewUserCount> getRecentOneMonthNewUser();
}
