package com.training.apache.commons.collections;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        /**
         * 得到集合里按顺序存放的key之后的某一Key
         */
        OrderedMap map = new LinkedMap();
        map.put("FIVE", "5");
        map.put("SIX", "6");
        map.put("SEVEN", "7");
        map.firstKey();            // returns "FIVE"
        map.nextKey("FIVE");    // returns "SIX"
        map.nextKey("SIX");     // returns "SEVEN"

        /**
         * 通过key得到value
         * 通过value得到key
         * 将map里的key和value对调
         */

        BidiMap bidi = new TreeBidiMap();
        bidi.put("SIX", "6");
        bidi.get("SIX");      // returns "6"
        bidi.getKey("6");  // returns "SIX"

        // removes the mapping
        // bidi.removeValue("6");

        BidiMap inverse = bidi.inverseBidiMap();  // returns a map with keys and values swapped
        System.out.println(inverse);

        /**
         * 得到两个集合中相同的元素
         */
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("2");
        list2.add("3");
        list2.add("5");
        Collection c = CollectionUtils.retainAll(list1, list2);
        System.out.println(c);
    }
}
