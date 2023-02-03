package com.example.core.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密使用
 * @author lincheon
 * @since 2022/1/2 15:27
 **/
public class EncryptionUtils {
    //对密码进行加密
    public static String encryptPwd(String pwd,String salt){
        //对pwd进行salt加密，散列1024次
        Md5Hash md5Hash=new Md5Hash(pwd,salt,1024);
        return md5Hash.toHex();
    }
}
