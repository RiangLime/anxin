package cn.lime.anxin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AdStructureVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:10
 */
@Data
public class AdStructureVo implements Serializable {
    @Schema(description = "内部单元序列号")
    private Integer serialId;
    @Schema(description = "内部单元类型 1文字 2图片 3视频 4音频 5推广链接")
    private Integer type;
    @Schema(description = "内容")
    private String content;
}
