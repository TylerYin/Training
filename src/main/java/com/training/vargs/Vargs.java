package com.training.vargs;

/**
 * 可变参数
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class Vargs {

    public static void main(String args[]) {
        int[] paras = new int[]{1, 2, 3, 4, 5, 6, 7};
        System.out.println(sum(paras));

        System.out.println("===========================");

        System.out.println(sum1(5,paras));
    }

    //可变参数必须是方法中的最后一个参数，并且每个方法最多只能有一个可变参数
    public static int sum(int... args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }

    public static int sum1(int factor, int... args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum * factor;
    }
}
