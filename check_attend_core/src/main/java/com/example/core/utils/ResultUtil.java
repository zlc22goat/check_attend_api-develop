package com.example.core.utils;


import com.example.core.enums.ResultCode;
import com.example.core.model.vo.Result;

/**
 * @author cyb_xiyi
 * @since 2021/1/17
 */
@SuppressWarnings({"raw", "rawtypes"})
public class ResultUtil {

    // 成功且有状态码和数据
    public static <T> Result<T> success(ResultCode resultCode, T data) {
        return new Result<>(resultCode, data);
    }

    // 成功且有状态码和默认消息
    public static Result success(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    // 成功且有状态码和默认消息
    public static Result success(ResultCode resultCode, String message) {
        return new Result<>(resultCode, message);
    }

    // 成功且有状态码、自定义消息和数据
    public static <T> Result<T> success(ResultCode resultCode, String message, T data) {
        return new Result<>(resultCode, message, data);
    }

    // 失败且有状态码和默认消息
    public static Result fail(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    // 失败且有状态码和自定义消息
    public static Result fail(ResultCode resultCode, String message) {
        return new Result<>(resultCode, message);
    }

    // 警告且有状态码和默认消息
    public static Result warn(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    // 警告且有状态码和自定义消息
    public static Result warn(ResultCode resultCode, String message) {
        return new Result<>(resultCode, message);
    }

    // ws有状态码和数据
    public static <T> Result ws(ResultCode resultCode, T data) {
        return new Result<>(resultCode, data);
    }

}
