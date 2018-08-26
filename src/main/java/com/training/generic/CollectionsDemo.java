package com.training.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Description Collections Demo
 * @Author Tyler Yin
 * @Create 2017-11-12 18:11
 **/
public class CollectionsDemo {
    public static void main(String[] args) {
		/**
         * Collections: 集合框架中的用于操作集合对象 工具类。
		 * 都是静态的工具方法。
		 * 1，获取Collection最值。
		 * 2，对List集合排序，也可以二分查找。
		 * 3，对排序逆序。
		 * 4，可以将非同步的集合转成同步的集合。
		 * Xxx synchronizedXxx(Xxx)  List synchronizedList(List)
		 */

        System.out.println("---------获取最值---------------");
        Collection<String> c = new ArrayList<>();
        c.add("haha");
        c.add("zz");
        c.add("xixii");
        c.add("abc");
        String max = Collections.max(c, new ComparatorByLength());

        System.out.println("max=" + max);
        System.out.println("-----------排序-------------");
        List<String> list = new ArrayList<>();
        list.add("hahaha");
        list.add("abc");
        list.add("xiix");
        list.add("z");
        list.add("java");
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);

        System.out.println("------------------------");
        System.out.println("------------------------");
    }
}
