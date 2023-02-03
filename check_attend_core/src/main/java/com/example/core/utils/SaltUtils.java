package com.example.core.utils;

import java.util.Random;

/**
 * 随机盐生成工具类
 *
 * @author lincheon
 * @since 2022/1/2 15:26
 **/
public class SaltUtils {

    static String FIXATED = "abcd*";

    //获取固定的盐值
    public static String getFixedSalt() {
        return FIXATED;
    }

    public static String getsalt(int n) {
        char[] chars = "bakfbaowbfjkabofbaCCUVUIVIWDKV%$&^%*&*%$1131415".toCharArray(); //转换为字符数组
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(EncryptionUtils.encryptPwd("123456",SaltUtils.getFixedSalt()));
    }
}
