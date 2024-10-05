package cn.lime.anxin.controller;

import cn.lime.anxin.model.dto.order.OrderCreateByUpdateReportDto;
import cn.lime.anxin.model.vo.QrCodeVo;
import cn.lime.anxin.model.vo.UpdateOrderVo;
import cn.lime.anxin.service.db.base.DetectorderService;
import cn.lime.core.annotation.AuthCheck;
import cn.lime.core.annotation.DtoCheck;
import cn.lime.core.annotation.RequestLog;
import cn.lime.core.common.BaseResponse;
import cn.lime.core.common.ResultUtils;
import cn.lime.core.constant.AuthLevel;
import cn.lime.core.threadlocal.ReqThreadLocal;
import cn.lime.mall.model.dto.order.OrderCreateDto;
import cn.lime.mall.model.dto.order.OrderItemDto;
import cn.lime.mall.model.entity.Order;
import cn.lime.mall.model.entity.OrderItem;
import cn.lime.mall.model.vo.OrderDetailVo;
import cn.lime.mall.service.db.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/22 14:46
 */
@RestController
@RequestMapping("/order")
@Tag(name = "订单相关接口")
@CrossOrigin(origins = "*")
@RequestLog
public class BizOrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private DetectorderService detectorderService;

    @PostMapping("/create/byupdate")
    @Operation(summary = "用户通过升级报告创建订单")
    @AuthCheck(needToken = true, needPlatform = true, authLevel = AuthLevel.USER)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<UpdateOrderVo> createOrder(@RequestBody @Valid OrderCreateByUpdateReportDto dto, BindingResult result) {
        OrderItemDto orderItem = new OrderItemDto(dto.getProductId(),dto.getSkuId(),dto.getNumber());
        Order order = orderService.createOrder(ReqThreadLocal.getInfo().getUserId(), dto.getAddressId(), List.of(orderItem), dto.getRemark(),dto.getDiscountId());
        OrderDetailVo orderVo = orderService.getOrderDetail(order.getOrderId());
        QrCodeVo qrCodeVo = detectorderService.copyFromDetectOrder(dto.getPreCode(),dto.getProductId(),dto.getSkuId(),orderVo.getOrderId());
        // 创建一个新的检测订单
        return ResultUtils.success(new UpdateOrderVo(orderVo,qrCodeVo));
    }

}
