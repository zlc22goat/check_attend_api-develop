package com.example.web.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.core.enums.ResultCode;
import com.example.core.model.pojo.User;
import com.example.core.model.vo.Result;
import com.example.core.service.impl.AbsServiceImpl;
import com.example.core.utils.EncryptionUtils;
import com.example.core.utils.JWTUtils;
import com.example.core.utils.ResultUtil;
import com.example.core.utils.SaltUtils;
import com.example.web.Realm.JWTToken;
import com.example.web.mapper.UserMapper;
import com.example.web.model.dto.LoginDto;
import com.example.web.model.vo.LoginUserVo;
import com.example.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tzy
 * @since 2021-03-27
 */
@Service
@Slf4j
@Transactional //开启事务支持
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, AbsServiceImpl<UserService, User> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result register(String body){
        User user = JSONObject.parseObject(body,User.class);
        // 获取盐值
        user.setSalt(SaltUtils.getFixedSalt());
        // 加密密码
        user.setPassword(EncryptionUtils.encryptPwd(user.getPassword(), user.getSalt()));
        // 默认的role
        user.setRoleId("0");
        // 调用底层封装保存用户
        Result result = this.create(JSONObject.toJSONString(user),User.class,this);
        switch (result.getMessage()) {
            case "重复添加,已存在":
                result.setMessage("用户名已存在");
                break;
            case "添加失败":
                result.setMessage("创建失败");
                break;
            case "添加成功":
                result.setMessage("创建成功");
                break;
            default:break;
        }
        return result;
    }

    @Override
    public Result login(String requestLogin) {
        LoginDto loginDto = JSONObject.parseObject(requestLogin, LoginDto.class);
        //Shiro整合JWT新增
        String usernamePasswordToken = JWTUtils.sign(loginDto.getUserName(), loginDto.getPassword());
        //获取subject对象
        Subject subject = SecurityUtils.getSubject();
        try {
            //Shiro整合JWT新增
            subject.login(new JWTToken(usernamePasswordToken));
            LoginUserVo loginUserVo=userMapper.getUserAllInfo(loginDto.getUserName());
            loginUserVo.setToken(usernamePasswordToken);
            return ResultUtil.success(ResultCode.LOGIN_SUCCESS, "登录成功", loginUserVo);
        } catch (UnknownAccountException e) {
            return ResultUtil.fail(ResultCode.USERNAME_NOT_FOUND, "账号不存在");
        } catch (IncorrectCredentialsException e) {
            return ResultUtil.fail(ResultCode.PASSWORD_WRONG, "密码错误");
        }
    }

    @Override
    public void beforeUpdate(Object entity) {
        // 如果出现密码更新
        String password = ((User) entity).getPassword();
        if(password!=null) {
            ((User) entity).setPassword(EncryptionUtils.encryptPwd(password, SaltUtils.getFixedSalt()));
        }
    }

    @Override
    public int confirmDelete(String id) {
        return 0;
    }

    @Override
    public Page deepSearch(Page page, Wrapper wrapper) {
        return null;
    }
}
