package cn.lime.anxin.service.db.extend.impl;

import cn.lime.anxin.mapper.BizUserMapper;
import cn.lime.anxin.model.vo.DailyNewUserCount;
import cn.lime.anxin.service.db.extend.BizUserService;
import cn.lime.core.module.entity.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author riang
* @description 针对表【User(用户表)】的数据库操作Service实现
* @createDate 2024-03-15 12:13:40
*/
@Service
public class BizUserServiceImpl extends ServiceImpl<BizUserMapper, User>
    implements BizUserService {

    @Override
    public int getNewUserNum() {
        return baseMapper.countNewUsersToday();
    }

    @Override
    public int getUserNum() {
        return baseMapper.countUsers();
    }

    @Override
    public List<DailyNewUserCount> getRecentOneMonthNewUser(){
        return baseMapper.countNewUsersLastMonth();

    }
}




