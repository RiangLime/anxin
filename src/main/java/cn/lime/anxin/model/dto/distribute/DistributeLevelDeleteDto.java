package cn.lime.anxin.model.dto.distribute;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: DistributeLevelDeleteDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 16:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributeLevelDeleteDto implements Serializable {
    @Schema(description = "分销等级")
    @NotNull(message = "Level不可为空")
    private Integer level;
}
