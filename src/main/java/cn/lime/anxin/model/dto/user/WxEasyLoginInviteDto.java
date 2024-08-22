package cn.lime.anxin.model.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: WxEasyLoginDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/22 16:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxEasyLoginInviteDto extends cn.lime.core.module.dto.unidto.WxEasyLoginDto implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(description = "邀请用户ID 序列化为String")
    private Long inviteUserId;
}
