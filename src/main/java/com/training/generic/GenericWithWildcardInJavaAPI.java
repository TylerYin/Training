package com.training.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description Generic with wildcard in java API
 * @Author Tyler Yin
 * @Create 2017-11-12 14:49
 **/
public class GenericWithWildcardInJavaAPI {
    public static void main(String[] args) {
        /**
         * 通配符? 在api中的体现。
         *
         * Collection接口： boolean containsAll(Collection<?> c)
         */

        Collection<String> c1 = new ArrayList<String>();
        c1.add("haha");
        c1.add("hehe");

        Collection<Integer> c2 = new ArrayList<Integer>();
        c2.add(4);
        c2.add(5);

        /**
         * 了解 containAll源码内判断是否包含的依据。依据是equals方法。
         * public  boolean equals(Object obj) "abc".equals(5);
         */
        boolean b = c1.containsAll(c2);
        System.out.println("b = " + b);
    }
}

/**
 * class Collection<E>{
 *      public boolean containsAll(Collection<?> c){
 *
 *      }
 * }
 */