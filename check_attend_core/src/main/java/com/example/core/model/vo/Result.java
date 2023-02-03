package com.example.core.model.vo;

import com.example.core.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cyb_xiyi
 * @since 2021/1/17
 */
@ApiModel
@Data
public class Result<T> implements Serializable {
    @ApiModelProperty(value = "响应码")
    private Integer code;
    @ApiModelProperty(value = "响应消息")
    private String message;
    @ApiModelProperty(value = "响应数据")
    private T data;

    /**
     * 状态码和消息
     *
     * @param resultCode
     */
    public Result(ResultCode resultCode) {
        this(resultCode.code(), resultCode.message(), null);
    }

    /**
     * 状态码和自定义消息
     *
     * @param resultCode
     * @param message
     */
    public Result(ResultCode resultCode, String message) {
        this(resultCode.code(), message, null);
    }

    /**
     * 状态码和数据
     *
     * @param resultCode
     * @param data
     */
    public Result(ResultCode resultCode, T data) {
        this(resultCode.code(), resultCode.message(), data);
    }

    /**
     * 状态码和数据
     *
     * @param resultCode
     * @param data
     */
    public Result(ResultCode resultCode, String message, T data) {
        this(resultCode.code(), message, data);
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Result() {

    }
}
