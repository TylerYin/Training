package com.fwzs.bitmatrix;

import com.util.ExtractPinYinFromHanZiUtils;
import com.util.NumberFormatUtils;
import com.util.ZxingHandlerUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

/**
 * 扫描文件夹，批量生成二维码
 *
 * @Author Tyler Yin
 */
public class GenerateBitMatrixTest {

    private String jxsFilePath;

    private String qrcodeFilePath;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Before
    public void setup() {
        qrcodeFilePath = "F:\\biMatrix_Source";
        jxsFilePath = "C:\\Users\\Administrator\\Desktop\\jxs_account.txt";
    }

    @Test
    public void testGenerateBitMatrixByDirectory() {
        File file = new File(qrcodeFilePath);
        if (file.isDirectory()) {
            Arrays.stream(file.listFiles()).filter(f -> -1 == f.getAbsolutePath().indexOf(".txt.gz")).forEach(f -> deleteFolder(f.getAbsolutePath()));
        }

        if (file.isDirectory()) {
            Arrays.stream(file.listFiles()).forEach(f -> {
                String storePath = f.getParentFile().getAbsolutePath() + File.separator + f.getName().substring(0, f.getName().indexOf(".txt.gz"));
                File directory = new File(storePath);
                directory.mkdirs();
                generateMatrixByQrcode(f, storePath);
                //generateOutBoundCodeFile(f);
            });
        }
    }

    @Test
    public void testFloatDiv() {
        BigDecimal v1 = new BigDecimal(2);
        BigDecimal v2 = new BigDecimal(100);
        System.out.println(v1.divide(v2, 8, BigDecimal.ROUND_HALF_UP).toPlainString());
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
     * 根据码文件生成扫码数据
     *
     * @param file
     */
    private void generateOutBoundCodeFile(File file) {
        String line;
        String qrcode;

        int count = 1;

        int groupNumber = 5000;
        int outBoundNumber = 40;
        int generateItemsLimit = groupNumber * outBoundNumber;

        String codeType = "0";
        String prodCode = "004";

        List<String> list = new ArrayList<>();
        for (int i = 0; i < outBoundNumber; i++) {
            list.add("CKD180921" + NumberFormatUtils.format(3, 3, false, i));
        }

        List<String> codeList = new ArrayList<>(groupNumber);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new GZIPInputStream(new FileInputStream(file)), "UTF-8"))) {
            int index = 0;
            while (null != (line = reader.readLine())) {
                if (line.contains("=")) {
                    qrcode = line.substring(line.indexOf("=") + 1);
                } else {
                    qrcode = line;
                }

                codeList.add(count + "," + qrcode + "," + sdf.format(new Date()) + "," + codeType + "," + prodCode + "," + list.get(index));
                if (count % groupNumber == 0) {
                    codeList.stream().forEach(System.out::println);
                    codeList.clear();
                    index++;
                }

                count++;
                if (count > generateItemsLimit) {
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("文件 " + file.getName() + " 生成出库扫码记录 " + count + " 个");
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

    /**
     * 测试Break用法
     */
    @Test
    public void testMethodForBreak() {
        for (int k = 200; k < 300; k++) {
            for (int i = 0; i < 100; i++) {
                if (i == 10) {
                    break;
                }
                System.out.println(i);
            }
            System.out.println(k);
            break;
        }
    }

    /**
     * 校验经销商帐号是否合法
     */
    @Test
    public void testJXSIsValid() {
        File file = new File(jxsFilePath);
        try {
            List<String> accounts = Files.lines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).distinct().collect(Collectors.toList());
            accounts.stream().forEach(account -> System.out.println(ExtractPinYinFromHanZiUtils.extractPinYinFirstSpell(account.trim()).toLowerCase()));

            System.out.println("经销商帐号共计：" + accounts.size());
            System.out.println("经销商帐号最长：" + accounts.stream().mapToInt(s -> s.trim().length()).max().getAsInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
