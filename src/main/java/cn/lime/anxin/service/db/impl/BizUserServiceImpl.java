package cn.lime.anxin.service.db.impl;

import cn.lime.anxin.mapper.BizUserMapper;
import cn.lime.anxin.model.vo.DailyNewUserCount;
import cn.lime.anxin.service.db.BizUserService;
import cn.lime.core.common.ErrorCode;
import cn.lime.core.common.PageResult;
import cn.lime.core.common.PageUtils;
import cn.lime.core.common.ThrowUtils;
import cn.lime.core.config.CoreParams;
import cn.lime.core.constant.YesNoEnum;
import cn.lime.core.mapper.UserMapper;
import cn.lime.core.module.entity.User;
import cn.lime.core.module.entity.Userthirdauthorization;
import cn.lime.core.module.vo.LoginVo;
import cn.lime.core.module.vo.UserVo;
import cn.lime.core.service.db.UserService;
import cn.lime.core.service.db.UserthirdauthorizationService;
import cn.lime.core.service.login.UniLogService;
import cn.lime.core.service.oss.QiNiuOssService;
import cn.lime.core.service.phone.BasePhoneService;
import cn.lime.core.service.wx.auth.WxMpOuterService;
import cn.lime.core.service.wx.bean.WxPhoneInfo;
import cn.lime.core.snowflake.SnowFlakeGenerator;
import cn.lime.core.threadlocal.ReqThreadLocal;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

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




