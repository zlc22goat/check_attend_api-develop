package com.example.web.controller;

import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

/**
 * @author lincheon
 * @since 2022/1/2 0:31
 **/
@RestController
@RequestMapping("hello")
@Api(value = "Hello接口", tags = {"测试Hello接口"})
public class HelloController {

    @ApiOperation(value = "testHello")
    @GetMapping("testHello")
    @ApiResponses(value = {@ApiResponse(code = 1002, message = "成功"), @ApiResponse(code = 1003, message = "失败")})
    public String testHello(@ApiParam("姓名") @RequestParam("name") String name) {
        return name;
    }

    @ApiOperation(value = "测试是否拥有ordinary角色")
    @GetMapping("ordinary")
    @ApiResponses(value = {@ApiResponse(code = 1002, message = "成功"), @ApiResponse(code = 1003, message = "失败")})
    @RequiresRoles(value = "ordinary")
    public String requireOrdinary(@ApiParam("姓名") @RequestParam("name") String name) {
        return "是具有ordinary角色的用户";
    }

    @ApiOperation(value = "测试是否拥有test:list权限")
    @GetMapping("testList")
    @ApiResponses(value = {@ApiResponse(code = 1002, message = "成功"), @ApiResponse(code = 1003, message = "失败")})
    @RequiresPermissions(value = "test:list")
    public String requireTestList(@ApiParam("姓名") @RequestParam("name") String name) {
        return "是具有test:list权限的用户";
    }
}
