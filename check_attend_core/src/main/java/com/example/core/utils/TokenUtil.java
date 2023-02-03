package com.example.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import java.util.Calendar;
import java.util.Map;

/**
 * @author cybxiyi
 * @since 2021/7/9 9:33 上午
 */
public class TokenUtil {
    // 签名私钥
    private static final String PRIVATE_KEY = "619YYDS";
    private static final Algorithm SIGN_ALGORITHM = Algorithm.HMAC256(PRIVATE_KEY);
    // token过期时间
    private static final Integer EXPIRE_HOUR = 6;

    public static String generateToken(Map<String, String> claims) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, EXPIRE_HOUR);
        JWTCreator.Builder builder = JWT.create();
        // 设置一系列声明字段
        if (claims != null) {
            claims.forEach(builder::withClaim);
        }
        // 设置过期时间
        builder.withExpiresAt(calendar.getTime());
        return builder.sign(SIGN_ALGORITHM);
    }

    public static void verifyToken(String token) {
        JWT.require(SIGN_ALGORITHM)
                .build()
                .verify(token);
    }

    /**
     * 获得token中的某个claim
     *
     * @param token     token值
     * @param claimName claim键值对的key
     * @param clazz     需要转换的类
     * @param <T>       类的泛型
     * @return claim的raw type
     */
    public static <T> T getClaim(String token, String claimName, Class<T> clazz) {
        return JWT.decode(token)
                .getClaim(claimName).as(clazz);
    }

    /**
     * 获得token中的所有claim
     *
     * @param token
     * @return
     */
    public static Map<String, Claim> getClaims(String token) {
        return JWT.decode(token)
                .getClaims();
    }
}
