package cn.lime.anxin.model.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: AdAddDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:29
 */
@Data
public class AdAddDto implements Serializable {
    private String title;
    private String picture;
    private List<AdStructureDto> structures;
}
