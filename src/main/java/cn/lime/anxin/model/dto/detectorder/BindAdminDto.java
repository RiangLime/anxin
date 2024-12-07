package cn.lime.anxin.model.dto.detectorder;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: BindAdminDto
 * @Description: TODO 描述类的功能
 * @Author: riang
 * @Date: 2024/12/4 15:41
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BindAdminDto implements Serializable {

    @Schema(description = "十二位编码")
    @NotNull
    private String code;
    @Schema(description = "要绑定是用户ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

}