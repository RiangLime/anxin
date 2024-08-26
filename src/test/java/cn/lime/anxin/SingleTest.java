package cn.lime.anxin;

import org.junit.jupiter.api.Test;

import java.util.Base64;

/**
 * @ClassName: SingleTest
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 14:34
 */
public class SingleTest {

    @Test
    public void test(){
        Integer price = 18000;
        Integer percent  = 23;
        Integer res = price * percent / 100;
        System.out.println(res);
    }

}
