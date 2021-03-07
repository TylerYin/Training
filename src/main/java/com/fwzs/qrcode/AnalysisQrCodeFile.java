package com.fwzs.qrcode;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.sql.DriverManager.getConnection;

/**
 * 查找无效二维码
 *
 * @Author Tyler Yin
 */
public class AnalysisQrCodeFile {

    private static int fileCount = 365;
    private static int folderCount = 0;
    private static int hasScPlanCount = 0;
    private static int notHasScPlanCount = 0;

    private static int invalidFileCount = 0;
    private static int invalidQrcodeCount = 0;

    private Pattern pattern = Pattern.compile("\\d{32}");
    private static List<String> invalidQrcodeList = new ArrayList<>();
    private DecimalFormat fnum = new DecimalFormat("##0.0000");

    private File report = new File("C:\\Users\\Administrator\\Desktop\\report_20180820.txt");

    public static void main(String[] args) throws SQLException {
        AnalysisQrCodeFile analysisQrCodeFile = new AnalysisQrCodeFile();
        Connection conn = getConnection("jdbc:mysql://localhost:3306/fwzs1?characterEncoding=utf8&useSSL=true", "root", "123456");
        analysisQrCodeFile.analysisScPlan(conn, "C:\\Users\\Administrator\\Desktop\\生产码核对 - 副本");
        //analysisQrCodeFile.analysisScPlan(conn, "C:\\Users\\Administrator\\Desktop\\生产码核对\\180314\\SC180314003.txt");
        conn.close();

        System.out.println("=============统计结果如下===============");
        System.out.println("hasScPlanCount = " + hasScPlanCount);
        System.out.println("notHasScPlanCount = " + notHasScPlanCount);
    }

