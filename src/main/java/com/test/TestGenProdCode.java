package com.test;

import com.google.common.base.Stopwatch;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestGenProdCode {

    private List<Integer> targetProductCodeList = new ArrayList<>();

    @Before
    public void mockTargetProdCode() {
        for (int i = 0; i < 1009; i++) {
            targetProductCodeList.add(i % 999 + 1);
        }
        Collections.shuffle(targetProductCodeList);
    }

    @Test
    public void findProdCode() {
        int serialNumber = 1;
        List<Integer> sourceProductCodeList = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            sourceProductCodeList.add(i);
        }

        Collection<Integer> integerList;
        if (targetProductCodeList.size() < 999) {
            integerList = CollectionUtils.subtract(sourceProductCodeList, targetProductCodeList);
            serialNumber = integerList.iterator().next();
        } else {
            integerList = targetProductCodeList;
            for (int i = 0; i < targetProductCodeList.size() / 999; i++) {
                integerList = CollectionUtils.subtract(integerList, sourceProductCodeList);
            }

            if (CollectionUtils.isNotEmpty(integerList)) {
                List<Integer> prodCodes = new ArrayList<>();
                prodCodes.addAll(integerList);
                Collections.sort(prodCodes);
                serialNumber = prodCodes.get(prodCodes.size() - 1) + 1;
            }
        }
        System.out.println("serialNumber = " + serialNumber);
    }

    @Test
    public void testGenerateQrcode() {
        List<Long> qrcodeList = new ArrayList<>();
        Stopwatch sw = Stopwatch.createStarted();
        for (int i = 0; i <= 999999; i++) {
            qrcodeList.add(System.currentTimeMillis());
        }
        sw.stop();
        List<Long> distinctList = qrcodeList.stream().distinct().collect(Collectors.toList());
        System.out.println("总共生成了：" + qrcodeList.size() + " 个编码，用时：" + sw.elapsed(TimeUnit.MILLISECONDS) + "毫秒");
        distinctList.stream().forEach(System.out::println);
    }
}
