package com.fwzs.qrcode;

import com.util.DateUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 查找无效二维码
 *
 * @Author Tyler Yin
 */
public class GenerateMultiLevelQrcodeTest {

    Set<String> qrCodeSet = new HashSet<>();

    RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').build();

    @Test
    public void testGenerateMultiLevelQrcode() {
        int qrCodeNum = 18000 * 6;
        int boxCodeNum = qrCodeNum / 10;
        int bigBoxCodeNum = qrCodeNum / 450;

        List<String> qrCodeLevel1 = new ArrayList<>();
        List<String> qrCodeLevel2 = new ArrayList<>();
        List<String> qrCodeLevel3 = new ArrayList<>();

        for (int i = 0; i < qrCodeNum; i++) {
            qrCodeLevel1.add(generateUniqueQrcode());
        }

        for (int i = 0; i < boxCodeNum; i++) {
            qrCodeLevel2.add(generateUniqueQrcode());
        }

        for (int i = 0; i < bigBoxCodeNum; i++) {
            qrCodeLevel3.add(generateUniqueQrcode());
        }

        List<String> level12Codes = new ArrayList<>();
        File levelFile12 = new File("D://level12.txt");
        try {
            for (int i = 0; i < qrCodeLevel1.size(); i++) {
                level12Codes.add(qrCodeLevel1.get(i) + "," + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + "," + qrCodeLevel2.get(i / 10) + "," + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
            }
            FileUtils.writeLines(levelFile12, level12Codes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> level23Codes = new ArrayList<>();
        File levelFile23 = new File("D://level23.txt");
        try {
            for (int i = 0; i < qrCodeLevel2.size(); i++) {
                level23Codes.add(qrCodeLevel2.get(i) + "," + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + "," + qrCodeLevel3.get(i / 45) + "," + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
            }
            FileUtils.writeLines(levelFile23, level23Codes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateUniqueQrcode() {
        String qrCode = generator.generate(20);
        while (qrCodeSet.contains(qrCode)) {
            System.out.println("重复防伪码");
            qrCode = generator.generate(20);
        }
        qrCodeSet.add(qrCode);
        return qrCode;
    }
}