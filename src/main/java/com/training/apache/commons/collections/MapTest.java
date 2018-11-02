package com.training.apache.commons.collections;

import org.junit.Test;

import java.util.*;

/**
 * @Description 遍历Map
 * @Author Tyler Yin
 * @Create 2017-11-19 7:52
 **/
public class MapTest {

    @Test
    public void test() {
        final Map<String, String> map = new HashMap<>(10);
        map.put("星期一", "Monday");
        map.put("星期日", "Sunday");

        System.out.println("------------keySet方法的获取---------------");
        // 怎么获取到所有的键呢？既然是所有的键，应该是一个集合，而且是一个单列集合。
        // list还是set呢？应该是set，因为map集合中键需要保证唯一性。
        // 找到一个方法，Set<k> keySet()

        // 方式1
        final Set<String> keySet = map.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = map.get(key);
            System.out.println(key + "::" + value);
        }

        // 方式2
        for (String key : keySet) {
            System.out.println(key + ":::::" + map.get(key));
        }

        /**
         * 方式3
         *
         * Set entrySet():将map集合中映射关系存储到了Set集合中
         */
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, String>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> me = it.next();
            String key = me.getKey();
            String value = me.getValue();
            System.out.println(key + "-----" + value);
        }

        // 方式4
        for (Map.Entry<String, String> me : map.entrySet()) {
            String key = me.getKey();
            String value = me.getValue();
            System.out.println(key + "--------" + value);
        }

        System.out.println("-----------获取所有值的方法 values()----------------");
        /**
         * 获取所有的值，因为值不需要保证唯一性。所以返回类型时Collection。
         */
        final Collection<String> values = map.values();
        for (String value : values) {
            System.out.println("value:" + value);
        }
    }
}
