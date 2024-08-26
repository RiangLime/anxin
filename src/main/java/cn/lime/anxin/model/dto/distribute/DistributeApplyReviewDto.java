package cn.lime.anxin.model.dto.distribute;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @ClassName: DistributeApplyReviewDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 15:17
 */
@Data
public class DistributeApplyReviewDto {
    @Schema(description = "申请ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "申请ID不可为空")
    private Long applyId;
    @Schema(description = "是否通过申请 0不通过1通过")
    @NotNull
    @Range(min = 0,max = 1,message = "非法入参 isApprove 0不通过1通过")
    private Integer isApprove;
    @Schema(description = "备注信息")
    private String remark;
}
