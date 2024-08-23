package cn.lime.anxin.model.vo.distribute;

import cn.lime.core.module.vo.UserVo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ApplicationPageVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/23 14:42
 */
@Data
public class ApplicationPageVo implements Serializable {
    /**
     * 申请信息
     */
    private Long id;
    private Long userId;
    private String realName;
    private String phone;
    private String region;
    private String reason;
    private Integer state;
    private Long approveTime;
    private String remark;
    private Long applyTime;
    /**
     * 用户信息
     */
    private UserVo userVo;
}
