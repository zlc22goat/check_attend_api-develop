package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.model.pojo.Role;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tzy
 * @since 2022-01-01
 */
public interface RoleMapper extends BaseMapper<Role> {
    // 根据用户名查找角色
    String findRoleByUserName(String name);
}
