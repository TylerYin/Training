package com.training.apache.commons.beanutils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

/**
 * 测试BeanUtils工具类
 *
 * @Author Tyler Yin
 */
public class BeanUtilsTest {

    /**
     * BeanUtils常用的方法有: cloneBean和populate。
     * 当然该工具类也可以用来进行两个对象之间属性值的拷贝，如copyProperties方法，但是不推荐使用，因为其效率不高。
     * 若需要，可以换用Spring的BeanUtils工具类中的copyProperties方法，或者PropertyUtils的opyProperties方法。
     */

    @Test
    public void testCloneBeanMethod() {
        Person person = new Person();
        person.setName("tom");
        person.setEmail("qq@qq.com");
        person.setAge(21);

        List<Hobby> hobbies = new ArrayList<>();
        hobbies.add(new Hobby("骑马", "骑马运动"));
        hobbies.add(new Hobby("跳水", "跳水运动"));
        hobbies.add(new Hobby("滑冰", "滑冰运动"));
        person.setHobbies(hobbies);

        try {
            Person person2 = (Person) BeanUtils.cloneBean(person);
            System.out.println(person2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * populate方法用来将Map<Key,value>中的以值（String或String[]）转换到目标bean对应的属性中，Map中的Key必须是目标bean的属性名。
     * 原理也是通过Java的反射机制来做的
     */
    @Test
    public void testPopulateMethod() {
        Map map = new HashMap(10);
        map.put("age", "21");
        map.put("name", "tom");
        map.put("phone", "13377777777");
        map.put("email", "tom@");

        List<Hobby> hobbies = new ArrayList<>();
        hobbies.add(new Hobby("骑马", "骑马运动"));
        hobbies.add(new Hobby("跳水", "跳水运动"));
        hobbies.add(new Hobby("滑冰", "滑冰运动"));
        map.put("hobbies", hobbies);

        Person person = new Person();
        try {
            BeanUtils.populate(person, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 通过上面的一行代码，此时person的属性就已经具有了上面所赋的值了。
        // 将一个Bean转化为一个Map对象了，如下：
        try {
            map = BeanUtils.describe(person);
            System.out.println(map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用PropertyUtils进行两个对象之间属性的拷贝
     */
    @Test
    public void testCopyProperties() {
        Person person = new Person();
        person.setName("tom");
        person.setEmail("279935400@QQ.com");
        person.setAge(21);

        List<Hobby> hobbyList = new ArrayList<>();
        hobbyList.add(new Hobby("basketball", "I love basketball"));
        hobbyList.add(new Hobby("football", "I love football"));
        hobbyList.add(new Hobby("tennis", "I love tennis"));
        hobbyList.add(new Hobby("swimming", "I love swimming"));
        person.setHobbies(hobbyList);

        // Person person1 = new Person();
        // BeanUtils.copyProperties(person, copiedPerson);

        CopiedPerson copiedPerson = new CopiedPerson();
        try {
            PropertyUtils.copyProperties(copiedPerson, person);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(copiedPerson);
    }
}


