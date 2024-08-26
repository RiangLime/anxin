package cn.lime.anxin.controller;

import cn.lime.anxin.model.dto.distribute.DistributeApplyPageDto;
import cn.lime.anxin.model.dto.distribute.DistributeWithdrawApplyDto;
import cn.lime.anxin.model.dto.distribute.DistributorApplyDto;
import cn.lime.anxin.model.dto.distribute.DistributeWithdrawPageDto;
import cn.lime.anxin.model.vo.distribute.ApplicationPageVo;
import cn.lime.anxin.model.vo.distribute.DistributeWithdrawVo;
import cn.lime.anxin.service.db.distribute.DistributeApplicationService;
import cn.lime.anxin.service.db.distribute.DistributeWithdrawService;
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
 * @ClassName: DistributeController
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 15:10
 */
@RestController
@RequestMapping("/distribute")
@Tag(name = "分销系统接口")
@CrossOrigin(origins = "*")
@RequestLog
public class DistributeController {

    @Resource
    private DistributeApplicationService applicationService;
    @Resource
    private DistributeWithdrawService withdrawService;

    @PostMapping("/distributor/apply")
    @Operation(summary = "用户申请成为经销商")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true, authLevel = AuthLevel.USER)
    public BaseResponse<Void> applyToBeDistributor(@Valid @RequestBody DistributorApplyDto dto, BindingResult result) {
        applicationService.apply(ReqThreadLocal.getInfo().getUserId(), dto.getRealName(), dto.getPhone(), dto.getRegion(), dto.getReason());
        return ResultUtils.success(null);
    }

    @PostMapping("/distributor/apply/page")
    @Operation(summary = "用户查看经销商申请信息")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true, authLevel = AuthLevel.USER)
    public BaseResponse<PageResult<ApplicationPageVo>> listDistributorApplyPage(@Valid @RequestBody DistributeApplyPageDto dto, BindingResult result) {
        PageResult<ApplicationPageVo> vo = applicationService.pageApplications(ReqThreadLocal.getInfo().getUserId(),
                null, null, null, null, null, dto.getCurrent(), dto.getPageSize());
        return ResultUtils.success(vo);
    }


    @PostMapping("/withdraw/apply")
    @Operation(summary = "用户申请佣金提现")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true, authLevel = AuthLevel.USER)
    public BaseResponse<Void> applyWithdraw(@Valid @RequestBody DistributeWithdrawApplyDto dto, BindingResult result) {
        withdrawService.applyWithdraw(ReqThreadLocal.getInfo().getUserId(), dto.getAmount());
        return ResultUtils.success(null);
    }

    @PostMapping("/withdraw/apply/page")
    @Operation(summary = "用户佣金提现申请分页查询")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true, authLevel = AuthLevel.USER)
    public BaseResponse<PageResult<DistributeWithdrawVo>> pageWithdraw(@Valid @RequestBody DistributeWithdrawPageDto dto, BindingResult result) {
        PageResult<DistributeWithdrawVo> vo = withdrawService.pageWithDraw(ReqThreadLocal.getInfo().getUserId(),
                dto.getState(), dto.getPriceStart(), dto.getPriceEnd(), dto.getCreateTimeStart(), dto.getCreateTimeEnd(),
                dto.getCurrent(), dto.getPageSize(), null, null);
        return ResultUtils.success(vo);
    }

}
