package com.example.web.Realm;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author tzy
 * @since 2021/3/29 23:09
 * aim 取代UsernamePasswordToken，因为这里使用JWT了，需要实现AuthenticationToken接口
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}