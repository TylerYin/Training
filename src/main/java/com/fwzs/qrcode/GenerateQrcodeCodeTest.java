package com.fwzs.qrcode;

import com.util.SystemUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 生成二维码
 *
 * @Author Tyler Yin
 */
public class GenerateQrcodeCodeTest {
    @Test
    public void generate108BitCode() throws IOException {
        int counter = 0;
        List<String> list;
        File newFile;
        String template = "http://weixin.qq.com/r/GTp-Z7zEmuHlrfgE928L/16903148078942/?/0000000000/00000000/82175995";
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmssSSS");

        for (int i = 0; i < 10; i++) {
            list = new ArrayList<>();
            for (int k = 0; k < 100000; k++) {
                if (counter <= 10000) {
                    counter++;
                } else {
                    counter = 0;
                }
                list.add(template.replace("?", sdf.format(new Date()) + SystemUtils.formatNumber(7, 7, false, counter)));
            }

            newFile = new File("C:\\Users\\Administrator\\Desktop\\testdata\\data_" + i + ".txt");
            FileUtils.writeLines(newFile, list, "\r");
        }
        System.out.println("=============finish============");
    }
}
