package cn.lime.anxin.model.dto.detectorder;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSetReturnDeliverInfoDto implements Serializable {

    @Schema(description = "12位码")
    @NotNull(message = "12位码不可为空")
    private String code;
    @Schema(description = "回寄信息 - 寄件人姓名")
    @NotNull(message = "寄件人姓名 不可为空")
    private String returnDeliverUserName;
    @Schema(description = "回寄信息 - 寄件人省区市")
    @NotNull(message = "寄件人省区市 不可为空")
    private String returnDeliverUserPosition;
    @Schema(description = "回寄信息 - 寄件人详细地址")
    @NotNull(message = "寄件人详细地址 不可为空")
    private String returnDeliverUserAddress;
    @Schema(description = "回寄信息 - 寄件人手机号")
    @NotNull(message = "寄件人手机号 不可为空")
    private String returnDeliverUserPhone;
    @Schema(description = "回寄信息 - 检测人年龄")
    @NotNull(message = "检测人年龄 不可为空")
    private Integer returnDeliverUserAge;
    @Schema(description = "回寄信息 - 上门时间 序列化为String unix时间戳")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String returnDeliverVisitTime;
}
