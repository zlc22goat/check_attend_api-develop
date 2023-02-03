package com.example.core.utils;

/**
 *
 * TODO:记录一些可能可以通用的方法，这个Service需要维护，不可盲目添加方法，不然失去灵活性
 *
 * @author tzy
 * @since 2021/9/17 0:08
 */
public class GatherFunctions{
    /**
     *
     * 根据小驼峰命名规则获取相应的Spring容器中的bean
     *
     * @param beanName
     */
    public static Object getBeanFromName(String beanName){
        System.out.println("进行中");
        return ApplicationContextUtils.getBean(beanName);
    }
}
