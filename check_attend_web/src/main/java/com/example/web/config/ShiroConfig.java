package com.example.web.config;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.web.Cache.RedisCacheManager;
import com.example.web.Realm.UserRealm;
import com.example.web.filter.JwtFilter;
import com.example.web.filter.ShiroLogoutFilter;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Shiro+Jwt的配置
 * @author lincheon
 * @since 2022/1/2 0:19
 **/
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager){
        //创建shiro的filter
        ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
        //注入安全管理器
        factoryBean.setSecurityManager(securityManager);
        /*原先写法*/
        Map<String,String> map=new HashMap<>();
        map.put("/user/login", "anon");
        map.put("/driver/login", "anon");
        map.put("/wx-user/login","anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/webjars/**", "anon");
        map.put("/v2/**", "anon");
        map.put("/swagger-ui.html", "anon");
        map.put("/user/register/**","anon");
        map.put("/doc.html/**","anon");
        map.put("/user/logout", "logout");
        map.put("/**","jwt");
        factoryBean.setFilterChainDefinitionMap(map);
        HashMap<String, Filter> myFilters=new HashMap<>();
        myFilters.put("jwt",new JwtFilter());
        ShiroLogoutFilter logoutFilter=new ShiroLogoutFilter();
        myFilters.put("logout",logoutFilter);
        factoryBean.setFilters(myFilters);
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("getRealm") Realm realm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        /*关闭session(此方法是在集成JWT之后加的，不集成JWT删去即可)*/
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    @Bean
    public Realm getRealm(){
        UserRealm userRealm=new UserRealm();
//        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("md5");
//        matcher.setHashIterations(1024); //散列1024次
//        userRealm.setCredentialsMatcher(matcher);
// 使用RedisCache缓存依赖
//        userRealm.setCacheManager(new RedisCacheManager());
        //使用EhCache缓存依赖
        userRealm.setCacheManager(new EhCacheManager());
        //开启全局缓存
        userRealm.setCachingEnabled(true);
        //开启认证缓存
        userRealm.setAuthenticationCachingEnabled(true);
        //开启授权缓存
        userRealm.setAuthorizationCachingEnabled(true);
        //设置认证缓存的名字
        userRealm.setAuthenticationCacheName("AuthenticationCache");
        userRealm.setAuthorizationCacheName("AuthorizationCache");
        return userRealm;
    }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
