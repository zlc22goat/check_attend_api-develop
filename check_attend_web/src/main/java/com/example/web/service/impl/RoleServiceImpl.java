package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.core.model.pojo.Role;
import com.example.core.model.vo.Result;
import com.example.core.service.impl.AbsServiceImpl;
import com.example.web.log.LogAnnotation;
import com.example.web.mapper.RoleMapper;
import com.example.web.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tzy
 * @since 2022-01-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService, AbsServiceImpl<RoleService, Role> {

    @Override
    @LogAnnotation(operateModule = "角色管理",operateType = "添加",operateDesc = "添加角色")
    public Result<String> create(String requestBody, Class<Role> goalClass, IService<Role> service) {
        return AbsServiceImpl.super.create(requestBody, goalClass, service);
    }

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int confirmDelete(String id) {
        return 0;
    }

    @Override
    public Page deepSearch(Page page, Wrapper wrapper) {
        return null;
    }

    @Override
    public String findRoleByUserName(String name) {
        return roleMapper.findRoleByUserName(name);
    }
}
