package com.fwzs.qrcode;

import org.apache.commons.collections.ListUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Version: 1.0
 * @Description:
 * @Date: 2020-09-29 10:45
 * @Author: Tyler Yin
 * @Project: Training
 **/
public class CompareQrcodeTest {
    @Test
    public void testQrcode() {
        try {
            Stream<String> stream1 = Files.lines(Paths.get("src/main/java/com/fwzs/qrcode/1.txt"), Charset.defaultCharset());
            List<String> list1 = stream1.collect(Collectors.toList());

            Stream<String> stream2 = Files.lines(Paths.get("src/main/java/com/fwzs/qrcode/2.txt"), Charset.defaultCharset());
            List<String> list2 = stream2.collect(Collectors.toList());
            System.out.println("list2 大小：" + list2.size());



            System.out.println(list2.containsAll(list1));
            ListUtils.subtract(list2, list1).stream().forEach(System.out::println);

//            list2 = list2.stream().distinct().collect(Collectors.toList());
//            System.out.println("list2 大小：" + list2.size());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}