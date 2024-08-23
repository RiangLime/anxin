package cn.lime.anxin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName Distribute_Application
 */
@TableName(value ="Distribute_Application")
@Data
public class DistributeApplication implements Serializable {
    /**
     * 申请ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 所属地区
     */
    @TableField(value = "region")
    private String region;

    /**
     * 申请理由
     */
    @TableField(value = "reason")
    private String reason;

    /**
     * 申请状态 0待同意 1已批准
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 批准时间
     */
    @TableField(value = "approve_time")
    private Long approveTime;

    @TableField(value = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created")
    private Date gmtCreated;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}