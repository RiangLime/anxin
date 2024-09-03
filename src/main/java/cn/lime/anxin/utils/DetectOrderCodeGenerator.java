package cn.lime.anxin.utils;

import java.security.SecureRandom;
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
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int UUID_LENGTH = 14;
    private static final SecureRandom random = new SecureRandom();

    public static String generateUniqueCode() {
        StringBuilder uuidBuilder = new StringBuilder(UUID_LENGTH);

        for (int i = 1; i <= UUID_LENGTH; i++) {
            if (i%5==0){
                uuidBuilder.append("-");
            }else {
                int index = random.nextInt(CHAR_POOL.length());
                uuidBuilder.append(CHAR_POOL.charAt(index));
            }
        }

        return uuidBuilder.toString();
    }
}
