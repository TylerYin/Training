package com.training.generic;

import java.util.Comparator;

/**
 * @Description Comparator by length
 * @Author Tyler Yin
 * @Create 2017-11-12 18:11
 **/
public class ComparatorByLength implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int temp = o1.length() - o2.length();
        return temp == 0 ? o1.compareTo(o2) : temp;
    }
}
