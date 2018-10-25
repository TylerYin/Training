package com.training.apache.commons.beanutils;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Tyler Yin
 */
public class Test2 {
    public static void main(String[] args) {
        testMethond1();
    }

    public static void testMethond1() {
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

        CopiedPerson copiedPerson = new CopiedPerson();
        Person person1 = new Person();

        // BeanUtils.copyProperties(person, copiedPerson);
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


