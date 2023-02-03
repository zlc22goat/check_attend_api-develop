package com.example.core.model.dto;


/**
 *
 * 判断这个数据在数据库中能否重复添加，不可重复添加的类可以 implements 这个方法
 *
 * @author tzy
 * @since 2021/9/23 15:01
 */
public interface UnrepeatableEntity {
    /**
     * 实现这个方法，获取用来判断这个数据是否已经重复存在，return 的是数据库中的那个字段名（navicat可视化表格第一行），小驼峰格式
     * @return 用来判断是否可以重复的属性的变量名称
     */
    String judgeProperty();
}
