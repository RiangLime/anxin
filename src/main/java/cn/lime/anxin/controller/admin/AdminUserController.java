package cn.lime.anxin.controller.admin;

import cn.lime.core.annotation.RequestLog;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AdminUserController
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/7/31 14:41
 */
@RestController
@RequestMapping("/admin/user")
@Tag(name = "用户相关接口[管理员]")
@CrossOrigin(origins = "*")
@RequestLog
public class AdminUserController {



}
