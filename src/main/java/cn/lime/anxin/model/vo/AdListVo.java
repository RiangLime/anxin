package cn.lime.anxin.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AdListVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 15:49
 */
@Data
public class AdListVo implements Serializable {
    private Integer adId;
    private String adTitle;
    private String adPicture;
    private Long adCreateTime;
    private Integer adViewCount;
}
