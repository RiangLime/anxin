package cn.lime.anxin.model.vo.distribute;

import cn.lime.mall.model.vo.ProductPageVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ProductWithDistributeTagPageVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/23 14:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductWithDistributeTagPageVo extends ProductPageVo {
    @Schema(description = "是否为分销商品")
    private Integer isDistribute;
}
