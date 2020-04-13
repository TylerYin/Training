package com.training.thread;

import org.junit.Test;

/**
 * @Version: 1.0
 * @Description:
 * @Date: 2020-04-10 17:13
 * @Author: Tyler Yin
 * @Project: Training
 **/
public class SellTicketRunnableTest {

    @Test
    public void testSellTicket() {
        SellTicket sellTicket = new SellTicket();
        Thread sellTicketThread1 = new Thread(sellTicket);
        Thread sellTicketThread2 = new Thread(sellTicket);
        Thread sellTicketThread3 = new Thread(sellTicket);
        Thread sellTicketThread4 = new Thread(sellTicket);

        sellTicketThread1.start();
        sellTicketThread2.start();
        sellTicketThread3.start();
        sellTicketThread4.start();
    }

    /**
     * 这个类只是为了描述线程的任务，跟线程没有任何关系。
     * 所有线程共享500张票。
     */
    class SellTicket implements Runnable {
        private int num = 500;

        @Override
        public void run() {
            while (num > 0) {
                System.out.println(Thread.currentThread().getName() + "...sale..." + num--);
            }
        }
    }
}