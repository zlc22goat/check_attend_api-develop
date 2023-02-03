package com.example.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.core.model.pojo.User;
import com.example.core.model.vo.Result;
import com.example.core.service.AbsService;

import java.sql.ResultSet;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tzy
 * @since 2021-03-27
 */
public interface UserService extends IService<User>, AbsService<User> {
    Result register(String body);
    Result login(String requestLogin);
}
