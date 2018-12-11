package com.fwzs.bonus.sg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Test1 {

    private static Random rand = new Random();

    public static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static <T> void shuffle(T[] arr) {
        int length = arr.length;
        for (int i = length; i > 0; i--) {
            int randInd = rand.nextInt(i);
            swap(arr, randInd, i - 1);
        }
    }

    public static void main(String[] args) {
        //定义中奖率分母 百分之
        int probabilityCount = 100;
        String[] prizesId = new String[probabilityCount];

        //iphone 中奖机率 10%
        //100元购物卷 中奖机率 30%
        //10元购物卷 中奖机率 50%
        //总的中奖机率是 10%+30%+50%=90%
        //剩余10%是谢谢惠顾，不中奖的

        //获取商品列表
        ArrayList<HashMap> prizeInfo = new ArrayList<HashMap>();

        HashMap map1 = new HashMap();
        map1.put("goods", "iphone");
        map1.put("Odds", 1);
        prizeInfo.add(map1);

        HashMap map2 = new HashMap();
        map2.put("goods", "100元购物券");
        map2.put("Odds", 4);
        prizeInfo.add(map2);

        HashMap map3 = new HashMap();
        map3.put("goods", "10元购物券");
        map3.put("Odds", 5);
        prizeInfo.add(map3);

        HashMap map4 = new HashMap();
        map4.put("goods", "谢谢惠顾");
        map4.put("Odds", 90);
        prizeInfo.add(map4);

        int num = 0;
        //循环所有商品
        for (HashMap prize : prizeInfo) {
            Integer probability = (Integer) prize.get("Odds");
            //循环商品概率
            for (int i = 0; i < probability; i++) {
                prizesId[num] = prize.get("goods").toString();
                num++;
            }
        }

        System.out.println(Arrays.toString(prizesId));
        shuffle(prizesId);
        shuffle(prizesId);
        shuffle(prizesId);
        System.out.println(Arrays.toString(prizesId));

        //随机一个数字
        for (int i = 0; i < 100; i++) {
            Math.random();
            int index = (int) (Math.random() * probabilityCount);
            //获取到随机商品ID
            String prizeId = prizesId[index];

            System.out.println(i + "--------------" + prizeId);

        }


    }
}
