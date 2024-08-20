package cn.lime.anxin.model.vo;

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
    private Integer adId;
    private String adTitle;
    private String adPicture;
    private Long adCreateTime;
    private Integer adViewCount;

    private List<AdStructureVo> structures;
}
