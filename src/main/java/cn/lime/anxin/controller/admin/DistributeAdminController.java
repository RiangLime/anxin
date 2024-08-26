package cn.lime.anxin.controller.admin;

import cn.lime.anxin.model.dto.distribute.*;
import cn.lime.anxin.model.vo.distribute.ApplicationPageVo;
import cn.lime.anxin.model.vo.distribute.DistributeLevelVo;
import cn.lime.anxin.model.vo.distribute.ProductWithDistributeTagPageVo;
import cn.lime.anxin.service.db.distribute.DistributeApplicationService;
import cn.lime.anxin.service.db.distribute.DistributeLevelService;
import cn.lime.anxin.service.db.distribute.DistributeProductService;
import cn.lime.core.annotation.AuthCheck;
import cn.lime.core.annotation.DtoCheck;
import cn.lime.core.annotation.RequestLog;
import cn.lime.core.common.BaseResponse;
import cn.lime.core.common.PageResult;
import cn.lime.core.common.ResultUtils;
import cn.lime.core.constant.AuthLevel;
import cn.lime.core.module.dto.EmptyDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: DistributeAdminController
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/26 15:10
 */
@RestController
@RequestMapping("/admin/distribute")
@Tag(name = "分销系统接口 管理员")
@CrossOrigin(origins = "*")
@RequestLog
public class DistributeAdminController {
    @Resource
    private DistributeApplicationService applicationService;
    @Resource
    private DistributeLevelService levelService;
    @Resource
    private DistributeProductService productService;


    @PostMapping("/distributor/review")
    @Operation(summary = "管理员审批经销商申请")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true, authLevel = AuthLevel.ADMIN)
    public BaseResponse<Void> applyToBeDistributor(@Valid @RequestBody DistributeApplyReviewDto dto, BindingResult result) {
        applicationService.reviewApplication(dto.getApplyId(), dto.getIsApprove(), dto.getRemark());
        return ResultUtils.success(null);
    }

    @PostMapping("/distributor/apply/page")
    @Operation(summary = "管理员查看经销商申请信息")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    public BaseResponse<PageResult<ApplicationPageVo>> listDistributorApplyPage(@Valid @RequestBody DistributeApplyAdminPageDto dto, BindingResult result){
        PageResult<ApplicationPageVo> vo = applicationService.pageApplications(dto.getUserId(), dto.getRegion(),dto.getApplyTimeStart(),
                dto.getApplyTimeEnd(), dto.getState(),dto.getQueryField(), dto.getCurrent(),dto.getPageSize());
        return ResultUtils.success(vo);
    }

    @PostMapping("/level/add")
    @Operation(summary = "管理员添加分销等级")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    public BaseResponse<Void> addDistributeLevel(@Valid @RequestBody DistributeLevelDto dto, BindingResult result){
        levelService.addLevel(dto.getLevel(),dto.getName(),dto.getSelfRate(),dto.getDirectSuperiorRate(),dto.getSecondLevelSuperiorRate());
        return ResultUtils.success(null);
    }

    @PostMapping("/level/update")
    @Operation(summary = "管理员更新分销等级信息")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    public BaseResponse<Void> updateDistributeLevel(@Valid @RequestBody DistributeLevelDto dto, BindingResult result){
        levelService.updateLevel(dto.getLevel(),dto.getName(),dto.getSelfRate(),dto.getDirectSuperiorRate(),dto.getSecondLevelSuperiorRate());
        return ResultUtils.success(null);
    }

    @PostMapping("/level/delete")
    @Operation(summary = "管理员删除分销等级")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    public BaseResponse<Void> deleteDistributeLevel(@Valid @RequestBody DistributeLevelDto dto, BindingResult result){
        levelService.deleteLevel(dto.getLevel());
        return ResultUtils.success(null);
    }

    @PostMapping("/level/list")
    @Operation(summary = "管理员查询分销等级列表")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    public BaseResponse<List<DistributeLevelVo>> listDistributeLevel(@Valid @RequestBody EmptyDto dto, BindingResult result){
        return ResultUtils.success(levelService.listLevels());
    }

    @PostMapping("/product/add")
    @Operation(summary = "管理员添加分销商品")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    public BaseResponse<Void> addDistributeProduct(@Valid @RequestBody ProductSkuIdDto dto, BindingResult result){
        productService.addDistributeProducts(dto.getProductId(),dto.getSkuId());
        return ResultUtils.success(null);
    }

    @PostMapping("/product/delete")
    @Operation(summary = "管理员删除分销商品")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    public BaseResponse<Void> deleteDistributeProduct(@Valid @RequestBody ProductSkuIdDto dto, BindingResult result){
        productService.deleteDistributeProducts(dto.getProductId(),dto.getSkuId());
        return ResultUtils.success(null);
    }

    @PostMapping("/product/page")
    @Operation(summary = "管理员查询分销商品分页信息")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    public BaseResponse<PageResult<ProductWithDistributeTagPageVo>> pageDistributeProduct(@Valid @RequestBody DistributeProductPageDto dto, BindingResult result){
        PageResult<ProductWithDistributeTagPageVo> vo = productService.pageDistributeProducts(dto.getProductName(),
                dto.getTagIds(),dto.getProductType(),dto.getProductState(),dto.getDistributeState(),
                dto.getCurrent(),dto.getPageSize(),dto.getSortField(),dto.getSortOrder());
        return ResultUtils.success(vo);
    }

}
