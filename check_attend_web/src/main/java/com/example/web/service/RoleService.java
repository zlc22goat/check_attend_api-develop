package com.example.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.core.model.pojo.Role;
import com.example.core.service.AbsService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tzy
 * @since 2022-01-01
 */
public interface RoleService extends IService<Role>, AbsService<Role> {
    String findRoleByUserName(String name);
}
