package com.training.proxy.dynamic;

/**
 * 定义被代理类
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class Student implements Person {

    public String say(String message) {
        return "Hello " + message;
    }
}
