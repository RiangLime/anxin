package cn.lime.anxin;

import cn.lime.anxin.utils.DetectOrderCodeGenerator;
import cn.lime.core.aes.AesUtils;
import lombok.SneakyThrows;
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
    @SneakyThrows
    public void test(){
        AesUtils aesUtils = new AesUtils("1234123412341234","1234123412341234");
        System.out.println(aesUtils.encrypt("admin888"));
        String a = DetectOrderCodeGenerator.generateUniqueCode();
        System.out.println(a);
    }

}
