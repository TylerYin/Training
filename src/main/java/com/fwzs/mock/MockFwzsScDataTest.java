package com.fwzs.mock;

import com.fwzs.system.IdGen;
import com.util.DateUtils;
import com.util.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Tyler
 */
public class MockFwzsScDataTest {

    /**
     * 产品
     */
    private String prod_id[] = {"6b987f1a40bc41f7999b92d0f1415264", "96cd85adc429440d97bd28d8a7ed904a"};

    /**
     * 生产线
     */
    private String scline_id[] = {"6990776b03cd4a05be4efbca4880946b", "0f8faae9bb3c4bdbb33600895523cd14"};

    /**
     * 仓库
     */
    private String warehouse_id[] = {"7fadae9dfada4cd7a22b0cf5702148ce", "ce734f31d1594fec9a19ec5ca16a2864"};

    /**
     * 仓库
     */
    private String realtime_id[] = {"266b36e2bf224e2d9c92144c0ae92ee2", "6fb1967949a5415bb31886fa443eaf7a"};

    /**
     * 创建人
     */
    private String create_by = "31e9df2f3fe14134b02fffc98dc2c94e";

    /**
     * newId
     */
    private Integer newId = 3000;

    private String filePath = "C:\\Users\\Administrator\\Desktop\\生产计划模拟数据.txt";

    private final static String[] COLOR_ARRAY = {"#434348", "#90ed7d", "#f7a35c", "#8085e9", "#f15c80", "#e4d354", "#2b908f", "#90ed7d", "#f45b5b", "#91e8e1", "#7cb5ec"};

    private File scPlanMockDataFile = new File(filePath);

    List<String> scPlanDataList = new ArrayList<>();
    List<String> scPlanProductDataList = new ArrayList<>();
    List<String> inboundDataList = new ArrayList<>();
    List<String> stockFlowDataList = new ArrayList<>();
    List<String> realtimeDataList = new ArrayList<>();


    @Test
    public void testGenerateEChartsBarData() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        List<String> productList = new ArrayList<>();
        List<String> stockList = new ArrayList<>();
        for (int i = 1; i <= 300; i++) {
            // System.out.println("[" + RandomUtils.nextInt(1, 100) + ", '产品" + i + "'],");
            productList.add("'产品" + i + "'");
            stockList.add("'" + RandomUtils.nextInt(1, 100) + "'");
        }

        System.out.println(productList.stream().collect(Collectors.joining(",")));
        System.out.println(stockList.stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testGenerateLogisticsData() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
        String logisticsNo = "";
        for (int i = 0; i < 300; i++) {
            if (i % 50 == 0) {
                logisticsNo = "SF" + generator.generate(12);
            }
            System.out.println(logisticsNo + "," + generator.generate(32) + "," + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + "," + generator.generate(8) + "," + generator.generate(9));
        }
    }

    @Test
    public void testReadProductName() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\product_nameSpec_ny.txt");
        try {
            List prodNameList = Files.lines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset()).collect(Collectors.toList());
            //System.out.println(prodNameList.stream().limit(30).collect(Collectors.joining(",")));

            List<String> test = new ArrayList<>();
            for (int i = 0; i < prodNameList.size(); i++) {
                test.add("{value:" + RandomUtils.nextInt(1, 100) + ",itemStyle:{color:'" + COLOR_ARRAY[i % 11] + "'}}");
            }

