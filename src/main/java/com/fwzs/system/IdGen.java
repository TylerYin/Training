package com.fwzs.system;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;


/**
 * 封装各种生成唯一性ID算法的工具类.
 *
 * @version 2013-01-15
 * @Author Tyler Yin
 */
public class IdGen {

    private static SecureRandom random = new SecureRandom();

    private static int num = 0;

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 返回长度为【strLength】的随机数，在前面补0
     *
     * @param strLength
     * @return
     */
    public static String getFixLenthString(int strLength) {
        Random rm = new Random();
        // 获得随机数
        long pross = (long) ((1 + rm.nextDouble()) * Math.pow(10, strLength));
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);
        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

    /**
     * 生成不重复的21位随机码
     *
     * @return
     */
    public static String genRadom21() {
        String nanoTime = String.valueOf(System.nanoTime());

        /**
         * 为了增加不重复的概率，添加时间系数 getTime 方法返回一个整数值，这个整数代表了从 1970 年 1 月 1 日开始计算到 Date
         * 对象中的时间之间的毫秒数。
         */
        String time = String.valueOf(System.currentTimeMillis());
        return nanoTime.substring(nanoTime.length() - 4)
                + time.substring(time.length() - 9)
                + IdGen.getFixLenthString(5);
    }

    public static synchronized String genRadom13() {
        String currentTime = String.valueOf(System.currentTimeMillis());
        if (num == 99999) {
            num = 0;
        }
        num++;
        return currentTime.substring(currentTime.length() - 8) + IdGen.getRandom(num, 5);
    }

    /**
     * 返回长度为【strLength】的随机数，在前面补0
     *
     * @param num
     * @param strLength
     * @return
     */
    public static synchronized String getRandom(int num, int strLength) {
        // 获得随机数
        num = num << 2;
        Random rm = new Random(num);
        // 获得随机数
        long pross = (long) ((1 + rm.nextDouble()) * Math.pow(10, strLength));
        // 返回固定的长度的随机数
        return String.valueOf(pross).substring(1, strLength + 1);
    }
}
