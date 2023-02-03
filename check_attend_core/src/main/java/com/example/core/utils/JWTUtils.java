package com.example.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

public class JWTUtils {


    private static final String wxSignSecret = "gc";
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    private static final String SALT = SaltUtils.getFixedSalt(); //若使用随机盐后续不知道如何处理，故使用固定盐

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 校验token是否正确(微信小程序使用)
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean wxVerify(String token, String openId, String secret) {
        try {
            Md5Hash encryption = new Md5Hash(secret, SALT, 1024);
            Algorithm algorithm = Algorithm.HMAC256(encryption.toHex());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("open_id", openId)
                    .withClaim("secret", secret)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        String ans;
        try {
            DecodedJWT jwt = JWT.decode(token);
            ans=jwt.getClaim("username").asString();
        } catch (Exception e) {
            return null;
        }
        return ans;
    }

    /**
     * 微信端使用
     *
     * @return token中包含的openId
     */
    public static String getOpenId(String token) {
        String ans;
        try {
            DecodedJWT jwt = JWT.decode(token);
            ans=jwt.getClaim("open_id").asString();
        } catch (Exception e) {
            return null;
        }
        return ans;
    }

    /**
     * 微信端使用
     *
     * @return token中包含的secret
     */
    public static String getWxSecret(String token) {
        String ans;
        try {
            DecodedJWT jwt = JWT.decode(token);
            ans=jwt.getClaim("secret").asString();
        } catch (Exception e) {
            return null;
        }
        return ans;
    }

    /**
     * 微信端使用
     * 判断是否是来自微信端的登录
     */
    public static boolean getIsWx(String token) {
        boolean flag;
        try {
            DecodedJWT jwt = JWT.decode(token);
            flag=jwt.getClaim("isWx").asBoolean();
        } catch (Exception e) {
            return false;
        }
        return flag;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static Integer getUserId(String token) {
        int userId;
        try {
            DecodedJWT jwt = JWT.decode(token);
            userId=jwt.getClaim("userId").asInt();
        } catch (Exception e) {
            return null;
        }
        return userId;
    }

    /**
     * 获得tokenId
     *
     * @return uuid
     */
    public static String getTokenId(String token) {
        String id;
        try {
            DecodedJWT jwt = JWT.decode(token);
            id= jwt.getId();
        } catch (Exception e) {
            return null;
        }
        return id;
    }

    /**
     * 获取token过期时间
     *
     * @return 过期时间
     */
    public static Date getExpiresAt(String token) {
        Date date;
        try {
            DecodedJWT jwt = JWT.decode(token);
            date= jwt.getExpiresAt();
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    /**
     * 获取token签发时间
     *
     * @return 签发时间
     */
    public static Date getIssuedAt(String token) {
        Date date;
        try {
            DecodedJWT jwt = JWT.decode(token);
            date= jwt.getIssuedAt();
        } catch (Exception e) {
            return null;
        }
        return date;
    }


    /**
     * 生成签名
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        //后期新增，原本只是再sign中加的未处理的密码即secret
        Md5Hash encryption = new Md5Hash(secret, SALT, 1024);
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(encryption.toHex());
        String jwtId = UUID.randomUUID().toString();
        // 附带username信息
        return JWT.create()
                .withJWTId(jwtId)
                .withClaim("username", username)
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    /**
     * 生成签名(微信小程序端)
     *
     * @param openId 用户标识
     * @return 加密的token
     */
    public static String wxSign(String openId) {
        Md5Hash encryption = new Md5Hash(wxSignSecret, SALT, 1024);
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(encryption.toHex());
        String jwtId = UUID.randomUUID().toString();
        // 附带username信息
        return JWT.create()
                .withJWTId(jwtId)
                .withClaim("open_id", openId)
                .withClaim("isWx", true) // 用于判别是否是来自微信的登录
                .withClaim("secret", wxSignSecret)
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public static void main(String[] args) {
        String token = sign("aaa", "123456");
        System.out.println("token" + token);
        System.out.println(getTokenId(token));
        System.out.println(getUserId(token));
        System.out.println(getUsername(token));
        System.out.println(getIssuedAt(token));
        System.out.println(getExpiresAt(token));
        System.out.println(verify(token, "aaa", "123456"));
    }

}
