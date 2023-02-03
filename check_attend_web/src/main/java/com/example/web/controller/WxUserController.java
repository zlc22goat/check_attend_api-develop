package com.example.web.controller;


import com.example.core.model.vo.Result;
import com.example.web.model.dto.AuthUserDto;
import com.example.web.service.WxUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lincheon
 * @since 2022-01-08
 */
@RestController
@RequestMapping("/wx-user")
@Api(value = "微信小程序",tags = {"微信小程序"})
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    @PostMapping("/login")
    public Result login(@RequestBody AuthUserDto authUserDto, HttpServletRequest request){
        return wxUserService.login(authUserDto,request);
    }
}

