package cn.lime.anxin.model.dto.order;

import cn.lime.mall.model.dto.order.OrderItemDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: OrderCreateByUpdateReportDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/22 14:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateByUpdateReportDto implements Serializable {
    @Schema(description = "地址ID")
    private Integer addressId;
    @Schema(description = "商品ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull
    private Long productId;
    @Schema(description = "SKU ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull
    private Long skuId;
    @Schema(description = "购买数量")
    @NotNull
    @Min(value = 1, message = "数量必须大于等于1")
    private Integer number;
    @Schema(description = "前置二维码")
    @NotNull
    private String preCode;
    @Schema(description = "用户备注")
    private String remark;
}
