package cn.lime.anxin.model.dto.distribute;

import cn.lime.mall.model.dto.product.ProductPageAdminDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: DistributeProductPageDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 16:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DistributeProductPageDto extends ProductPageAdminDto {
    @Schema(description = "是否为分销商品 0不是1是")
    private Integer distributeState;
}
