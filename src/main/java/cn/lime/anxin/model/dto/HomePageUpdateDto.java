package cn.lime.anxin.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: HomePageUpdateDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:36
 */
@Data
public class HomePageUpdateDto implements Serializable {
    String version;
    String homepageStructure;
}
