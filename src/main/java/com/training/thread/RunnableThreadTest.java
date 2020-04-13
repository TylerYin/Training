package com.training.thread;

import org.junit.Test;

/**
 * @Version: 1.0
 * @Description: Runnable Thread Test
 * @Date: 2020-04-10 14:48
 * @Author: Tyler Yin
 * @Project: Training
 **/
public class RunnableThreadTest {

    @Test
    public void testRunnable() {
        Runner1 runner = new Runner1();
        Thread thread = new Thread(runner);
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("Main Thread：---------" + i);
        }
    }

    class Runner1 implements Runnable {
        private int num = 50;

        @Override
        public void run() {
            while (num > 0) {
                System.out.println(Thread.currentThread().getName() + "...sale..." + num--);
            }
        }
    }
}