package com.example.web.controller;


import com.example.core.controller.AbsController;
import com.example.core.model.pojo.Role;
import com.example.web.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tzy
 * @since 2022-01-01
 */
@RestController
@RequestMapping("/role")
@Api(value = "角色管理-Role",tags = {"角色管理"})
public class RoleController extends AbsController<RoleService, Role> {

}

