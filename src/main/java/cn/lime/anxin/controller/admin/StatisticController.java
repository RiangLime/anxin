package cn.lime.anxin.controller.admin;

import cn.lime.anxin.model.vo.StatisticVo;
import cn.lime.anxin.service.db.extend.StatisticService;
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

@RestController
@RequestMapping("/admin/statistic")
@Tag(name = "数据分析接口[管理员]")
@CrossOrigin(origins = "*")
@RequestLog
public class StatisticController {

    @Resource
    private StatisticService service;

    @PostMapping("/get")
    @Operation(summary = "管理员查询统计信息")
    @AuthCheck(needToken = true,authLevel = AuthLevel.ADMIN)
    @DtoCheck(checkBindResult = true)
    public BaseResponse<StatisticVo> getStatistic(@RequestBody @Valid EmptyDto dto, BindingResult result){
        return ResultUtils.success(service.getStatistic());
    }

}
