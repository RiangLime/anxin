package cn.lime.anxin.controller.admin;

import cn.lime.anxin.model.dto.AdAddDto;
import cn.lime.anxin.model.dto.AdDeleteDto;
import cn.lime.anxin.model.dto.AdUpdateDto;
import cn.lime.anxin.model.dto.HomePageUpdateDto;
import cn.lime.anxin.model.vo.AdListVo;
import cn.lime.anxin.service.db.base.AdvertisementService;
import cn.lime.anxin.service.db.base.HomepagestructureService;
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

import java.util.List;

/**
 * @ClassName: StructureAdminController
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:27
 */
@RestController
@RequestMapping("/admin/structure")
@Tag(name = "小程序结构相关接口[首页/推广]")
@CrossOrigin(origins = "*")
@RequestLog
public class StructureAdminController {
    @Resource
    private AdvertisementService adService;
    @Resource
    private HomepagestructureService homepageService;

    @PostMapping("/ad/add")
    @Operation(summary = "添加广告")
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<Void> addAd(@RequestBody @Valid AdAddDto dto, BindingResult result) {
        adService.addAd(dto.getTitle(),dto.getPicture(),dto.getStructures());
        return ResultUtils.success(null);
    }

    @PostMapping("/ad/update")
    @Operation(summary = "修改广告")
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<Void> updateAd(@RequestBody @Valid AdUpdateDto dto, BindingResult result) {
        adService.updateAd(dto.getAdId(),dto.getTitle(),dto.getPicture(),dto.getStructures());
        return ResultUtils.success(null);
    }

    @PostMapping("/ad/delete")
    @Operation(summary = "删除广告")
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<Void> deleteAd(@RequestBody @Valid AdDeleteDto dto, BindingResult result) {
        adService.deleteAd(dto.getAdId());
        return ResultUtils.success(null);
    }

    @PostMapping("/homepage/update")
    @Operation(summary = "更新首页结构")
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<Void> updateHomepage(@RequestBody @Valid HomePageUpdateDto dto, BindingResult result) {
        homepageService.addVersion(dto.getVersion(),dto.getHomepageStructure());
        return ResultUtils.success(null);
    }
}
