package com.training.annotation;

/**
 * 注解测试类
 *
 * @author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class TransactionTest {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        Transaction bank = new Transaction();
        bank.transaction1("张三", "李四", 6000);

        System.out.println("============================================");

        bank.transaction2("张三", "李四", 9000);
    }
}
