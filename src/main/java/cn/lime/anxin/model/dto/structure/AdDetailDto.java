package cn.lime.anxin.model.dto.structure;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: AdDetailDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDetailDto implements Serializable {
    @Schema(description = "广告ID")
    @NotNull
    private Integer adId;
}
