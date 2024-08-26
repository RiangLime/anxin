package cn.lime.anxin.model.vo.distribute;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: DistributeWithdrawVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 12:19
 */
@Data
public class DistributeWithdrawVo implements Serializable {
    /**
     * ID
     */
    @Schema(description = "提现ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    /**
     * 申请提现数量
     */
    @Schema(description = "提现金额")
    private Integer price;

    /**
     * 0已提交申请 1已通过 2已拒绝
     */
    @Schema(description = "提现状态 0提交申请 1通过 2拒绝")
    private Integer state;

    /**
     * 审批时间
     */
    @Schema(description = "审批时间 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long reviewTime;

    /**
     * 创建时间
     */
    @Schema(description = "申请提交时间 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long createTime;
}
