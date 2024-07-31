package cn.lime.anxin.mapper;

import cn.lime.anxin.model.vo.DailyNewUserCount;
import cn.lime.core.module.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: BizUserMapper
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/7/31 14:32
 */
@Mapper
public interface BizUserMapper extends BaseMapper<User> {
    @Select("SELECT COUNT(*) FROM User WHERE DATE(gmt_created) = CURDATE()")
    int countNewUsersToday();
    @Select("SELECT COUNT(*) FROM User")
    int countUsers();
    @Select("SELECT DATE(gmt_created) AS date, COUNT(*) AS count " +
            "FROM User " +
            "WHERE gmt_created >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) " +
            "GROUP BY DATE(gmt_created) " +
            "ORDER BY DATE(gmt_created)")
    List<DailyNewUserCount> countNewUsersLastMonth();
}
