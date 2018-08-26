package com.training.generic;

import java.util.*;

/**
 * @Description Arrays Demo
 * @Author Tyler Yin
 * @Create 2017-11-12 18:11
 **/
public class ArraysDemo {
    public static void main(String[] args) {
        /**
         * Arrays:用于操作数组的工具类。
         * 类中定义的都是静态工具方法
         * 1，对数组排序。
         * 2，二分查找。
         * 3，数组复制。
         * 4，对两个数组进行元素的比较，判断两个数组是否相同。
         * 5，将数组转成字符串。
         */

        int[] arr = {34, 21, 67};
        System.out.println(Arrays.toString(arr));

        //将arr转成list集合。？如果数组中存储的是基本数据类型，那么转成集合，数组对象会作为集合中的元素存在。
        //数组中元素时引用数据类型时，转成，数组元素会作为集合元素存在。
        List<int[]> list1 = Arrays.asList(arr);
        System.out.println(list1);

        String[] strs = {"hah", "hehe", "xixi"};
        boolean b = contains(strs, "hehe");
        System.out.println(b);
        //将数组转成list集合。asList
        /**
         * 数组转成集合：就为了使用集合的方法操作数组中的元素。
         * 但是不要使用增删等改变长度的方法。add remove  发生UnsupportedOperationException
         */
        List<String> list = Arrays.asList(strs);
        System.out.println(list.contains("hehe"));
        System.out.println(list.get(2));
        System.out.println(list.indexOf("hehe"));
        //list.add("java");//UnsupportedOperationException  数组长度的固定的，转成List集合长度也是固定的。

        //-------------------集合转成数组---------------------
        /**
         * 为什么集合转成数组呢？
         * 为了限制对元素的增删操作。
         */
        Collection<String> c = new ArrayList<>();
        c.add("haha1");
        c.add("haha2");
        c.add("haha3");
        c.add("haha4");
        /**
         *  如果传递的数组的长度小于集合的长度，会创建一个同类型的数组长度为集合的长度。
         *  如果传递的数组的长度大于了集合的长度，就会使用这个数组，没有存储元素的位置为null。
         *  长度最好直接定义为和集合长度一致。
         */
        String[] str_arr = c.toArray(new String[c.size()]);
        System.out.println(Arrays.toString(str_arr));
    }

    public static boolean contains(String[] strs, String key) {
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(key)) {
                return true;
            }
        }
        return false;
    }
}
