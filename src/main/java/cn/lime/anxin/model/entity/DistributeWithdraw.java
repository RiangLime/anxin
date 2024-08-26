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
 * @TableName Distribute_Withdraw
 */
@TableName(value ="Distribute_Withdraw")
@Data
public class DistributeWithdraw implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 申请提现数量
     */
    @TableField(value = "number")
    private Integer number;

    /**
     * 0已提交申请 1已通过 2已拒绝
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 审批时间
     */
    @TableField(value = "review_time")
    private Long reviewTime;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created")
    private Date gmtCreated;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}