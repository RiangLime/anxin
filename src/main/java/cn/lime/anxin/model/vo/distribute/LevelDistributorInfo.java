package cn.lime.anxin.model.vo.distribute;

import cn.lime.core.module.vo.UserVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelDistributorInfo implements Serializable {

    @Schema(description = "用户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    @Schema(description = "是否为分销商")
    private Integer isDistributor;

    @Schema(description = "申请成为经销商时 真实姓名")
    private String realName;
    @Schema(description = "申请成为经销商时 联系手机号")
    private String phone;
    @Schema(description = "申请成为经销商时 区域")
    private String region;
    @Schema(description = "申请成为经销商时 申请理由")
    private String reason;
    @Schema(description = "申请成为经销商时 审核通过时间 序列化为String")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long distributorApproveTime;

    private Integer isUpstreamRelationFreeze;
    private Integer isDistributorFreeze;

    private Integer distributorLevel;
    private Integer assetsGet;
    private Integer assetsRemain;

    @Schema(description = "用户信息")
    private UserVo userVo;
    @Schema(description = "下级信息")
    private List<LevelDistributorInfo> downstreamDistributor;

    public LevelDistributorInfo(Long userId) {
        this.userId = userId;
    }
}
