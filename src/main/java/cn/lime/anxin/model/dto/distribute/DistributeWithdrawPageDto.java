package cn.lime.anxin.model.dto.distribute;

import cn.lime.core.common.PageRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: WithdrawPageDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 16:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DistributeWithdrawPageDto extends PageRequest implements Serializable {
    @Schema(description = "提现申请状态 0已申请 1已通过 2已拒绝")
    private Integer state;
    @Schema(description = "查询申请金额范围 开始金额")
    private Integer priceStart;
    @Schema(description = "查询申请金额范围 结束金额")
    private Integer priceEnd;
    @Schema(description = "查询申请时间范围 开始时间 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long createTimeStart;
    @Schema(description = "查询申请时间范围 结束时间 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long createTimeEnd;
}
