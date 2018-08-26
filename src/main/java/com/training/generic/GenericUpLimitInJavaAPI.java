package com.training.generic;

import com.training.generic.domain.Person;
import com.training.generic.domain.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 * @Description Generic limit in java API
 * @Author Tyler Yin
 * @Create 2017-11-12 14:50
 **/
public class GenericUpLimitInJavaAPI {
    public static void main(String[] args) {
        /**
         *  泛型的限定在api中的使用。上限的体现。
         *  TreeSet(Collection<? extends E> c)
         */

        //创建一个Collection
        final Collection<Student> c = new ArrayList<>();
        c.add(new Student("wangcai1", 26));
        c.add(new Student("wangcai2", 29));

        //TreeSet集合在创建时，就将c中的存储到Treeset集合。
        TreeSet<Person> ts = new TreeSet<>(c);
        ts.add(new Student("wangwu", 23));
        ts.add(new Person("lisi", 20));

        ts.stream().forEach(System.out::println);
    }
}

/**
 * class TreeSet<E>{
 *      TreeSet(Collection<? extends E> c){
 *
 *      }
 * }
 */