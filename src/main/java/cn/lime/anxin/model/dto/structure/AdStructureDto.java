package cn.lime.anxin.model.dto.structure;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AdStructureDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 15:46
 */
@Data
public class AdStructureDto implements Serializable {

    @Schema(description = "广告结构单元内容")
    private String structureContent;
    @Schema(description = "广告结构单元类型 1文字 2图片 3视频 4音频 5推广链接")
    @NotNull
    private Integer structureType;

}
