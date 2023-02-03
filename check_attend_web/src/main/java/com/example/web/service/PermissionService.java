package com.example.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.core.model.pojo.Permission;
import com.example.core.service.AbsService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tzy
 * @since 2022-01-01
 */
public interface PermissionService extends IService<Permission>, AbsService<Permission> {
    List<String> findPermsByRole(String role);
}
