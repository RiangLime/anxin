package cn.lime.anxin.controller;

import cn.lime.anxin.model.dto.user.WxEasyLoginInviteDto;
import cn.lime.anxin.service.db.base.DistributeRelationService;
import cn.lime.core.annotation.AuthCheck;
import cn.lime.core.annotation.DtoCheck;
import cn.lime.core.annotation.RequestLog;
import cn.lime.core.common.BaseResponse;
import cn.lime.core.common.ResultUtils;
import cn.lime.core.module.vo.LoginVo;
import cn.lime.core.service.login.UniLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
    private DistributeRelationService distributeRelationService;

    @PostMapping("/easylogin/wx/invite")
    @Operation(summary = "一键登录 微信")
    @DtoCheck(checkBindResult = true)
    @AuthCheck(needPlatform = true)
    public BaseResponse<LoginVo> easyLoginWx(@Valid @RequestBody WxEasyLoginInviteDto request, BindingResult result){
        LoginVo loginVo = uniLogService.easyLogin(request);
        if (loginVo.getIsNew()){
            distributeRelationService.addRelation(request.getInviteUserCode(),loginVo.getUserId());
        }
        return ResultUtils.success(loginVo);
    }

}
