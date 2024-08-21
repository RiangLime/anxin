package cn.lime.anxin.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: AdDetailVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 15:49
 */
@Data
public class AdDetailVo implements Serializable {
    @Schema(description = "广告ID")
    private Integer adId;
    @Schema(description = "类型 1广告")
    private Integer adType;
    @Schema(description = "广告标题")
    private String adTitle;
    @Schema(description = "广告图片")
    private String adPicture;
    @Schema(description = "广告创建时间 秒级时间戳 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long adCreateTime;
    @Schema(description = "广告浏览量")
    private Integer adViewCount;
    @Schema(description = "广告内容")
    private List<AdStructureVo> structures;
}
