package cn.lime.anxin.model.dto.structure;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetShareQrCodeDto implements Serializable {

    @Schema(description = "wx小程序路由路径")
    private String wxPage;
    @Schema(description = "checkPath 默认为true")
    private Boolean checkPath = true;
    @Schema(description = "wx场景 release/trail")
    private String wxEnv;

}
