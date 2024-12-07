package cn.lime.anxin.controller;

import cn.lime.anxin.model.dto.user.WxEasyLoginInviteDto;
import cn.lime.anxin.service.db.distribute.DistributeInviteRelationService;
import cn.lime.core.annotation.AuthCheck;
import cn.lime.core.annotation.DtoCheck;
import cn.lime.core.annotation.RequestLog;
import cn.lime.core.common.BaseResponse;
import cn.lime.core.common.ResultUtils;
import cn.lime.core.constant.AuthLevel;
import cn.lime.core.module.dto.user.UserBindPhoneDto;
import cn.lime.core.module.vo.LoginVo;
import cn.lime.core.service.db.UserService;
import cn.lime.core.service.login.UniLogService;
import cn.lime.core.threadlocal.ReqThreadLocal;
import cn.lime.mall.service.db.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: BizUserController
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/22 16:26
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户相关接口")
@CrossOrigin(origins = "*")
@RequestLog
public class BizUserController {

    @Resource
    private UniLogService uniLogService;
    @Resource
    private DistributeInviteRelationService distributeInviteRelationService;
    @Resource
    private UserService userService;
    @Resource
    private DiscountService discountService;

    @PostMapping("/easylogin/wx/invite")
    @Operation(summary = "一键登录 微信 分销商邀请")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needPlatform = true)
    public BaseResponse<LoginVo> easyLoginWx(@Valid @RequestBody WxEasyLoginInviteDto request, BindingResult result){
        LoginVo loginVo = uniLogService.easyLogin(request);
        if (loginVo.getIsNew()){
            distributeInviteRelationService.inviteeRegister(loginVo.getUserId(),request.getInviteUserId());
        }
        return ResultUtils.success(loginVo);
    }

    @PostMapping("/update/mobile/first/givediscount")
    @Operation(summary = "用户首次绑定手机号 返回信息如果有值就是新登录信息")
    @AuthCheck(needToken = true,authLevel = AuthLevel.USER)
    @DtoCheck(checkBindResult = true)
    @Transactional
    public BaseResponse<LoginVo> bindUserMobile(@Valid @RequestBody UserBindPhoneDto request, BindingResult result) {
        discountService.giveUserDiscount(ReqThreadLocal.getInfo().getUserId());
        return ResultUtils.success(userService.bindPhoneByPhoneCode(request.getPhoneCode()));
    }

}
