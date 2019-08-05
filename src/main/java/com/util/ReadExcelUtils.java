package com.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Tyler
 */
public class ReadExcelUtils {

    /**
     * @param fullName  文件全路径名
     * @param startRow  excel的行索引、列索引都是从0开始
     * @param delimiter 分割符
     * @throws IOException
     */
    public static void read(String fullName, int startRow, String delimiter) throws IOException {
        InputStream is = new FileInputStream(fullName);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

        String cellContent;
        StringBuilder builder = new StringBuilder();
        int columnNum = xssfSheet.getRow(0).getPhysicalNumberOfCells();

        String line;
        String fullNamePrefix = StringUtils.substringBeforeLast(fullName, "/");
        String fileName = StringUtils.substringAfterLast(fullName, "/");
        fileName = StringUtils.substringBeforeLast(fileName, ".");

        StopWatch stopWatch = StopWatch.createStarted();

        FileWriter fileWriter = new FileWriter(fullNamePrefix + "/" + fileName + ".impex");
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (int rowNum = startRow; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    builder.setLength(0);

                    for (int i = 0; i < columnNum; i++) {
                        cellContent = getValue(xssfRow.getCell(i));
                        if (StringUtils.isEmpty(cellContent)) {
                            cellContent = "";
                        }
                        builder.append(cellContent);

                        if (i < columnNum - 1) {
                            builder.append(delimiter);
                        }
                    }

                    line = builder.toString();
                    System.out.println(line);
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }

            stopWatch.stop();
            System.out.println("Excel处理完成，共耗时：" + stopWatch.getTime(TimeUnit.SECONDS) + "秒");
        }
    }

    /**
     * @param fullName      文件全路径名
     * @param startRow      excel的行索引、列索引都是从0开始
     * @param columnIndexes 分割符
     * @throws IOException
     */
    public static List<String> read(String fullName, int startRow, int... columnIndexes) throws IOException {
        InputStream is = new FileInputStream(fullName);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

        String cellContent;
        List<String> excelRows = new ArrayList<>();
        List<String> columnValues = new ArrayList<>();

        StopWatch stopWatch = StopWatch.createStarted();
        for (int rowNum = startRow; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow != null) {
                columnValues.clear();
                for (int i = 0; i < columnIndexes.length; i++) {
                    cellContent = getValue(xssfRow.getCell(columnIndexes[i]));
                    if (StringUtils.isEmpty(cellContent)) {
                        cellContent = "";
                    }
                    columnValues.add(cellContent);
                }
                excelRows.add(StringUtils.join(columnValues, ","));
            }
        }

        stopWatch.stop();
        System.out.println("Excel处理完成，共耗时：" + stopWatch.getTime(TimeUnit.SECONDS) + "秒");
        return excelRows;
    }

    public static void modifyExcel(String fullName, int startRow) {
        XSSFCell xssfCell;
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(fullName));
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

            for (int rowNum = startRow; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    xssfCell = xssfRow.getCell(8);
                    xssfCell.setCellValue(getValue(xssfCell) + "test");
                }
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getValue(XSSFCell xssfCell) {
        String xssFCellValue;
        if (xssfCell != null) {
            if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                xssFCellValue = String.valueOf(xssfCell.getBooleanCellValue());
            } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                xssFCellValue = String.valueOf(xssfCell.getNumericCellValue());
            } else {
                xssFCellValue = String.valueOf(xssfCell.getStringCellValue());
            }

            if (StringUtils.isNotBlank(xssFCellValue)) {
                return xssFCellValue.trim();
            }
        }
        return StringUtils.EMPTY;
    }
}