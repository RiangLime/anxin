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
        Long a = 1023242928052637696L;
        String base64 = Base64.getEncoder().encodeToString(String.valueOf(a).getBytes());
        System.out.println(base64);
    }

}
