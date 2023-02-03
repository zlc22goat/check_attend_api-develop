package com.example.web.log;


import java.lang.annotation.*;

/**
 * 自定义操作日志注解
 *
 * @author lincheon
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String operateModule() default ""; // 操作模块

    String operateType() default ""; // 操作类型

    String operateDesc() default ""; // 操作说明
}
