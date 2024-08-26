package cn.lime.anxin.controller.admin;

import cn.lime.anxin.model.dto.detectorder.SetReturnDeliverDto;
import cn.lime.anxin.model.vo.PollingInfoVo;
import cn.lime.anxin.service.db.extend.PollingService;
import cn.lime.core.annotation.AuthCheck;
import cn.lime.core.annotation.DtoCheck;
import cn.lime.core.annotation.RequestLog;
import cn.lime.core.common.BaseResponse;
import cn.lime.core.common.ResultUtils;
import cn.lime.core.constant.AuthLevel;
import cn.lime.core.module.dto.EmptyDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: PollingController
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 18:19
 */

@RestController
@RequestMapping("/admin")
@Tag(name = "轮询接口[管理员]")
@CrossOrigin(origins = "*")
@RequestLog
public class PollingController {
    @Resource
    private PollingService pollingService;

    @PostMapping("/poll")
    @Operation(summary = "轮询接口")
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<PollingInfoVo> updateReturnInfo(@RequestBody @Valid EmptyDto dto, BindingResult result) {
        return ResultUtils.success(pollingService.pollingInfo());
    }
}
