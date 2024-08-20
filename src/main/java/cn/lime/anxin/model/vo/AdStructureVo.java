package cn.lime.anxin.model.vo;

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
    private Integer serialId;
    private Integer type;
    private String content;
}
