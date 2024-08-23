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
 * @TableName Distribute_Level
 */
@TableName(value ="Distribute_Level")
@Data
public class DistributeLevel implements Serializable {
    /**
     * 等级 1 2 3 ...
     */
    @TableId(value = "level_id")
    private Integer levelId;

    /**
     * 等级名称
     */
    @TableField(value = "level_name")
    private String levelName;

    /**
     * 一级佣金比例 百分比
     */
    @TableField(value = "rate1")
    private Integer rate1;

    /**
     * 二级佣金比例 百分比
     */
    @TableField(value = "rate2")
    private Integer rate2;

    /**
     * 自购佣金比例 百分比
     */
    @TableField(value = "rate3")
    private Integer rate3;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created")
    private Date gmtCreated;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}