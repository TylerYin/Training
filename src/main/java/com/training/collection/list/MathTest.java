package com.training.collection.list;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @Description Math thread
 * @Author Tyler Yin
 * @Create 2017-11-19 8:17
 **/
public class MathTest {
    @Test
    public void test() {
        /**
         * Math数序运算。方法都是静态的。
         * Math.PI
         */

        Math.abs(-4);

        double d1 = Math.ceil(-12.34); //获取参数右边的整数   //11 12floor 12.34  ceil13  14  15
        double d2 = Math.floor(12.34); //获取参数左边的整数。
        double d3 = Math.round(12.54); //四舍五入。

        System.out.println("d1 = " + d1);//13
        System.out.println("d2 = " + d2);//12
        System.out.println("d3 = " + d3);
        System.out.println(Math.pow(10, 3));

        final Random r = new Random(System.currentTimeMillis());
        for (int x = 0; x < 10; x++) {
            int d = (int) (Math.random() * 6 + 1);
            double d4 = Math.ceil(Math.random() * 6);
            int num = r.nextInt(6) + 1;
            System.out.println(num);
        }
    }
}

