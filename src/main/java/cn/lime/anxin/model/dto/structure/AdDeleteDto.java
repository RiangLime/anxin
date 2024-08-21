package cn.lime.anxin.model.dto.structure;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AdDeleteDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:33
 */
@Data
public class AdDeleteDto implements Serializable {
    @Schema(description = "广告ID")
    @NotNull
    private Integer adId;
}
