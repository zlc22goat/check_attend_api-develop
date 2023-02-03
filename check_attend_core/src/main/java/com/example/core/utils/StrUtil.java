package com.example.core.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author cybxiyi
 * @since 2021/7/9 4:21 下午
 */
public class StrUtil {

    private static final char[] chars =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@%^&*".toCharArray();
    private static final Random random = new Random();

    /**
     * 生成32位uuid
     *
     * @return 32位uuid
     */
    public static String generateUUId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 要判断的字符串
     * @return
     */
    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    /**
     * 随机生成n位字符串
     *
     * @param n 位数
     * @return
     */
    public static String generateRandStr(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    /**
     * 随机生成d位数字验证码
     *
     * @param d 位数
     * @return
     */
    public static String generateDigitalCaptcha(int d) {
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < d; i++) {
            int n = random.nextInt(10);
            while (captcha.toString().contains(String.valueOf(n))) {
                n = random.nextInt(10);
            }
            captcha.append(n);
        }
        return captcha.toString();
    }
}
