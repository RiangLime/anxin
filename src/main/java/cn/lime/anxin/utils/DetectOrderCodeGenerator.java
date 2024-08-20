package cn.lime.anxin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName: DetectOrderCodeGenerator
 * @Description: TODO
 * @Author: Lime
 * @Date: 2024/8/20 16:48
 */
public class DetectOrderCodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    public static String generateUniqueCode() {
        // 获取当前时间戳并格式化为yyyyMMddHHmmssSSS
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        // 获取时间戳的最后9位，保证时间戳部分的长度适合插入到编码中
        String timestampPart = timestamp.substring(timestamp.length() - 9);

        // 生成6位随机字符
        StringBuilder randomPart = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            randomPart.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        // 拼接时间戳部分和随机部分
        String fullCode = timestampPart + randomPart.toString();

        // 插入'-'分隔符
        return fullCode.substring(0, 5) + "-" +
                fullCode.substring(5, 10) + "-" +
                fullCode.substring(10, 15);
    }
}
