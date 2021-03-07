package com.fwzs.qrcode;

import com.util.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Tyler Yin
 * @Create 2020-10-04 13:48
 **/
public class QrCodeUpdateScPlanTest {

    @Test
    public void testUpdateQrCodeByScPlanNewId() throws IOException {
        String qrCodes = Files.lines(Paths.get("src/main/java/com/fwzs/data/SC201002011.txt"), Charset.defaultCharset()).map(qrcode -> "'" + qrcode.split(",")[0] + "'").collect(Collectors.joining(","));
        String updateSql = "update fwm_qrcode_ht set plan_id ='10391' where qrCode in(" + qrCodes + ")";
        System.out.println(updateSql);
        System.out.println("修复完成！");
    }

    @org.junit.jupiter.api.Test
    public void testDeleteQrCode() {
        File dir = new File("C:\\Users\\Administrator\\Desktop\\udpate\\");
        if (dir.isDirectory() && dir.listFiles().length > 0) {
            Arrays.stream(dir.listFiles()).forEach(file -> {
                String fileName = file.getName().substring(0, file.getName().indexOf("."));
                String tableName = fileName.split("_")[2];
                try {
                    String qrCodes = Files.lines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).map(qrcode -> "'" + qrcode.split(",")[0] + "'").collect(Collectors.joining(","));
                    String updateSql = "Update fwm_qrcode_" + tableName + " set del_flag = '1' WHERE plan_id IS NULL AND qrCode in(" + qrCodes + ")";
                    FileUtils.writeToFile("C:\\Users\\Administrator\\Desktop\\" + fileName + ".sql", updateSql, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("SQL生成完毕！");
    }
}
