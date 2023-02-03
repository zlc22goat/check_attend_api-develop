package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.core.model.pojo.Permission;
import com.example.core.service.impl.AbsServiceImpl;

import com.example.web.mapper.PermissionMapper;
import com.example.web.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tzy
 * @since 2022-01-01
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService, AbsServiceImpl<PermissionService, Permission> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int confirmDelete(String id) {
        return 0;
    }

    @Override
    public Page deepSearch(Page page, Wrapper wrapper) {
        return null;
    }

    @Override
    public List<String> findPermsByRole(String role) {
        return permissionMapper.findPermsByRole(role);
    }
}
