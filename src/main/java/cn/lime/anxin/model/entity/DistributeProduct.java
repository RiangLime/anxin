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
 * @TableName Distribute_Product
 */
@TableName(value ="Distribute_Product")
@Data
public class DistributeProduct implements Serializable {
    /**
     * 分销商品ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 参与分销商品ID
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 参与分销商品SkuId
     */
    @TableField(value = "sku_id")
    private Long skuId;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created")
    private Date gmtCreated;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}