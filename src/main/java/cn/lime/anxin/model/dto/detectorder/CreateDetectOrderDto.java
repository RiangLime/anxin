package cn.lime.anxin.model.dto.detectorder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: CreateDetectOrderDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/21 14:41
 */
@Data
public class CreateDetectOrderDto implements Serializable {
    @Schema(description = "商品ID")
    @NotNull
    private Long productId;
    @Schema(description = "SKU ID")
    @NotNull
    private Long skuId;
    @Schema(description = "关联商城订单ID")
    private Long relateOrderId;
}
