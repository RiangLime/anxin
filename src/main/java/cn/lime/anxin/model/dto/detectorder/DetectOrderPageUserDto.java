package cn.lime.anxin.model.dto.detectorder;

import cn.lime.core.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: OrderPageDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/21 18:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DetectOrderPageUserDto extends PageRequest implements Serializable {
    @Schema(description = "商品名称")
    private String productName;
    @Schema(description = "12位码")
    private String code;
    @Schema(description = "检测状态 0待采样 1回寄中 2待检测 3检测中 4已出报告")
    private Integer state;
    @Schema(description = "是否可以升级 0不可 1可以")
    private Integer canUpdate;
    @Schema(description = "是否为升级检测")
    private Integer isUpdated;
}
