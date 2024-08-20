package cn.lime.anxin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: QrCodeVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 17:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeVo implements Serializable {
    private String url;
    private String code;

}
