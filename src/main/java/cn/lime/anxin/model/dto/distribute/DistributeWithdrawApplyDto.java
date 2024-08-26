package cn.lime.anxin.model.dto.distribute;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * @ClassName: DistributeWithdrawApplyDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 16:24
 */
@Data
public class DistributeWithdrawApplyDto implements Serializable {
    @Schema(description = "用户申请提现金额")
    @NotNull(message = "申请金额不可为空")
    @Min(value = 1,message = "申请金额不可为0")
    private Integer amount;
}
