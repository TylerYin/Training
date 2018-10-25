package com.training.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 * @Author Tyler Yin
 * @create 2017-11-04 19:26
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBInfo {
    String driverClassName();
    String url();
    String username();
    String password();
}
