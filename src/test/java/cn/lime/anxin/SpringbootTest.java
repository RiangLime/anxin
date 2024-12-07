package cn.lime.anxin;

import cn.lime.anxin.service.db.distribute.DistributeUserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: SpringbootTest
 * @Description: TODO 描述类的功能
 * @Author: riang
 * @Date: 2024/11/6 19:03
 */
@SpringBootTest
public class SpringbootTest {

    @Resource
    private DistributeUserService service;

//    @Test
    public void test(){
        service.getUserInfo(1050595799223898112L,1,10);
    }

}