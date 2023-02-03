package com.example.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @auther tzy
 * @date 2021/3/27 23:42
 * aim 根据名字获取Spring容器中的bean，名字格式：首字母必小写
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    //根据bean名字获取工厂中指定的bean对象
    public static Object getBean(String beanName){
        System.out.println("进行中");
        return context.getBean(beanName);
    }
}
