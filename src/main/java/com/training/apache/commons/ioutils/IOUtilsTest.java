package com.training.apache.commons.ioutils;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * IOUtils测试类
 *
 * @Author Tyler Yin
 */
public class IOUtilsTest {

    @Test
    public void testIOUtils() {
        InputStream is = null;
        OutputStream os = null;
        String filePath = this.getClass().getClassLoader().getResource("data/").getPath();
        try {
            is = new FileInputStream(filePath + "test.txt");
            os = new FileOutputStream(filePath + "IOUtilsCopy.txt");
            IOUtils.copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGetInputStream() {
        InputStream is = null;
        try {
            is = IOUtils.toInputStream("hello beijing", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testToString() {
        InputStream is = null;
        try {
            is = IOUtils.toInputStream("hello beijing", "UTF-8");
            String str = IOUtils.toString(is, "UTF-8");
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testReadLines() {
        InputStream is = null;
        try {
            String filePath = this.getClass().getClassLoader().getResource("data/test.txt").getPath();
            is = new FileInputStream(filePath);
            List<String> lines = IOUtils.readLines(is, "UTF-8");
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}