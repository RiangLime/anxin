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
 * @TableName AdStructure
 */
@TableName(value ="AdStructure")
@Data
public class Adstructure implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告ID
     */
    @TableField(value = "ad_id")
    private Integer adId;

    /**
     * 广告内部单元序列号
     */
    @TableField(value = "serial_id")
    private Integer serialId;

    /**
     * 内容单元类型 1图片 2视频 3推广链接
     */
    @TableField(value = "struct_type")
    private Integer structType;

    /**
     * 广告标题
     */
    @TableField(value = "struct_content")
    private String structContent;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created")
    private Date gmtCreated;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}