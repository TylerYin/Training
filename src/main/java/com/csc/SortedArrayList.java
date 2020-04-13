package com.csc;

import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {

    @Override
    public boolean add(E e) {
        if (size() == 0) {
            add(0, e);
            return true;
        } else {
            int x;
            E value = e;
            for (x = size(); x > 0; x--) {
                if (value.compareTo(get(x - 1)) > 0) {
                    break;
                }
            }
            add(x, value);
            return true;
        }
    }
}