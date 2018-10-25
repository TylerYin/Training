package com.training.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射
 *
 * @Author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class ReflectTest {

    @Test
    public void getClassObject() throws ClassNotFoundException {
        //第一种
        Class person = Class.forName("com.training.reflect.Person");
        System.out.println(person);

        //第二种
        Class person2 = new Person().getClass();
        System.out.println(person2);

        //第三种
        Class person3 = Person.class;
        System.out.println(person3);
    }

    /**
     * 获取构造的对象
     *
     * @throws Exception
     */
    @Test
    public void run2() throws Exception {
        Class clazz = Class.forName("com.training.reflect.Person");
        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        Person person = (Person) constructor.newInstance("LiLei");
        System.out.println(person);
    }

    /**
     * 获取属性的对象
     *
     * @throws Exception
     */
    @Test
    public void run3() throws Exception {
        Class clazz = Class.forName("com.training.reflect.Person");
        Person person = (Person) clazz.newInstance();
        Field field = clazz.getDeclaredField("name");

        field.setAccessible(true);
        field.set(person, "张三");
        System.out.println(field.get(person));
        System.out.println(person);
    }

    /**
     * 获取方法
     *
     * @throws Exception
     */
    @Test
    public void run4() throws Exception {
        Class clazz = Class.forName("com.training.reflect.Person");
        Person person = (Person) clazz.newInstance();

        Method method = clazz.getDeclaredMethod("setId", Integer.class);
        method.setAccessible(true);
        method.invoke(person, 100);

        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(person, "张三");

        System.out.println(person.getId());
        System.out.println(field.get(person));
        System.out.println(person);
    }
}