    private void analysisScPlan(Connection conn, String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            folderCount++;
            System.out.println("--------------------------------文件夹：" + file.getName() + "，文件个数：" + file.list().length + "-----------------------");
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    analysisScPlan(conn, files[i].getPath());
                } else {
                    filterInvalidQrcode(files[i]);
//                    analysisFile(conn, files[i]);
                }
            }
        } else {
            filterInvalidQrcode(file);
            // analysisFile(conn, file);
        }
    }

    private void analysisFile(Connection conn, File file) {
        String line;
        int rowCount = 0;
        String originalFileName = file.getName();

        if (file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader((new FileInputStream(file)), "UTF-8"))) {
                while (null != (line = reader.readLine())) {
                    if (StringUtils.isNotBlank(line)) {
                        rowCount++;
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            String fileName = originalFileName;
            if (originalFileName.contains("_")) {
                fileName = originalFileName.substring(0, originalFileName.indexOf("_"));
            } else {
                fileName = fileName.substring(0, fileName.indexOf(".txt"));
            }

            String planId = findScPlanIdByPlanNo(conn, fileName);
            if (StringUtils.isNotBlank(planId)) {
                hasScPlanCount++;
            } else {
                notHasScPlanCount++;
            }

            planId = StringUtils.isNotBlank(planId) ? planId : "无";
            System.out.println("planId=" + planId);
            System.out.println("realNumber=" + rowCount);
            System.out.println("fileData=" + originalFileName);
            System.out.println("");
        } else {
            System.out.println("planId=无");
            System.out.println("realNumber=0");
            System.out.println("fileData=" + originalFileName);
            System.out.println("");
        }
    }

    /**
     * 过滤掉防伪码文件中的无效码，并且去掉重复码
     *
     * @param file
     * @return
     */
    private List<String> findValidAndDistinctQrcode(File file) {
        try {
            List<String> qrcodes = Files.lines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).collect(Collectors.toList());
            return qrcodes.stream().filter(qrcode -> StringUtils.isNotBlank(qrcode)).filter(qrcode -> {
                String[] qrcodeArray = qrcode.split(",");
                if (StringUtils.isNotBlank(qrcodeArray[0]) && StringUtils.isNotBlank(qrcodeArray[1]) && qrcodeArray[0].length() == 32) {
                    return true;
                } else {
                    return false;
                }
            }).map(str -> str.split(",")[0].trim()).distinct().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private List<String> findValidQrcodeByForEach(List<String> qrcodes) {
        List<String> validQrcodes = new ArrayList();
        for (String qrcode : qrcodes) {
            if (StringUtils.isBlank(qrcode)) {
                continue;
            }

            boolean isValid = false;
            String[] qrcodeArray = qrcode.split(",");
            if (qrcodeArray.length == 2 && StringUtils.isNotBlank(qrcodeArray[0]) && StringUtils.isNotBlank(qrcodeArray[1]) && qrcodeArray[0].length() == 32) {
                Matcher m = pattern.matcher(qrcode);
                if (m.find()) {
                    isValid = true;
                }
            }

            if (isValid) {
                validQrcodes.add(qrcode);
            }
        }
        return validQrcodes;
    }

    private List<String> findInvalidQrcodeByForEach(List<String> qrcodes) {
        List<String> invalidQrcodes = new ArrayList();
        for (String qrcode : qrcodes) {
            if (StringUtils.isBlank(qrcode)) {
                continue;
            }

            boolean isValid = false;
            String[] qrcodeArray = qrcode.split(",");
            if (qrcodeArray.length == 2 && StringUtils.isNotBlank(qrcodeArray[0]) && StringUtils.isNotBlank(qrcodeArray[1]) && qrcodeArray[0].length() == 32) {
                Matcher m = pattern.matcher(qrcode);
                if (m.find()) {
                    isValid = true;
                }
            }

            if (!isValid) {
                invalidQrcodes.add(qrcode);
            }
        }
        return invalidQrcodes;
    }

    private List<String> findInvalidQrcodeByStream(List<String> qrcodes) {
        List<String> invalidQrcodes = new ArrayList();
        qrcodes.stream().filter(qrcode -> StringUtils.isNotBlank(qrcode)).forEach(qrcode -> {
            boolean isValid = false;
            String[] qrcodeArray = qrcode.split(",");
            if (qrcodeArray.length == 2 && StringUtils.isNotBlank(qrcodeArray[0]) && StringUtils.isNotBlank(qrcodeArray[1]) && qrcodeArray[0].length() == 32) {
                Matcher m = pattern.matcher(qrcode);
                if (m.find()) {
                    isValid = true;
                }
            }

            if (!isValid) {
                invalidQrcodes.add(qrcode);
            }
        });
        return invalidQrcodes;
    }

    private void filterInvalidQrcode(File file) {
        List<String> filteredQrcodes = new ArrayList();
        List<String> distinctQrcodes = findValidAndDistinctQrcode(file);

        String line;
        int rowCount = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader((new FileInputStream(file)), "UTF-8"))) {
            while (null != (line = reader.readLine())) {
                if (StringUtils.isNotBlank(line)) {
                    filteredQrcodes.add(line);
                    rowCount++;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Collection<String> duplicateList = new ArrayList<>();
        if (rowCount != distinctQrcodes.size()) {
            filteredQrcodes.clear();
            distinctQrcodes.stream().forEach(code -> {
                List<String> qrcodes;
                try {
                    qrcodes = Files.lines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).collect(Collectors.toList());
                    List<String> findedQrCodeList = qrcodes.stream().filter(qrcode -> StringUtils.isNotBlank(qrcode) && qrcode.split(",").length == 2).filter(qrcode -> {
                        String[] qrcodeArray = qrcode.split(",");
                        if (StringUtils.isNotBlank(qrcodeArray[0]) && StringUtils.isNotBlank(qrcodeArray[1]) && qrcodeArray[0].length() == 32) {
                            return true;
                        } else {
                            return false;
                        }
                    }).filter(qrcode -> qrcode.split(",")[0].trim().equals(code)).collect(Collectors.toList());

                    if (CollectionUtils.isNotEmpty(findedQrCodeList)) {
                        filteredQrcodes.add(findedQrCodeList.get(0));
                        if (findedQrCodeList.size() > 1) {
                            duplicateList.add(findedQrCodeList.get(0).split(",")[0].trim());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        if (CollectionUtils.isNotEmpty(duplicateList)) {
            String newFileName = file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf(".txt")) + "_duplicate.txt";
            File newFile = new File(newFileName);
            try {
                FileUtils.writeLines(newFile, duplicateList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (CollectionUtils.isNotEmpty(filteredQrcodes)) {
            String newFileName = file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf(".txt")) + "_filtered.txt";
            File newFile = new File(newFileName);
            try {
                FileUtils.writeLines(newFile, filteredQrcodes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String result = "第 " + ++fileCount + " 个文件 " + file.getAbsolutePath().substring(file.getParent().lastIndexOf("\\") + 1) + "," +
                " 有效码共 " + filteredQrcodes.size() + " 个, 重复码共 " + duplicateList.size() + " 个";
        System.out.println(result);

        try {
            FileUtils.writeStringToFile(report, result + "\r\n", "utf-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void filterInvalidQrcode(File file, String planId) {
        fileCount++;
        List<String> qrcodes = new ArrayList<>();
        List<String> invalidQrcodes = new ArrayList();

        try {
            qrcodes = FileUtils.readLines(file, "GBK");
            invalidQrcodes = findInvalidQrcodeByForEach(qrcodes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (CollectionUtils.isNotEmpty(invalidQrcodes)) {
            String newFileName = file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf(".txt")) + "_invalid.txt";
            File newFile = new File(newFileName);
            try {
                FileUtils.writeLines(newFile, invalidQrcodes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            invalidFileCount++;

            int totalLines = qrcodes.stream().filter(str -> StringUtils.isNotBlank(str)).collect(Collectors.toList()).size();
            int validLines = totalLines - invalidQrcodes.size();
            String repairRation = fnum.format((validLines / (float) totalLines) * 100);
            String result = "第 " + invalidFileCount + " 个无效文件 " + file.getAbsolutePath().substring(file.getParent().lastIndexOf("\\") + 1)
                    + "," + " 无效码共 " + invalidQrcodes.size() + " 个，总码量共 " + totalLines + " 个，可修复率 " + repairRation + "%";
            System.out.println(result);
            try {
                FileUtils.writeStringToFile(report, result + "\r\n", "utf-8", true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (validLines > 0) {
                List<String> validQrcodes = findValidQrcodeByForEach(qrcodes);
                String name = file.getAbsolutePath().substring(file.getParent().lastIndexOf("\\") + 1);
                newFileName = "C:\\Users\\Administrator\\Desktop\\生产码修复\\" + name.substring(0, name.indexOf(".txt")) + "_" + planId + "_" + validLines + "_" + repairRation + ".txt";
                newFile = new File(newFileName);
                try {
                    FileUtils.writeLines(newFile, validQrcodes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void findScPlanInfoByPlanNos(Connection conn) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\scPlanNos.txt");
        List<String> qrcodes = null;
        try {
            qrcodes = FileUtils.readLines(file, "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        qrcodes.stream().forEach(qrcode -> {
            try {
                String sql = "SELECT id, status, operate_by, qc_by FROM sc_plan where plan_no = '" + qrcode + "'";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    System.out.println(qrcode + ", " + resultSet.getString(1) + ", "
                            + resultSet.getString(2) + ", "
                            + resultSet.getString(3) + ", "
                            + resultSet.getString(4));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private String findScPlanIdByPlanNo(Connection conn, String planNo) {
        String planId = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sql = "SELECT id FROM sc_plan where plan_no = '" + planNo + "'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                planId = resultSet.getString(1);
            }
            resultSet.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planId;
    }

    private void calculateInvalidQrcode(File file) {
        if (file.length() > 0) {
            List<String> list = new ArrayList<>();
            try {
                List<String> qrcodes = FileUtils.readLines(file, "GBK");
                for (String qrcode : qrcodes) {
                    if (StringUtils.isBlank(qrcode)) {
                        continue;
                    }

                    Matcher m = pattern.matcher(qrcode);
                    if (!m.find()) {
                        invalidQrcodeCount++;
                        list.add(qrcode);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (list.size() > 0) {
                System.out.println(list);
                invalidFileCount++;
                invalidQrcodeList.addAll(list);
            }
        }
    }

    @Test
    public void test() throws IOException {
        File originalFile = new File("C:\\Users\\Administrator\\Desktop\\生产码核对 - 副本\\171026\\SC171022013_22626.txt");
        File filteredFile = new File("C:\\Users\\Administrator\\Desktop\\生产码核对 - 副本\\171026\\SC171022013_22626_filtered.txt");


        List<String> originalFileQrcodes = Files.lines(Paths.get(originalFile.getAbsolutePath()), Charset.defaultCharset()).filter(str -> StringUtils.isNotBlank(str)).collect(Collectors.toList());
        List<String> filteredFileQrcodes = Files.lines(Paths.get(filteredFile.getAbsolutePath()), Charset.defaultCharset()).filter(str -> StringUtils.isNotBlank(str)).collect(Collectors.toList());

        Collection<String> subtract = CollectionUtils.subtract(originalFileQrcodes, filteredFileQrcodes);

        List<String> qrcodes = Files.lines(Paths.get(originalFile.getAbsolutePath()), Charset.defaultCharset())
                .filter(str -> StringUtils.isNotBlank(str)).map(str -> str.split(",")[0]).filter(str -> str.length() == 32).distinct().collect(Collectors.toList());

        System.out.println(subtract);
    }
}