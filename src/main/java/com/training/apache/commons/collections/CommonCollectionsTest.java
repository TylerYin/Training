package com.training.apache.commons.collections;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.map.LinkedMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author Tyler Yin
 */
public class CommonCollectionsTest {

    /**
     * 得到集合里按顺序存放的key之后的某一Key
     */
    @Test
    public void testOrderedMap() {
        OrderedMap map = new LinkedMap();
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");

        System.out.println(map.firstKey());
        System.out.println(map.nextKey("five"));
        System.out.println(map.nextKey("six"));
    }

    /**
     * 通过key得到value
     * 通过value得到key
     * 将map里的key和value对调
     */
    @Test
    public void testBidiMap() {
        BidiMap bidi = new TreeBidiMap();
        bidi.put("six", "6");
        bidi.put("seven", "7");
        bidi.put("eight", "8");

        System.out.println(bidi.get("six"));
        System.out.println(bidi.getKey("6"));

        // removes the mapping
        bidi.removeValue("6");

        BidiMap inverse = bidi.inverseBidiMap();
        System.out.println(inverse);
    }

    /**
     * CollectionUtils测试
     */
    @Test
    public void testCollectionUtils() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("3");
        list1.add("4");
        list1.add("5");

        List<String> list2 = new ArrayList<>();
        list2.add("2");
        list2.add("3");
        list2.add("6");

        // 交集，如果某个对象在两个集合中出现，则新集合中该对象的数量取两个集合中该对象出现次数的较大数。
        Collection c = CollectionUtils.retainAll(list1, list2);
        System.out.println(c);

        // 交集，如果某个对象在两个集合中出现，则新集合中该对象的数量取两个集合中该对象出现次数的较小数。
        c = CollectionUtils.intersection(list1, list2);
        System.out.println(c);

        // 并集，如果某个对象在两个集合中出现，则新集合中该对象的数量取两个集合中该对象出现次数的较大数。
        c = CollectionUtils.union(list1, list2);
        System.out.println(c);

        // 并集，如果某个对象在两个集合中出现，则新集合中该对象的数量是两个集合中该对象出现次数的之和。
        c = CollectionUtils.collate(list1, list2);
        System.out.println(c);

        // 差集，若list2中的某个元素在list1中出现，则从list1中减去该元素，每次只减去一个元素
        c = CollectionUtils.subtract(list1, list2);
        System.out.println(c);

        // 是否子集
        System.out.println(CollectionUtils.isSubCollection(list2, list1));

        String[] array = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        System.out.println(array);

        // 倒序
        CollectionUtils.reverseArray(array);
        System.out.println(array);
    }
}
