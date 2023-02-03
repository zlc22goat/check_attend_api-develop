package com.example.core.enums;

/**
 * @author lincheon
 * @since 2021/12/31 0:25
 **/
public enum ResultCode {

    /**
     * 添加的一些其他情况响应
     */
    DELETE_DATA_NOT_FOUND_OR_ASSOCIATED(40522,"部分数据不存在或存在关联"),
    ALL_DELETE_DATA_NOT_FOUND(40420,"数据都不存在"),
    ALL_DELETE_DATA_ASSOCIATED(40520,"数据都存在关联"),
    PARTIAL_DELETE_DATA_NOT_FOUND(40421,"部分数据不存在"),
    PARTIAL_DELETE_DATA_ASSOCIATED(40521,"部分数据存在关联"),
    /*
    成功响应
     */
    SUCCESS(20000, "成功"),
    LOGIN_SUCCESS(20001, "登录成功"),
    LOGOUT_SUCCESS(20002, "已退出登录"),
    CREATE_SUCCESS(20003, "添加成功"),
    DELETE_SUCCESS(20004, "删除成功"),
    UPDATE_SUCCESS(20005, "编辑成功"),
    EXPORT_SUCCESS(20006, "导出成功"),
    GET_DATA_SUCCESS(20007, "数据获取成功"),
    IMPORT_SUCCESS(20008,"导入成功"),
    SEND_DATA_SUCCESS(20009,"数据发送成功"),

    /*
    警示类响应
     */
    WARN(40000, "异常出现"),
    TOKEN_EXPIRED(40001, "Token过期"),
    TOKEN_ILLEGAL(40002, "Token异常"),
    TOKEN_NULL(40003, "没有Token, 无法访问"),
    NO_PERMISSION(40004, "没有访问权限"),

    /*
    失败响应
     */
    FAIL(50000, "失败"),
    USERNAME_NOT_FOUND(50001, "账号不存在"),
    PASSWORD_WRONG(50002, "密码错误"),
    USERNAME_CONFLICT(50003, "该账号已存在"),
    CREATE_FAIL(50004, "添加失败"),
    CREATE_CONFLICT(50005, "添加失败, 存在重复字段"),
    UPDATE_FAIL(50006, "编辑失败"),
    UPDATE_CONFLICT(50007, "编辑失败, 存在重复字段"),
    DELETE_FAIL(50008, "删除失败"),
    EXPORT_FAIL(50009, "导出失败"),
    GET_DATA_FAIL(50010, "数据获取失败"),
    NO_DATA_FOUND(50011, "没有找到该数据"),
    INVALID_PHONE(50012, "手机号不符合规范"),
    INVALID_EMAIL(50013, "无效的邮箱"),
    SEND_DATA_FAIL(50014,"数据发送失败");


    /*
    ws响应
     */


    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return code;
    }

    public String message() {
        return message;
    }


}

