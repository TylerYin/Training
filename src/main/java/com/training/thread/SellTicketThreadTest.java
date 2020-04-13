package com.training.thread;

import org.junit.Test;

/**
 * @Version: 1.0
 * @Description:
 * @Date: 2020-04-10 17:08
 * @Author: Tyler Yin
 * @Project: Training
 **/
public class SellTicketThreadTest extends Thread {

    /**
     * 每个线程分别有500张票。
     */
    private int num = 500;

    @Override
    public void run() {
        while (num > 0) {
            System.out.println(Thread.currentThread().getName() + "...sale..." + num--);
        }
    }

    @Test
    public void testSellTicket() {
        SellTicketThreadTest thread1 = new SellTicketThreadTest();
        SellTicketThreadTest thread2 = new SellTicketThreadTest();
        SellTicketThreadTest thread3 = new SellTicketThreadTest();
        SellTicketThreadTest thread4 = new SellTicketThreadTest();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

