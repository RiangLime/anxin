package cn.lime.anxin.model.dto.detectorder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SetReturnDeliverDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/21 14:54
 */
@Data
public class SetReturnDeliverDto implements Serializable {
    @Schema(description = "十二位码")
    @NotNull
    private String code;
    @Schema(description = "快递公司")
    private String deliverCompany;
    @Schema(description = "快递号")
    @NotNull
    private String deliverId;
}
