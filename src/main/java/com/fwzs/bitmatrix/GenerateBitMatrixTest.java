package com.fwzs.bitmatrix;

import com.util.ZxingHandlerUtils;
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

    @Test
    public void testGenerateBitMatrixBySpecifiedFile() {
        String bitMatrixFilePath = "F:\\biMatrix_Source\\CF180903003.txt.gz";

        String line;
        String qrCode;
        File file = new File(bitMatrixFilePath);
        String bitMatrixStorePath = "F:\\biMatrix_Destination\\" + file.getName().substring(0, file.getName().indexOf(".txt.gz"));

        int count = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new GZIPInputStream(new FileInputStream(file)), "UTF-8"))) {
            File destinationDirectory = new File(bitMatrixStorePath);
            if (!destinationDirectory.exists()) {
                destinationDirectory.mkdirs();
            }

            while (null != (line = reader.readLine())) {
                if (line.contains("=")) {
                    qrCode = line.substring(line.indexOf("=") + 1);
                } else {
                    qrCode = line;
                }
                count++;
                ZxingHandlerUtils.encode2(line, 400, 400, bitMatrixStorePath + "\\" + qrCode + ".jpg");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("总共生成二维码数量 ：" + count);
    }

    @Test
    public void testGenerateBitMatrixByDirectory() {
        String filePath = "F:\\biMatrix_Source";
        File file = new File(filePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            Arrays.stream(files).forEach(f -> {
                if (-1 == f.getPath().indexOf(".txt.gz")) {
                    deleteFolder(f.getPath());
                }
            });
        }

        file = new File(filePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            Arrays.stream(files).forEach(f -> {
                String destinationDirectory = f.getParentFile().getPath() + "\\" + f.getName().substring(0, f.getName().indexOf(".txt.gz"));
                File directory = new File(destinationDirectory);
                directory.mkdirs();
                generateMatrixByQrcode(f, destinationDirectory);
            });
        }
    }

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
                ZxingHandlerUtils.encode2(line, 400, 400, storePath + "\\" + qrcode + ".jpg");
                count++;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("文件 " + file.getName() + " 生成二维码 " + count + " 个");
    }

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
     * 删除目录（文件夹）以及目录下的文件
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
