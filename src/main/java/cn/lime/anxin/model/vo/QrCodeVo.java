package cn.lime.anxin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: QrCodeVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 17:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeVo implements Serializable {
    @Schema(description = "二维码对应URL")
    private String url;
    @Schema(description = "12位码")
    private String code;
}
