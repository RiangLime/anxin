package cn.lime.anxin.service.db.distribute;

import cn.lime.anxin.model.entity.DistributeLevel;
import cn.lime.anxin.model.vo.distribute.DistributeLevelVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author riang
* @description 针对表【Distribute_Level】的数据库操作Service
* @createDate 2024-08-23 14:26:06
*/
public interface DistributeLevelService extends IService<DistributeLevel> {
    /**
     * 新建分销等级
     * @param level 等级
     * @param name  等级名
     * @param rate1 一级分销佣金率
     * @param rate2 二级分销佣金率
     * @param rate3 自购分销佣金率
     */
    void addLevel(Integer level,String name,Integer rate1,Integer rate2,Integer rate3);

    /**
     * 更新分销等级信息
     * @param level 等级
     * @param name  等级名
     * @param rate1 一级分销佣金率
     * @param rate2 二级分销佣金率
     * @param rate3 自购分销佣金率
     */
    void updateLevel(Integer level,String name,Integer rate1,Integer rate2,Integer rate3);

    /**
     * 删除分销等级
     * @param level 分销等级
     */
    void deleteLevel(Integer level);
    /**
     * 查询所有分销等级
     * @return 分销登记信息
     */
    List<DistributeLevelVo> listLevels();
}
