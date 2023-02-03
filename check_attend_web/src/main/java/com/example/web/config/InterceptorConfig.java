package com.example.web.config;

import com.example.core.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tzy
 * @since 2022/1/2 9:36 上午
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                // 不拦截登入登出, 可自己路径修改
                .excludePathPatterns("/user/login", "/user/logout","/user/register/**","/wx-user/login","/driver/login")
//                 不拦截swagger文档页面
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/image/**")
                .addPathPatterns("/**");
    }

}
