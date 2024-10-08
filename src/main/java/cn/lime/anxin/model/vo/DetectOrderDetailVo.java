package cn.lime.anxin.model.vo;

import cn.lime.mall.model.vo.OrderDetailVo;
import cn.lime.mall.model.vo.SkuAttributeVo;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: DetectOrderDetailVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/21 10:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectOrderDetailVo implements Serializable {
    // 二维码订单信息
    @Schema(description = "检测订单ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @Schema(description = "是否为升级检测")
    private Integer isUpdated;
    @Schema(description = "12位码")
    private String code;
    @Schema(description = "二维码")
    private String qrCode;
    @Schema(description = "检测状态 0待采样 1采样完毕 2回寄中 3升级产品未付款待检测 4待检测 5检测中 6已出报告")
    private Integer state;
    @Schema(description = "创建时间 秒级时间戳 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long createTime;

    @Schema(description = "回寄信息 - 寄件人姓名")
    private String returnDeliverUserName;
    @Schema(description = "回寄信息 - 寄件人省市区")
    private String returnDeliverUserPosition;
    @Schema(description = "回寄信息 - 寄件人详细地址")
    private String returnDeliverUserAddress;
    @Schema(description = "回寄信息 - 寄件人手机号")
    private String returnDeliverUserPhone;
    @Schema(description = "回寄信息 - 检测人年龄")
    private Integer returnDeliverUserAge;
    @Schema(description = "回寄信息 - 回寄上门时间")
    private String returnDeliverVisitTime;


    @Schema(description = "回寄物流公司")
    private String returnDeliverCompany;
    @Schema(description = "回寄物流单号")
    private String returnDeliverCode;

    // 商品信息
    @Schema(description = "商品ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long productId;
    @Schema(description = "商品名称")
    private String productName;
    @Schema(description = "商品主图")
    private String productMainUrl;

    // sku信息
    @Schema(description = "SKU ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long skuId;
    @Schema(description = "SKU属性信息")
    private List<SkuAttributeVo> skuAttributes;
    @Schema(description = "SKU单价")
    private Integer price;

    // 绑定用户信息
    @Schema(description = "绑定用户ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long bindUserId;
    @Schema(description = "绑定用户昵称")
    private String bindUserName;
    @Schema(description = "绑定时间 秒级时间戳 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long bindTime;

    // 报告信息
    @Schema(description = "报告名")
    private String reportName;
    @Schema(description = "报告标题")
    private String reportTitle;
    @Schema(description = "是否正常 1正常 0异常 2存疑")
    private Integer reportResult;
    @Schema(description = "报告上传时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long reportTime;
    @JsonIgnore
    private String dbReportUrlStr;
    @JsonIgnore
    private String dbContactorStr;
    @Schema(description = "报告图URL")
    private List<String> reportUrls;
    @Schema(description = "联系医师图片URL")
    private List<String> contactorUrls;

    @Schema(description = "关联商城订单ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long relateOrderId;
    @Schema(description = "关联商城订单详情信息")
    private OrderDetailVo orderDetailVo;

    @Schema(description = "报告是否能升级")
    private Integer canReportUpdate;
    @Schema(description = "升级报告对应的产品ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long updateProductId;
    @Schema(description = "升级报告对应的SKU ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long updateSkuId;

    public void form(){
        reportUrls = JSONArray.parseArray(dbReportUrlStr,String.class);
        contactorUrls = JSONArray.parseArray(dbContactorStr,String.class);
    }

}
