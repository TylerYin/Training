package com.training.apache.commons.fileutils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * FileUtils测试类
 *
 * @Author Tyler Yin
 */
public class FileUtilsTest {

    private String encoding = "UTF-8";

    /**
     * 写文件
     */
    @Test
    public void testWriteStringToFile() {
        String filePath = this.getClass().getClassLoader().getResource("data/test.txt").getPath();
        File file = new File(filePath);
        String str = "I Love Beijing，我爱北京\r\n";
        try {
            FileUtils.writeStringToFile(file, str, encoding, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     */
    @Test
    public void testReadFileToString() {
        try {
            String filePath = this.getClass().getClassLoader().getResource("data/test.txt").getPath();
            String str = FileUtils.readFileToString(new File(filePath), encoding);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试创建目录
     * 测试删除目录
     */
    @Test
    public void testDirectory() {
        RandomStringGenerator generator1 = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        String filePath = this.getClass().getClassLoader().getResource("data/").getPath();
        File file = new File(filePath + generator1.generate(5));
        try {
            // 创建目录
            FileUtils.forceMkdir(file);

            // 删除目录
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历目录
     */
    @Test
    public void testListFiles() {
        File dir = new File(".");
        String[] extensions = {"java"};
        boolean recursive = true;

        Collection<File> files = FileUtils.listFiles(dir, extensions, recursive);
        for (File file : files) {
            System.out.println(file);
        }
    }

    /**
     * 拷贝文件
     *
     * @throws IOException
     */
    @Test
    public void testCopyFile() throws IOException {
        String sourceFilePath = this.getClass().getClassLoader().getResource("data/test.txt").getPath();
        String destFilePath = this.getClass().getClassLoader().getResource("data/").getPath();
        File src = new File(sourceFilePath);
        File dest = new File(destFilePath + "/testCopy.txt");
        FileUtils.copyFile(src, dest);
    }

    /**
     * 拷贝目录
     */
    @Test
    public void testCopyDirectory() {
        File srcDir = new File(".idea");
        File destDir = new File("copyIDEAConfig");
        try {
            FileUtils.copyDirectory(srcDir, destDir, new NameFileFilter("java"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}