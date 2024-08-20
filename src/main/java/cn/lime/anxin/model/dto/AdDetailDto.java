package cn.lime.anxin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: AdDetailDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDetailDto implements Serializable {
    private Integer adId;
}
