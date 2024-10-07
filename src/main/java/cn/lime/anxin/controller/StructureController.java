package cn.lime.anxin.controller;

import cn.lime.anxin.model.dto.structure.AdDetailDto;
import cn.lime.anxin.model.entity.Homepagestructure;
import cn.lime.anxin.model.vo.AdDetailVo;
import cn.lime.anxin.model.vo.AdListVo;
import cn.lime.anxin.service.db.base.AdvertisementService;
import cn.lime.anxin.service.db.base.HomepagestructureService;
import cn.lime.core.annotation.AuthCheck;
import cn.lime.core.annotation.DtoCheck;
import cn.lime.core.annotation.RequestLog;
import cn.lime.core.common.BaseResponse;
import cn.lime.core.common.ResultUtils;
import cn.lime.core.config.CoreParams;
import cn.lime.core.constant.AuthLevel;
import cn.lime.core.module.dto.EmptyDto;
import cn.lime.core.service.wx.auth.WxMpOuterService;
import cn.lime.core.threadlocal.ReqThreadLocal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: StructureController
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:20
 */
@RestController
@RequestMapping("/structure")
@Tag(name = "小程序结构相关接口[首页/推广]")
@CrossOrigin(origins = "*")
@RequestLog
public class StructureController {

    @Resource
    private AdvertisementService adService;
    @Resource
    private HomepagestructureService homepageService;
    @Resource
    private WxMpOuterService wxMpOuterService;
    @Resource
    private CoreParams coreParams;

    @PostMapping("/ad/list")
    @Operation(summary = "查询所有推广广告")
    @AuthCheck(authLevel = AuthLevel.TOURIST)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<List<AdListVo>> listAds(@RequestBody @Valid EmptyDto dto, BindingResult result) {
        return ResultUtils.success(adService.listAds());
    }

    @PostMapping("/ad/detail")
    @Operation(summary = "查询推广广告详情")
    @AuthCheck(authLevel = AuthLevel.TOURIST)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<AdDetailVo> getAdDetail(@RequestBody @Valid AdDetailDto dto, BindingResult result) {
        return ResultUtils.success(adService.getAdDetail(dto.getAdId()));
    }

    @PostMapping("/homepage/latest")
    @Operation(summary = "查询最新的首页结构")
    @AuthCheck(authLevel = AuthLevel.TOURIST)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<Homepagestructure> getLatestHomePage(@RequestBody @Valid EmptyDto dto, BindingResult result) {
        return ResultUtils.success(homepageService.getLatest());
    }

    @PostMapping("/getshareqrcode")
    @Operation(summary = "获取推广二维码")
    @AuthCheck(needToken = true,authLevel = AuthLevel.USER)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<String> getShareQrCode(@RequestBody @Valid EmptyDto dto, BindingResult result) {
        String base64Code = wxMpOuterService.getShareCode(coreParams.getWxMpAppId(),coreParams.getWxMpSecretId(),
                "pages/index/index","userId="+ ReqThreadLocal.getInfo().getUserId());
        return ResultUtils.success(base64Code);
    }

}
