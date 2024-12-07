package cn.lime.anxin.model.dto.distribute;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributeWithdrawReviewDto implements Serializable {

    @Schema(description = "申请ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long applyId;
    @Schema(description = "是否同意 0不同意 1同意")
    @Range(min = 0,max = 1,message = "非法入参 isApprove 0不通过1通过")
    @NotNull
    private Integer isApprove;

}
