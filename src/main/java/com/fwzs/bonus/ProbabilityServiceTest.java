package com.fwzs.bonus;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 抽奖概率测试类
 *
 * @Author Tyler Yin
 */
public class ProbabilityServiceTest {

    /**
     * 是否中奖
     *
     * @param probabilityPercentage
     * @return
     */
    public boolean isHit(double probabilityPercentage) {
        if (probabilityPercentage >= 100) {
            return true;
        }

        probabilityPercentage = probabilityPercentage / 100;
        return internalIsHit(probabilityPercentage);
    }

    /**
     * 是否中奖
     *
     * @param probabilityPercentage
     * @param hasHitCount
     * @param noHitCount
     * @return
     */
    public boolean isHit(double probabilityPercentage, int hasHitCount, int noHitCount) {
        if (probabilityPercentage >= 100) {
            return true;
        }

        //得到概率的百分比。
        probabilityPercentage = probabilityPercentage / 100;
        //得到总计算次数。
        double totalCount = (double) (hasHitCount + noHitCount);
        //得到当前命中的概率。
        double currentProbability = hasHitCount / totalCount;
        //如果当前命中的概率大于传入的概率则不会命中。
        if (currentProbability > probabilityPercentage) {
            return false;
        }
        return internalIsHit(probabilityPercentage);
    }

    /**
     * 是否命中
     *
     * @param probabilityPercentage
     * @return
     */
    private static boolean internalIsHit(double probabilityPercentage) {
        BigDecimal b = new BigDecimal(generateRandomValue());
        double randomNumber = b.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
        return probabilityPercentage > randomNumber;
    }

    private static double generateRandomValue() {
        Random random = new Random();
        double seed = random.nextDouble();
        while (seed <= 0.0) {
            seed = random.nextDouble();
        }
        return seed;
    }

    @Test
    public void testRandom() {
        int totalCount = 100;
        List<Integer> pers = new ArrayList<>();
        pers.add(50);
        pers.add(60);
        pers.add(70);
        pers.add(80);
        pers.add(90);

        for (int r = 0; r < pers.size(); r++) {
            float percentage = pers.get(r);
            System.out.println("测试次数设定为：" + totalCount + "，概率设定为：" + percentage + "%");
            for (int k = 0; k < 20; k++) {

                //总命中次数。
                int hitCount = 0;
                for (double i = 0; i < totalCount; i++) {
                    if (isHit(percentage, hitCount, totalCount - hitCount)) {
                        hitCount++;
                    }
                }
                System.out.println("总次数：" + totalCount + "，命中次数：" + hitCount + "，概率" + (hitCount / Float.valueOf(totalCount)) * 100 + "%");
            }
            System.out.println();
        }
    }

    @Test
    public void testRandomNew() {
        double random;
        int firstClassBouns = 0;
//        for (int i = 0; i < 1000; i++) {
//            random = RandomUtils.nextDouble(0.01, 1000);
//            if (random <= 0.07) {
//                firstClassBouns++;
//            }
//        }

        int i=0;
        while (true){
            random = RandomUtils.nextDouble(0.01, 1000);
            if(random<0.07){
                System.out.println(random);
                break;
            }
            i++;
        }
        System.out.println(++i);
    }
}
