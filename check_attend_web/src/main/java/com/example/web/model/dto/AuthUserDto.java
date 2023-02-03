package com.example.web.model.dto;

import com.example.web.model.pojo.WxUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 认证用户
 *
 * @author lincheon
 * @since 2022/1/8 21:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AuthUserDto {
    /**
     * 传入参数：临时登录凭证
     */
    private String code;

    /**
     * 用户登录id
     */
    private String uuid = "";

    /**
     * 微信openId
     */
    @JsonIgnore
    private String openId;

    /**
     * 传入参数: 用户非敏感信息
     */
    private String rawData;

    /**
     * 传入参数: 签名
     */
    private String signature;

    /**
     * 传入参数: 用户敏感信息
     */
    private String encryptedData;

    /**
     * 传入参数: 解密算法的向量
     */
    private String iv;

    /**
     * 会话密钥
     */
    @JsonIgnore
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符
     */
    @JsonIgnore
    private String unionId;

    /**
     * 返回:服务器jwt token
     */
    private String token;

    /**
     * 返回：userName或openId对应的用户
     */
    private WxUser userInfo;

}