            System.out.println(test.stream().collect(Collectors.joining(",")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGenerateMockData() {
        LocalDateTime beginLocalDateTime;
        LocalDateTime endLocalDateTime;

        String scPlanId;
        String scPlanProductId;
        String inBoundId;
        String stockFlowId;
        String createDate;

        Integer planNumber;
        FileUtils.deleteFile(filePath);

        for (int i = 0; i <= 1; i++) {
            beginLocalDateTime = LocalDateTime.of(2019, 1, 1, 0, 0, 0);
            endLocalDateTime = LocalDateTime.of(2019, 7, 1, 0, 0, 0);

            scPlanDataList.clear();
            scPlanProductDataList.clear();
            inboundDataList.clear();
            stockFlowDataList.clear();
            realtimeDataList.clear();

            while (!beginLocalDateTime.equals(endLocalDateTime)) {
                scPlanId = IdGen.uuid();
                scPlanProductId = IdGen.uuid();
                inBoundId = IdGen.uuid();
                stockFlowId = IdGen.uuid();

                createDate = beginLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String batchNo = beginLocalDateTime.format(DateTimeFormatter.BASIC_ISO_DATE) + i + "001";
                String scPlanNo = "SC" + batchNo;

                ZoneId zone = ZoneId.systemDefault();
                Instant instant = beginLocalDateTime.atZone(zone).toInstant();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(Date.from(instant));

                Integer day = calendar.get(Calendar.DAY_OF_MONTH);
                Integer week = calendar.get(Calendar.WEEK_OF_YEAR);
                Integer month = calendar.get(Calendar.MONTH) + 1;
                Integer quarter = DateUtils.getQuarter(calendar);
                Integer year = calendar.get(Calendar.YEAR);

                if (3 == month) {
                    planNumber = generatePlanNumber(true);
                } else {
                    planNumber = generatePlanNumber(false);
                }

                scPlanDataList.add(mockScPlanData(scPlanId, newId++, scPlanNo, createDate, scline_id[i]));
                scPlanProductDataList.add(mockScPlanProductData(scPlanProductId, scPlanId, prod_id[i], batchNo, planNumber, planNumber, warehouse_id[i]));
                inboundDataList.add(mockInboundData(inBoundId, scPlanId, prod_id[i], warehouse_id[i], planNumber, createDate));

                stockFlowDataList.add(mockStockFlowData(stockFlowId, scPlanNo, warehouse_id[i], prod_id[i], planNumber, day, week, month, quarter, year, createDate));
                realtimeDataList.add(mockRealtimeData(realtime_id[i], planNumber));

                beginLocalDateTime = beginLocalDateTime.plusDays(1);
            }

//            try {
//                FileUtils.writeStringToFile(scPlanMockDataFile, "===========第" + i + "组模拟数据============" + "\r\n", "utf-8", true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            saveMockData();
        }
    }

    private String mockScPlanData(String id, Integer newId, String planNo, String createDate, String lineId) {
        String insert = "INSERT INTO sc_plan (`id`,`new_id`,`plan_no`,`made_date`,`lline_id`,`status`,`qc_by`,`create_by`,`create_date`,`del_flag`) " +
                " VALUES('" + id + "','" + newId + "','" + planNo + "','" + createDate + "','" + lineId + "','6','292163cf0cb145c59122487b248eaa8f','292163cf0cb145c59122487b248eaa8f','" + createDate + "','0');";
        return insert;
    }

    private String mockScPlanProductData(String id, String planId, String prodId, String batchNo, Integer planNumber, Integer realNumber, String warehouseId) {
        String insert = "INSERT INTO sc_plan_product (`id`,`plan_id`,`prod_id`,`batch_no`,`indate`,`plan_number`,`real_number`,`warehouse_id`,`del_flag`) " +
                " VALUES('" + id + "','" + planId + "','" + prodId + "','" + batchNo + "','2020-12-31 00:00:00','" + planNumber + "','" + realNumber + "','" + warehouseId + "','0');";
        return insert;
    }

    private String mockInboundData(String id, String planId, String prodId, String warehouseId, Integer realNumber, String createDate) {
        String insert = "INSERT INTO inbound (`id`,`plan_id`,`prod_id`,`warehouse_id`,`inbound_number`,`inbound_date`,`operator`,`del_flag`) " +
                " VALUES('" + id + "','" + planId + "','" + prodId + "','" + warehouseId + "','" + realNumber + "','" + createDate + "','31e9df2f3fe14134b02fffc98dc2c94e','0');";
        return insert;
    }

    private String mockStockFlowData(String id, String planNo, String warehouseId, String prodId, Integer realNumber, Integer day, Integer week, Integer month, Integer quarter, Integer year, String createDate) {
        String insert = "INSERT INTO stock_flow (`id`,`scPlan_no`,`company_id`,`warehouse_id`,`product_id`,`quantity`,`type`,`day`,`week`,`month`,`quarter`,`year`,`create_by`,`create_date`,`del_flag`) " +
                " VALUES('" + id + "','" + planNo + "','3d2772c219694f25ac1ed9240faa7b1c','" + warehouseId + "','" + prodId + "','" + realNumber + "','1','" + day + "','" + week + "','" + month + "','" + quarter + "','" + year + "','31e9df2f3fe14134b02fffc98dc2c94e','" + createDate + "','0');";
        return insert;
    }

    private String mockRealtimeData(String id, Integer stock) {
        String update = "update stock_realtime set stock = stock + " + stock + " where id='" + id + "';";
        return update;
    }

    private Integer generatePlanNumber(Boolean isAmendment) {
        int upper = 110;
        int lower = 80;

        if (isAmendment) {
            upper = 60;
            lower = 30;
        }

        return RandomUtils.nextInt(lower, upper);
    }

    private void saveMockData() {
        try {
            //FileUtils.writeStringToFile(scPlanMockDataFile, "===========生产计划测试数据============" + "\r\n", "utf-8", true);
            FileUtils.writeLines(scPlanMockDataFile, scPlanDataList, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // FileUtils.writeStringToFile(scPlanMockDataFile, "===========生产计划产品测试数据============" + "\r\n", "utf-8", true);
            FileUtils.writeLines(scPlanMockDataFile, scPlanProductDataList, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //FileUtils.writeStringToFile(scPlanMockDataFile, "===========生产计划入库测试数据============" + "\r\n", "utf-8", true);
            FileUtils.writeLines(scPlanMockDataFile, inboundDataList, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // FileUtils.writeStringToFile(scPlanMockDataFile, "===========库存流水测试数据============" + "\r\n", "utf-8", true);
            FileUtils.writeLines(scPlanMockDataFile, stockFlowDataList, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //  FileUtils.writeStringToFile(scPlanMockDataFile, "===========实时流水测试数据============" + "\r\n", "utf-8", true);
            FileUtils.writeLines(scPlanMockDataFile, realtimeDataList, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}