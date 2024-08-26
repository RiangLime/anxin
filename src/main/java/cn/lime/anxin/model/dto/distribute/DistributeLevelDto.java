package cn.lime.anxin.model.dto.distribute;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: DistributeLevelDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 15:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributeLevelDto implements Serializable {

    @Schema(description = "分销等级")
    @NotNull(message = "level不可为空")
    private Integer level;
    @Schema(description = "分销等级名")
    private String name;
    @Schema(description = "自购佣金率 20%->20")
    private Integer selfRate;
    @Schema(description = "一级上级佣金率 20%->20")
    private Integer directSuperiorRate;
    @Schema(description = "二级上级佣金率 20%->20")
    private Integer secondLevelSuperiorRate;

}
