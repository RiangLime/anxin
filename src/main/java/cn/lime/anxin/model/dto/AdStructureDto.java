package cn.lime.anxin.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AdStructureDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 15:46
 */
@Data
public class AdStructureDto implements Serializable {

    private String structureContent;
    private Integer structureType;

}
