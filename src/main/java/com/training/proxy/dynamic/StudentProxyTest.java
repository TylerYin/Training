package com.training.proxy.dynamic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * 测试动态代理
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class StudentProxyTest {

    // 做Person接口实现类Student的动态代理。
    @Test
    public void run() {
        // 1.创建一个Student 被代理
        final Person person = new Student();

        // 2.得到person的代理对象.
        Person sproxy = (Person) Proxy.newProxyInstance(person.getClass()
                        .getClassLoader(), person.getClass().getInterfaces(),
                /**
                 * 参数 proxy就是代理对象，我们一般不使用。
                 * 参数method就是调用方法, 就是要访问的方法。
                 * 参数args就是调用的方法的参数
                 * 返回值,就是真实行为执行后返回的结果，会传递给代理对象调用的方法.
                 **/
                (proxy, method, args) -> method.invoke(person, args));

        // 这个是代理对象调用say方法.
        System.out.println(sproxy.say("james"));
    }
}
