package com.training.generic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 泛型
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class GenericTest2 {

    @Test
    public void run() {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        change(arr, 1, 3);
        System.out.println(Arrays.toString(arr));

        String[] strarr = new String[]{"aa", "bb", "cc", "dd", "ee", "ff"};
        change(strarr, 1, 3);
        System.out.println(Arrays.toString(strarr));
    }

    public static <T> void change(T[] array, int start, int end) {
        T temp = array[start];
        array[start] = array[end];
        array[end] = temp;
    }

    @Test
    public void run2() {
        String[] strarr = new String[]{"aa", "bb", "cc", "dd", "ee", "ff", "hh"};
        // 打印的结果：ff ee dd cc bb aa
        reset(strarr);
        System.out.println(Arrays.toString(strarr));
    }

    /**
     * 颠倒所有的元素
     *
     * @param arr
     */
    public <T> void reset(T[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            T temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
