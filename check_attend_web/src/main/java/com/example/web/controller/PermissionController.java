package com.example.web.controller;


import com.example.core.controller.AbsController;
import com.example.core.model.pojo.Permission;
import com.example.web.service.PermissionService;
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
@RequestMapping("/permission")
@Api(value = "细化权限管理-Permission", tags = {"权限管理"})
public class PermissionController extends AbsController<PermissionService, Permission> {

}

