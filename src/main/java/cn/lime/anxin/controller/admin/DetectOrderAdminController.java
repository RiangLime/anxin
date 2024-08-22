package cn.lime.anxin.controller.admin;

import cn.lime.anxin.model.dto.detectorder.*;
import cn.lime.anxin.model.vo.DetectOrderPageVo;
import cn.lime.anxin.service.db.base.DetectorderService;
import cn.lime.core.annotation.AuthCheck;
import cn.lime.core.annotation.DtoCheck;
import cn.lime.core.annotation.RequestLog;
import cn.lime.core.common.BaseResponse;
import cn.lime.core.common.PageResult;
import cn.lime.core.common.ResultUtils;
import cn.lime.core.constant.AuthLevel;
import cn.lime.core.threadlocal.ReqThreadLocal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: DetectOrderAdminController
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/21 15:00
 */
@RestController
@RequestMapping("/admin/detectorder")
@Tag(name = "二维码/报告管理相关接口[管理员]")
@CrossOrigin(origins = "*")
@RequestLog
public class DetectOrderAdminController {

    @Resource
    private DetectorderService service;

    @PostMapping("/setreturninfo")
    @Operation(summary = "管理设置回寄单号")
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<Void> updateReturnInfo(@RequestBody @Valid SetReturnDeliverDto dto, BindingResult result) {
        service.setReturnDeliverInfo(dto.getCode(),dto.getDeliverCompany(),dto.getDeliverId());
        return ResultUtils.success(null);
    }

    @PostMapping("/finishreturn")
    @Operation(summary = "管理员确定收到回寄")
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<Void> confirmReceiveSampled(@RequestBody @Valid CodeDto dto, BindingResult result) {
        service.confirmReceiveReturn(dto.getCode());
        return ResultUtils.success(null);
    }

    @PostMapping("/uploadreport")
    @Operation(summary = "管理员上传报告")
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<Void> uploadReport(@RequestBody @Valid UploadReportDto dto, BindingResult result) {
        service.uploadReport(dto.getCode(),dto.getTitle(),dto.getName(),dto.getIsNormal(),dto.getCanUpdate(),
                dto.getUpdateProductId(),dto.getUpdateSkuId(),dto.getReportUrls(),dto.getContactorUrls());
        return ResultUtils.success(null);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询检测数据")
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<PageResult<DetectOrderPageVo>> page(@RequestBody @Valid DetectOrderPageAdminDto dto, BindingResult result) {
        return ResultUtils.success(service.pageDetectOrders(dto.getUserId(), dto.getUserName(), dto.getProductName(),
                dto.getCode(),dto.getState(),dto.getCanUpdate(),dto.getIsUpdated(),dto.getCurrent(),dto.getPageSize()));
    }
}
