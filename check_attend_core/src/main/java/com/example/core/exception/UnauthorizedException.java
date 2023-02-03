package com.example.core.exception;

/**
 * @author tzy
 * @since 2021/3/29 22:30
 * aim 未认证异常返回
 */
public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }

}
