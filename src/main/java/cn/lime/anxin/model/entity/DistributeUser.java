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
 * @TableName Distribute_User
 */
@TableName(value ="Distribute_User")
@Data
public class DistributeUser implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 等级ID
     */
    @TableField(value = "level_id")
    private Integer levelId;
    @TableField(value = "assets_get")
    private Integer assetsGet;
    @TableField(value = "assets_remain")
    private Integer assetsRemain;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created")
    private Date gmtCreated;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}