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
 * @TableName DetectOrder
 */
@TableName(value ="DetectOrder")
@Data
public class Detectorder implements Serializable {
    /**
     * 二维码ID
     */
    @TableId(value = "id")
    private Long id;

    @TableField(value = "is_updated")
    private Integer isUpdated;

    /**
     * 关联的商品 ID
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 关联的SKU ID
     */
    @TableField(value = "sku_id")
    private Long skuId;

    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 数字编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 二维码
     */
    @TableField(value = "qrcode")
    private String qrcode;

    /**
     * 绑定的用户ID
     */
    @TableField(value = "bind_user_id")
    private Long bindUserId;

    /**
     * 用户绑定时间
     */
    @TableField(value = "bind_time")
    private Date bindTime;

    /**
     * 回寄物流公司
     */
    @TableField(value = "return_deliver_company")
    private String returnDeliverCompany;

    /**
     * 回寄物流单号
     */
    @TableField(value = "return_deliver_id")
    private String returnDeliverId;

    /**
     * 检测状态 0待采样 1回寄中 2检测中 3已出报告
     */
    @TableField(value = "detect_state")
    private Integer detectState;

    /**
     * 报告标题
     */
    @TableField(value = "report_title")
    private String reportTitle;

    @TableField(value = "report_name")
    private String reportName;

    /**
     * 是否正常
     */
    @TableField(value = "report_is_normal")
    private Integer reportIsNormal;

    /**
     * 报告时间
     */
    @TableField(value = "report_time")
    private Long reportTime;

    /**
     * 报告URL JSON List
     */
    @TableField(value = "report_url")
    private String reportUrl;

    /**
     * 医师联系方式 JSON List
     */
    @TableField(value = "contactor_url")
    private String contactorUrl;

    @TableField(value = "can_report_update")
    private Integer canReportUpdate;

    @TableField(value = "update_product_id")
    private Long updateProductId;


    @TableField(value = "update_sku_id")
    private Long updateSkuId;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created")
    private Date gmtCreated;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}