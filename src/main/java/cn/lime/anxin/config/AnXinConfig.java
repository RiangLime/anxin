package cn.lime.anxin.config;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: AppConfig
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/7/31 11:07
 */
@Configuration
@ComponentScan(basePackages = {"cn.lime.core","cn.lime.mall"})
public class AnXinConfig {
}
