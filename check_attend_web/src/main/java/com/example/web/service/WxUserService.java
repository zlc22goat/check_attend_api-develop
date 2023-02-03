package com.example.web.service;

import com.example.core.model.pojo.User;
import com.example.core.model.vo.Result;
import com.example.web.model.dto.AuthUserDto;
import com.example.web.model.pojo.WxUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lincheon
 * @since 2022-01-08
 */
public interface WxUserService extends IService<WxUser> {
    Result login(AuthUserDto authUserDto, HttpServletRequest request);
    WxUser findByOpenId(String openId);
}
