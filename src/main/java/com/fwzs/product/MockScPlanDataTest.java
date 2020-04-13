package com.fwzs.product;

import com.util.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 生产数据模拟
 *
 * @Author Tyler Yin
 */
public class MockScPlanDataTest {

    private String qrCodeFilePath;
    private String boxCodeFilePath;
    private String mockDataFilePath;

    private String PLAN_NO = "SC190819002";
    private String PROD_NO = "459345449436";
    private String PDA_USER_ID = "aeb3ea72bbae482ebe715c48406fc44e";

    private List<String> qrCodes = new ArrayList<>();
    private List<String> boxCodes = new ArrayList<>();
    private List<String> scPlanDataList = new ArrayList<>();

    private int end_index = 15000;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Before
    public void setup() {
        qrCodeFilePath = "C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)\\C_004_20190820085235_210000_0.txt";
        boxCodeFilePath = "C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)\\X_004_20190820085249_70000.txt";
        mockDataFilePath = "C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)\\mockDataFile_" + end_index + "_.txt";
    }

    @Test
    public void testGenerateScPlanData() throws IOException {
        File qrCodeFile = new File(qrCodeFilePath);
        List<String> qrCodeList = Files.lines(Paths.get(qrCodeFile.getAbsolutePath()), Charset.defaultCharset()).collect(Collectors.toList());
        for (String qrCode : qrCodeList) {
            if (qrCode.contains("=")) {
                qrCodes.add(qrCode.split("=")[1]);
            } else {
                qrCodes.add(qrCode);
            }
        }

        File boxCodeFile = new File(boxCodeFilePath);
        List<String> boxCodeList = Files.lines(Paths.get(boxCodeFile.getAbsolutePath()), Charset.defaultCharset()).collect(Collectors.toList());
        for (String boxCode : boxCodeList) {
            if (boxCode.contains("=")) {
                boxCodes.add(boxCode.split("=")[1]);
            } else {
                boxCodes.add(boxCode);
            }
        }

        String boxCode = "";
        for (int i = 0; i < qrCodes.size(); i++) {
            if (i > end_index - 1) {
                break;
            }

            if (i % 3 == 0) {
                boxCode = boxCodes.get(i / 3);
            }
            scPlanDataList.add(qrCodes.get(i) + "," + boxCode + "," + "null," + sdf.format(new Date()) + "," + sdf.format(new Date()) + ",null," + PLAN_NO + "," + PROD_NO + "," + PDA_USER_ID);
        }

        scPlanDataList.stream().forEach(t -> System.out.println(t));
        FileUtils.writeLines(new File(mockDataFilePath), scPlanDataList);
    }
}
