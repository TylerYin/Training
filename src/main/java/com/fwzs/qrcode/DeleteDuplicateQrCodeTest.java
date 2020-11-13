package com.fwzs.qrcode;

import com.util.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Version: 1.0
 * @Description:
 * @Date: 2020-10-09 9:02
 * @Author: Tyler Yin
 * @Project: Training
 **/
public class DeleteDuplicateQrCodeTest {
    @Test
    public void testDeleteQrCode() {
        File dir = new File("C:\\Users\\Administrator\\Desktop\\update_09\\");
        if (dir.isDirectory() && dir.listFiles().length > 0) {
            Arrays.stream(dir.listFiles()).forEach(file -> {
                String fileName = file.getName().substring(0, file.getName().indexOf("."));
                String tableName = fileName.split("_")[2];
                try {
                    String qrCodes = Files.lines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).map(qrcode -> "'" + qrcode.split(",")[0] + "'").collect(Collectors.joining(","));
                    String updateSql = "Update fwm_qrcode_" + tableName + " set del_flag = '1' WHERE plan_id IS NULL AND qrCode in(" + qrCodes + ")";
                    FileUtils.writeToFile("C:\\Users\\Administrator\\Desktop\\duplicateQrCodeSQL_09\\" + fileName + ".sql", updateSql, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("SQL生成完毕！");
    }

    @Test
    public void testScPlanQueue() {
        File dir = new File("C:\\Users\\Administrator\\Desktop\\test\\");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String dateNow = dtf.format(LocalDateTime.now());

        if (dir.isDirectory() && dir.listFiles().length > 0) {
            Arrays.stream(dir.listFiles()).forEach(file -> {
                String fileName = file.getName().split("_")[2];
                try {
                    File f = new File("C:\\Users\\Administrator\\Desktop\\test\\" + fileName + ".txt");
                    List<String> qrCodes = Files.lines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).map(qrcode -> qrcode.split("=")[1] + "," + dateNow + ";").collect(Collectors.toList());
                    FileUtils.writeLines(f, qrCodes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("生成完毕！");
    }
}