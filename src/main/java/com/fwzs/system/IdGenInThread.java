package com.fwzs.system;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 封装各种生成唯一性ID算法的工具类.
 *
 * @version 2013-01-15
 * @Author Tyler Yin
 */
public class IdGenInThread extends Thread {

    private int num = 0;

    private String name;

    private int LOOP_SIZE = 99999;

    private CountDownLatch doneSignal;

    public IdGenInThread(String name, CountDownLatch doneSignal) {
        this.name = name;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        Collection<String> ids = new ArrayList<>();
        File file = new File("D:\\Test\\twoBillionCodes" + name + ".txt");

        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                if (num >= LOOP_SIZE) {
                    num = 0;
                }
                num++;

                String currentTime = String.valueOf(System.currentTimeMillis());
                ids.add(currentTime.substring(currentTime.length() - 8) + getRandom(num, 5));
            }

            try {
                FileUtils.writeLines(file, ids, true);
                ids.clear();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        doneSignal.countDown();
    }

    /**
     * 返回长度为【strLength】的随机数，在前面补0
     *
     * @param num
     * @param strLength
     * @return
     */
    private static String getRandom(int num, int strLength) {
        // 获得随机数
        num = num << 2;
        Random rm = new Random(num);

        // 获得随机数
        long pross = (long) ((1 + rm.nextDouble()) * Math.pow(10, strLength));

        // 返回固定的长度的随机数
        return String.valueOf(pross).substring(1, strLength + 1);
    }
}
