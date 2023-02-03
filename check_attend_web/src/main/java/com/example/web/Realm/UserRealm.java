package com.example.web.Realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.core.model.pojo.User;
import com.example.core.utils.ApplicationContextUtils;
import com.example.core.utils.JWTUtils;
import com.example.web.service.PermissionService;
import com.example.web.service.RoleService;
import com.example.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.security.Permission;
import java.time.Period;
import java.util.List;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Autowired
    @Lazy
    private PermissionService permissionService;

    /**
     * 重写supports方法，token必须是JWTToken（整合JWT时候需要，光使用shiro不需要添加这个方法）
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


    /**
     * aim 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*整合JWT和Shiro之后的代码*/
        log.info("--doGetAuthenticationInfo登陆验证");
        String tokenStr = (String) authenticationToken.getPrincipal();
        SimpleAuthenticationInfo info;


         // 进行是否是来自微信小程序端的用户的登录的判断->是则执行
        if (JWTUtils.getIsWx(tokenStr)) {
            if (JWTUtils.wxVerify(tokenStr, JWTUtils.getOpenId(tokenStr), JWTUtils.getWxSecret(tokenStr))) {
                log.info("登陆成功");
                return new SimpleAuthenticationInfo(authenticationToken.getCredentials(), authenticationToken.getCredentials(), this.getName());
            } else {
                log.error("登陆失败");
                return null;
            }
        }

        // 进行来自非小程序端登录的用户判断
        //解密获得username，用于和数据库进行比对
        //通过查看负载获得username
        String username = JWTUtils.getUsername(tokenStr);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        User user = userService.getOne(wrapper.eq("user_name", username));
        if (!ObjectUtils.isEmpty(user)) {
            //验证密码是否正确
            if (JWTUtils.verify(tokenStr, username, user.getPassword()))
                log.info("登陆成功");
            else {
                throw new IncorrectCredentialsException("密码错误");
            }
            info = new SimpleAuthenticationInfo(authenticationToken.getCredentials(), authenticationToken.getCredentials(), this.getName());
            return info;
        }
        return null;
    }

    /**
     * aim 获得|检索|相关授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info=null;

        // 进行是否是微信小程序登录的用户判断->是则直接放行
        if(JWTUtils.getIsWx(token)){
            info = new SimpleAuthorizationInfo();
            info.addRole("WxUser");
            return info;
        }


        // 进行来自非小程序端登录的用户判断
        log.info("执行doGetAuthorizationInfo方法进行授权");
        String username = JWTUtils.getUsername(token);
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        log.info("登录的用户:" + username);
        info = new SimpleAuthorizationInfo();
        String role = roleService.findRoleByUserName(username);
        List<String> perms = permissionService.findPermsByRole(role);
        if (role == null && CollectionUtils.isEmpty(perms)) {
            return null;
        }
        if (role != null) {
            info.addRole(role);
        }
        if (!CollectionUtils.isEmpty(perms)) {
            perms.forEach(info::addStringPermission);
        }
        return info;
    }

    /**
     * aim 清除缓存
     */
    public void clear(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}
