package cn.lime.anxin;

import cn.lime.anxin.utils.DetectOrderCodeGenerator;
import cn.lime.core.aes.AesUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
//        String a = DetectOrderCodeGenerator.generateUniqueCode();
//        System.out.println(a);
        String a = null;
        String b = "";
        System.out.println(StringUtils.isBlank(a));

        System.out.println(StringUtils.isNoneEmpty(b));
        System.out.println(b == null);
        System.out.println(b.length());
    }

}
