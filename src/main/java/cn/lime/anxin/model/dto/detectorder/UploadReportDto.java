package cn.lime.anxin.model.dto.detectorder;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: UploadReportDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/21 14:56
 */
@Data
public class UploadReportDto {
    @Schema(description = "十二位码")
    @NotNull
    private String code;
    @Schema(description = "报告标题")
    private String title;
    @Schema(description = "报告名称")
    private String name;
    @Schema(description = "是否正常 1正常 0异常 2存疑")
    private Integer detectResult = 1;

    @Schema(description = "报告是否能升级 默认0不可 ")
    private Integer canUpdate = 0;
    @Schema(description = "升级对应的商品ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long updateProductId;
    @Schema(description = "升级对应的商品SKU ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long updateSkuId;

    @Schema(description = "报告图片URL")
    @NotNull
    private List<String> reportUrls;
    @Schema(description = "联系人图片URL")
    private List<String> contactorUrls;
}
