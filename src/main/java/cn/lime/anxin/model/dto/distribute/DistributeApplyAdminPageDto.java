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
 * @ClassName: DistributeApplyAdminPageDto
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 15:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DistributeApplyAdminPageDto extends PageRequest implements Serializable {
    @Schema(description = "用户ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    @Schema(description = "用户区域")
    private String region;
    @Schema(description = "申请时间范围 开始时间 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long applyTimeStart;
    @Schema(description = "申请时间范围 结束时间 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long applyTimeEnd;
    @Schema(description = "申请状态 0待申请 1已通过 2已拒绝")
    private Integer state;
    @Schema(description = "模糊查询字段 用户昵称/用户账号/用户真实姓名")
    private String queryField;
}
