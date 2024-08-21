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
        // 获取当前时间戳，精确到毫秒
        long timestamp = System.currentTimeMillis();

        // 获取时间戳的后8位
        String timestampStr = String.valueOf(timestamp).substring(5);

        // 生成一个4位随机数
        Random random = new Random();
        int randomNum = random.nextInt(9000) + 1000; // 保证是4位数

        // 组合时间戳和随机数
        String code = timestampStr + randomNum;

        // 格式化成每4位用-隔开的形式
        return code.substring(0, 4) + "-" + code.substring(4, 8) + "-" + code.substring(8, 12);
    }
}
