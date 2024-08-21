package cn.lime.anxin.model.dto.detectorder;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: CodeIdDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/21 18:19
 */
@Data
public class CodeIdDto implements Serializable {
    @Schema(description = "检测ID 序列化为String")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
}
