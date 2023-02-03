package com.example.web.filter;

import com.example.web.Realm.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 退出登录逻辑 - 存在一定问题待更正
 * @author lincheon
 * @since 2022/1/2 16:39
 **/
@Slf4j
public class ShiroLogoutFilter extends LogoutFilter {

    /**
     * 自定义登出,登出之后,清理当前用户redis部分缓存信息
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        log.info("进入自定义Logout方法");
        //登出操作 清除缓存  subject.logout() 可以自动清理缓存信息, 这些代码是可以省略的  这里只是做个笔记 表示这种方式也可以清除
        Subject subject = getSubject(request,response);
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        UserRealm shiroRealm = (UserRealm) securityManager.getRealms().iterator().next();
        String token=((HttpServletRequest) request).getHeader("Authorization");
        PrincipalCollection principals = new SimplePrincipalCollection(token,UserRealm.class.getName());
        shiroRealm.clear(principals);

//        //登出
//        subject.logout();
//        //获取登出后重定向到的地址
//        String redirectUrl = getRedirectUrl(request,response,subject);
//        //重定向
//        issueRedirect(request,response,redirectUrl);
        return true;
    }

}
