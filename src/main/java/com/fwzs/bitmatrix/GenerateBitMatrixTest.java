package com.fwzs.bitmatrix;

import com.util.ZxingHandlerUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

/**
 * 扫描文件夹，批量生成二维码
 *
 * @author Tyler Yin
 */
public class GenerateBitMatrixTest {

    private String filePath;

    @Before
    public void setup() {
        filePath = "F:\\biMatrix_Source";
    }

    @Test
    public void testGenerateBitMatrixByDirectory() {
        File file = new File(filePath);
        if (file.isDirectory()) {
            Arrays.stream(file.listFiles()).filter(f -> -1 == f.getAbsolutePath().indexOf(".txt.gz")).forEach(f -> deleteFolder(f.getAbsolutePath()));
        }

        file = new File(filePath);
        if (file.isDirectory()) {
            Arrays.stream(file.listFiles()).forEach(f -> {
                String storePath = f.getParentFile().getAbsolutePath() + File.separator + f.getName().substring(0, f.getName().indexOf(".txt.gz"));
                File directory = new File(storePath);
                directory.mkdirs();
                generateMatrixByQrcode(f, storePath);
            });
        }
    }

    /**
     * 根据码文件生成二维码图片
     *
     * @param file
     * @param storePath
     */
    private void generateMatrixByQrcode(File file, String storePath) {
        String line;
        String qrcode;
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new GZIPInputStream(new FileInputStream(file)), "UTF-8"))) {
            while (null != (line = reader.readLine())) {
                if (line.contains("=")) {
                    qrcode = line.substring(line.indexOf("=") + 1);
                } else {
                    qrcode = line;
                }
                ZxingHandlerUtils.encode2(line, 400, 400, storePath + File.separator + qrcode + ".jpg");
                count++;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("文件 " + file.getName() + " 生成二维码 " + count + " 个");
    }

    /**
     * 删除文件夹及子文件夹
     *
     * @param sPath
     * @return
     */
    private boolean deleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (!file.exists()) {
            return flag;
        } else {
            if (file.isFile()) {
                return deleteFile(sPath);
            } else {
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除目录以及目录下的文件
     */
    private boolean deleteDirectory(String sPath) {
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }

        File dirFile = new File(sPath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }

        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            return false;
        }

        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除单个文件
     */
    private boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
