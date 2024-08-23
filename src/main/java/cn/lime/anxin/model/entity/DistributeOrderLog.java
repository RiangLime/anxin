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
 * @TableName Distribute_Order_Log
 */
@TableName(value ="Distribute_Order_Log")
@Data
public class DistributeOrderLog implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 1订单完成 2退款
     */
    @TableField(value = "op_type")
    private Integer opType;

    /**
     * 相关的分销商用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 涉及分销金额
     */
    @TableField(value = "amount")
    private Integer amount;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created")
    private Date gmtCreated;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}