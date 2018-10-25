package com.training.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @Author Tyler Yin
 * @create 2017-11-04 19:26
 **/

@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BankInfo {
	int maxMoney();
}
