package cn.lime.anxin.service.db.distribute.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.lime.anxin.model.entity.DistributeUser;
import cn.lime.anxin.service.db.distribute.DistributeUserService;
import cn.lime.anxin.mapper.DistributeUserMapper;
import org.springframework.stereotype.Service;

/**
* @author riang
* @description 针对表【Distribute_User】的数据库操作Service实现
* @createDate 2024-08-23 14:26:06
*/
@Service
public class DistributeUserServiceImpl extends ServiceImpl<DistributeUserMapper, DistributeUser>
    implements DistributeUserService{
    @Override
    public void addDistributor(Long userId) {

    }

    @Override
    public void updateDistributorLevel(Long userId, Integer level) {

    }
}




