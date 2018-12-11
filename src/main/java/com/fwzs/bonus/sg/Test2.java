package com.fwzs.bonus.sg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test2 {

    /**
     * @param args
     */
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
        map1.put("min", 1);
        map1.put("max", 1);
        prizeInfo.add(map1);

        HashMap map2 = new HashMap();
        map2.put("goods", "100元购物券");
        map2.put("min", 2);
        map2.put("max", 5);
        prizeInfo.add(map2);

        HashMap map3 = new HashMap();
        map3.put("goods", "10元购物券");
        map3.put("min", 6);
        map3.put("max", 10);
        prizeInfo.add(map3);

        HashMap map4 = new HashMap();
        map4.put("goods", "谢谢惠顾");
        map4.put("min", 11);
        map4.put("max", 100);
        prizeInfo.add(map4);


        //随机一个数字
        int index = (int) (Math.random() * probabilityCount);
        String strPrize = "";

        for (HashMap prize : prizeInfo) {
            Integer minNum = (Integer) prize.get("min");
            Integer maxNum = (Integer) prize.get("max");

            //校验index 再哪个商品概率中间
            if (minNum <= index && maxNum > index) {
                strPrize = prize.get("goods").toString();
                break;
            }
        }

        if (strPrize != "") {
            System.out.println(strPrize);
        } else {
            System.out.println("未中奖！");
        }
    }

}
