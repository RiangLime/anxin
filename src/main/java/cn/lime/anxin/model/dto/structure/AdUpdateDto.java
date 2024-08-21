package cn.lime.anxin.model.dto.structure;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: AdUpdateDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:32
 */
@Data
public class AdUpdateDto implements Serializable {
    @Schema(description = "广告ID")
    @NotNull
    private Integer adId;
    @Schema(description = "广告标题")
    private String title;
    @Schema(description = "广告图片")
    private String picture;
    @Schema(description = "广告内容单元")
    private List<AdStructureDto> structures;
}
