package com.training.thread;

import java.util.concurrent.*;

/**
 * @Author Tyler Yin
 */
public class BlockingQueueTest {
    public static void main(String[] args)
            throws Exception {
        // 定义一个长度为2的阻塞队列
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        bq.put("Java");
        bq.put("Java");
        bq.put("Java");
    }
}
