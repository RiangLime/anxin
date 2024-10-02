package cn.lime.anxin.controller;

import cn.lime.anxin.model.dto.detectorder.*;
import cn.lime.anxin.model.vo.DetectOrderDetailVo;
import cn.lime.anxin.model.vo.DetectOrderPageVo;
import cn.lime.anxin.model.vo.QrCodeVo;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: DetectOrderController
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/21 14:34
 */
@RestController
@RequestMapping("/detectorder")
@Tag(name = "二维码/报告管理相关接口")
@CrossOrigin(origins = "*")
@RequestLog
public class DetectOrderController {

    @Resource
    private DetectorderService service;

    @PostMapping("/create")
    @Operation(summary = "生成二维码")
    @AuthCheck(needToken = true,authLevel = AuthLevel.USER)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<QrCodeVo> createDetectOrder(@RequestBody @Valid CreateDetectOrderDto dto, BindingResult result) {
        return ResultUtils.success(service.createDetectOrder(dto.getProductId(),dto.getSkuId(),dto.getRelateOrderId()));
    }

    @PostMapping("/bind")
    @Operation(summary = "用户绑定")
    @AuthCheck(needToken = true,authLevel = AuthLevel.USER)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<Void> bindUser(@RequestBody @Valid CodeDto dto, BindingResult result) {
        service.bind(dto.getCode());
        return ResultUtils.success(null);
    }

    @PostMapping("/return")
    @Operation(summary = "用户确定采样完成")
    @AuthCheck(needToken = true,authLevel = AuthLevel.USER)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<Void> confirmSampled(@RequestBody @Valid CodeDto dto, BindingResult result) {
        service.confirmReadyToReturn(dto.getCode());
        return ResultUtils.success(null);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询检测数据")
    @AuthCheck(needToken = true,authLevel = AuthLevel.USER)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<PageResult<DetectOrderPageVo>> page(@RequestBody @Valid DetectOrderPageUserDto dto, BindingResult result) {
        return ResultUtils.success(service.pageDetectOrders(ReqThreadLocal.getInfo().getUserId(),null,
                dto.getProductName(), dto.getCode(),dto.getState(),dto.getCanUpdate(),dto.getIsUpdated(),
                dto.getCurrent(),dto.getPageSize()));
    }

    @PostMapping("/detail")
    @Operation(summary = "查询检测数据详情")
    @AuthCheck(needToken = true,authLevel = AuthLevel.USER)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<DetectOrderDetailVo> detail(@RequestBody @Valid CodeIdDto dto, BindingResult result) {
        return ResultUtils.success(service.getDetectOrderDetail(dto.getId()));
    }

    @PostMapping("/setreturninfo")
    @Operation(summary = "用户一键回寄")
    @AuthCheck(needToken = true,authLevel = AuthLevel.USER)
    @DtoCheck(checkBindResult = true)
    @Transactional
    public BaseResponse<Void> userEasyReturn(@RequestBody @Valid UserSetReturnDeliverInfoDto dto, BindingResult result) {
        service.userSetReturnDeliverInfo(dto.getCode(),dto.getReturnDeliverUserName(),dto.getReturnDeliverUserPosition(),
                dto.getReturnDeliverUserAddress(),dto.getReturnDeliverUserPhone(),dto.getReturnDeliverUserAge(),
                dto.getReturnDeliverVisitTime());
        service.confirmReadyToReturn(dto.getCode());
        return ResultUtils.success(null);
    }

}
