package com.training.generic;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 泛型的集合遍历
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class GenericCollectionTest {

    /**
     * 测试泛型集合
     */
    @Test
    public void run1() {
        // List集合
        List<String> list = new ArrayList<>();
        list.add("美美");
        list.add("旺财");
        list.add("芙蓉姐姐");

        // 循环遍历	三种	普通for	增强for	迭代器
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            System.out.println(str);
        }

        System.out.println("==============================");

        // 增强for循环		for(类型	变量 : 集合){}
        for (String str : list) {
            System.out.println(str);
        }

        System.out.println("==============================");
        // 迭代器
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * Set集合
     */
    @Test
    public void run2() {
        Set<String> set = new HashSet<>();
        set.add("柯震东");
        set.add("房祖名");
        set.add("王全安");
        set.add("张耀扬");
        // 遍历方式		迭代器和增强for循环
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("================================");
        for (String str : set) {
            System.out.println(str);
        }
    }

    /**
     * Map集合
     */
    @Test
    public void run3() {
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 11);
        map.put("bb", 22);
        map.put("cc", 33);

        // 遍历两种	获取key
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Integer value = map.get(key);
            System.out.println(key + " " + value);
        }
        System.out.println("===========================================");

        Set<Map.Entry<String, Integer>> entrys = map.entrySet();
        for (Map.Entry<String, Integer> entry : entrys) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /**
     * 只有两边有泛型，必须一致。
     */
    public void run4() {
        List list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        List list3 = new ArrayList<String>();
        List<String> list4 = new ArrayList<String>();

        //	List<Object> list5 = new ArrayList<String>();
        //	List<String> list6 = new ArrayList<Object>();
    }

    public void demo1(List list) {

    }

    public void demo() {
        demo1(new ArrayList<String>());
    }

}
