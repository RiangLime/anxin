package cn.lime.anxin.model.dto.distribute;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: ProductSkuIdDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 16:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuIdDto implements Serializable {
    @Schema(description = "产品ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "产品ID")
    private Long productId;
    @Schema(description = "SKU ID 可为空，如果为空则该产品下所有SKU参与分销")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long skuId;
}
