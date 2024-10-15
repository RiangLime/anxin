package cn.lime.anxin.model.dto.distribute;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributorUpstreamUpdateDto implements Serializable {
    @Schema(description = "目标用户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    @Schema(description = "邀请人ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long inviterId;
}
