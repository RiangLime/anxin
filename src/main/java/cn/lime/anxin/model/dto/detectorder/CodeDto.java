package cn.lime.anxin.model.dto.detectorder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: BindUserDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/21 14:49
 */
@Data
public class CodeDto implements Serializable {
    @Schema(description = "十二位编码")
    @NotNull
    private String code;
}
