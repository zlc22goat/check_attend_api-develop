package com.example.web.controller;


import com.example.core.controller.AbsController;
import com.example.core.enums.ResultCode;
import com.example.core.model.pojo.User;
import com.example.core.model.vo.Result;
import com.example.core.utils.ResultUtil;
import com.example.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tzy
 * @since 2022-01-01
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "用户管理", tags = {"用户管理"})
public class UserController extends AbsController<UserService, User> {

    @Autowired
    private UserService userService;

    /**
     * aim 注册
     *
     * @author tzy
     * @since 2022/1/2
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Result register(HttpServletRequest request, @RequestBody String body) {
        return userService.register(body);
    }

    /**
     * aim 登陆
     *
     * @author tzy
     * @since 2022/1/2
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result login(@RequestBody String requestLogin ) {
        return userService.login(requestLogin);
    }

    /**
     * aim 退出登录 - 具体是否有效还有待商榷
     *
     * @author tzy
     * @since 2022/1/2
     */
    @PostMapping("logout")
    @ApiOperation(value = "退出登录")
    public Result logout() {
        log.info("进入/LOGOUT方法");
        Subject subject = SecurityUtils.getSubject();
        subject.logout(); //退出用户
        return ResultUtil.success(ResultCode.LOGOUT_SUCCESS, "退出成功");
    }

}

