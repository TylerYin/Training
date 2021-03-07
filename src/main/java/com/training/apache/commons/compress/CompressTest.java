package com.training.apache.commons.compress;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Compress测试类
 *
 * @Author Tyler Yin
 */
public class CompressTest {

    @Test
    public void testCompress() throws IOException {
        //创建压缩对象
        ZipArchiveEntry entry = new ZipArchiveEntry("CompressTest");

        //要压缩的文件
        String filePath = this.getClass().getClassLoader().getResource("data/").getPath();
        File f = new File(filePath + "test.txt");
        FileInputStream fis = new FileInputStream(f);

        //输出的对象 压缩的文件
        ZipArchiveOutputStream zipOutput = new ZipArchiveOutputStream(new File(filePath + "test.zip"));
        zipOutput.putArchiveEntry(entry);
        int i = 0, j;
        while ((j = fis.read()) != -1) {
            zipOutput.write(j);
            System.out.println(++i);
        }
        zipOutput.closeArchiveEntry();
        zipOutput.close();
        fis.close();
    }
}
