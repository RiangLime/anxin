package cn.lime.anxin.model.dto.detectorder;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: SendQrcodeOnlineDto
 * @Description: TODO 描述类的功能
 * @Author: riang
 * @Date: 2024/10/25 13:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendQrcodeOnlineDto implements Serializable {

    @Schema(description = "订单ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long orderId;
    @Schema(description = "十二位码")
    private String qrCode;

}