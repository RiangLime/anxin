package cn.lime.anxin.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: AdUpdateDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:32
 */
@Data
public class AdUpdateDto implements Serializable {
    private Integer adId;
    private String title;
    private String picture;
    private List<AdStructureDto> structures;
}
