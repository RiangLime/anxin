package cn.lime.anxin.model.dto.distribute;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: DistributorApplyDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 15:13
 */
@Data
public class DistributorApplyDto implements Serializable {
    @Schema(description = "真实姓名")
    @NotNull(message = "真实姓名不可为空")
    private String realName;
    @Schema(description = "手机号")
    @NotNull(message = "手机号不可为空")
    private String phone;
    @Schema(description = "所在区域")
    @NotNull(message = "所在区域不可为空")
    private String region;
    @Schema(description = "申请理由")
    @NotNull(message = "申请理由不可为空")
    private String reason;
}
