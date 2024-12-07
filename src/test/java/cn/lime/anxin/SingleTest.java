package cn.lime.anxin;

import cn.lime.anxin.utils.DetectOrderCodeGenerator;
import cn.lime.core.aes.AesUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

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
        Instant now = Instant.now();
        System.out.println(Date.from(now));
    }

}
