package com.fwzs.drug;

import com.util.ReadExcelUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Tyler
 */
public class ProcessDrugDataTest {
    @Test
    public void testExtractDrugDataFromExcel() throws IOException {
//        int[] columnIndexes = {0, 5};
//        List<String> excelRows = ReadExcelUtils.read("src/main/java/com/fwzs/hyws/会员导出报表.xlsx", 1, columnIndexes);
//        System.out.println(excelRows);

        ReadExcelUtils.modifyExcel("src/main/java/com/fwzs/hyws/20190605100618订单明细统计.xlsx", 1);
    }
}