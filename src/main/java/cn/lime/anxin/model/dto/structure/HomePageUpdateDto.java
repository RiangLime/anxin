package cn.lime.anxin.model.dto.structure;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: HomePageUpdateDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:36
 */
@Data
public class HomePageUpdateDto implements Serializable {
    @Schema(description = "版本")
    private String version;
    @Schema(description = "主页结构")
    @NotNull
    private String homepageStructure;
}
