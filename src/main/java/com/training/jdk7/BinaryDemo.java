package com.training.jdk7;


import org.junit.Test;

/**
 * @Description Binary Demo
 * @Author Tyler Yin
 * @Create 2017-11-19 21:13
 **/
public class BinaryDemo {
    @Test
    public void test() {
        int x = 60;
        //获取60的十六进制。
        int n1 = 60 & 15;
        System.out.println((char) (n1 - 10 + 'A'));

        //便于定义二进制 0B开头。
        x = 0B111100;
        System.out.println(x);
        n1 = x & 0b1111;
        System.out.println(n1);

        //用下划线的方式对大的数据值进行分隔，便于阅读。
        int y = 21_345_678;
        System.out.println(y);

        int z = 0b111_0100_1010;
        System.out.println(z);

        double d = 1234.56_789;
        System.out.println(d);
    }
}
