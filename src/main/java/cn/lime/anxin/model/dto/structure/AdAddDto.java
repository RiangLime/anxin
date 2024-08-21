package cn.lime.anxin.model.dto.structure;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: AdAddDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:29
 */
@Data
public class AdAddDto implements Serializable {
    @Schema(description = "广告标题")
    @NotNull
    private String title;
    @Schema(description = "广告图片")
    private String picture;
    @Schema(description = "广告内部结构")
    private List<AdStructureDto> structures;
}
