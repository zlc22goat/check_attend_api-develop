package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.core.model.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tzy
 * @since 2022-01-01
 */
@Repository
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<String> findPermsByRole(String role);
}
