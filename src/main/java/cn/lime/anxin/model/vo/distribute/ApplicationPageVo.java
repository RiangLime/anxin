package cn.lime.anxin.model.vo.distribute;

import cn.lime.core.module.vo.UserVo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ApplicationPageVo
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/23 14:42
 */
@Data
public class ApplicationPageVo implements Serializable {
    /**
     * 申请信息
     */
    @Schema(description = "申请ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @Schema(description = "用户ID 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    @Schema(description = "真实姓名")
    private String realName;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "所在区域")
    private String region;
    @Schema(description = "申请理由")
    private String reason;
    @Schema(description = "申请状态")
    private Integer state;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(description = "批准时间 unix时间戳 序列化为String")
    private Long approveTime;
    private String remark;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(description = "申请时间 unix时间戳 序列化为String")
    private Long applyTime;
    /**
     * 用户信息
     */
    private UserVo userVo;
}
