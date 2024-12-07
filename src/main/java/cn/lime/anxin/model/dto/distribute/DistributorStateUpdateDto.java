package cn.lime.anxin.model.dto.distribute;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * @ClassName: DistributorStateUpdateDto
 * @Description: TODO 描述类的功能
 * @Author: riang
 * @Date: 2024/11/2 10:01
 */
@Data
public class DistributorStateUpdateDto implements Serializable {

    @Schema(description = "1更新某分销商是否冻结 2更新某分销商下用户和分销商的关系是否冻结")
    @Range(min = 1,max = 2,message = "参数错误，1更新某分销商是否冻结 2更新某分销商下用户和分销商的关系是否冻结")
    private Integer type;
    @Schema(description = "状态 0未冻结 1冻结")
    @Range(min = 0,max = 1,message = "状态 0未冻结 1冻结")
    private Integer isFreeze;
    @Schema(description = "用户ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull
    private Long userId;

}