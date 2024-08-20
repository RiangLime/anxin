package cn.lime.anxin.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AnXinParams
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:57
 */
@Component
@Data
public class AnXinParams {

    @Value("${anxin.qrcode-prefix:}")
    private String qrCodePrefix;

}
