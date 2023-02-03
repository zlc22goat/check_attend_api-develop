package com.example.web.model.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lincheon
 * @since 2022/1/5 15:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @ApiModelProperty(value = "用户名")
    String userName;
    @ApiModelProperty(value = "密码")
    String password;
}
