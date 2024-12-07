package cn.lime.anxin.model.dto.distribute;

import cn.lime.core.common.PageRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName: UserRelateDistributeOrderDto
 * @Description: TODO 描述类的功能
 * @Author: riang
 * @Date: 2024/11/2 10:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRelateDistributeOrderDto extends PageRequest implements Serializable {
    @Schema(description = "用户ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
}