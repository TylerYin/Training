package com.training.thread;

import org.junit.Test;

/**
 * @Version: 1.0
 * @Description: Extends Thread Test
 * @Date: 2020-04-10 14:47
 * @Author: Tyler Yin
 * @Project: Training
 **/
public class ExtendsThreadTest {
    @Test
    public void testExtendsThread() {
        SampleThread sampleThread = new SampleThread();
        sampleThread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("Main Thread：---------" + i);
        }
    }

    class SampleThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("Runner1：" + i);
            }
        }
    }
}