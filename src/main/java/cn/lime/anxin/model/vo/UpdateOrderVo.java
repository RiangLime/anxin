package cn.lime.anxin.model.vo;

import cn.lime.mall.model.vo.OrderDetailVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: UpdateOrderVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/22 15:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderVo implements Serializable {
    @Schema(description = "订单详情")
    private OrderDetailVo orderVo;
    @Schema(description = "根据前置二维码生成的新二维码信息")
    private QrCodeVo qrCodeVo;
}
