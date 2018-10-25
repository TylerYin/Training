package com.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class ReadThread extends Thread {
    private long end;
    private long start;
    private int curCount = 0;
    private int rowSize;

    private RandomAccessFile raf;
    private CountDownLatch doneSignal;

    public ReadThread(long start, long end, int rowSize, RandomAccessFile raf,
                      CountDownLatch doneSignal) {
        this.start = start;
        this.end = end;
        this.rowSize = rowSize;
        this.raf = raf;
        this.doneSignal = doneSignal;
    }

    public void run() {
        long startIndex = 0;
        if (start > 0) {
            startIndex = (start - 1) * 2 + (start - 1) * rowSize;
        }

        long totalCount = end - start;
        if (start > 0) {
            totalCount++;
        }

        try {
            String hasRead;
            raf.seek(startIndex);
            System.out.println("��ʼλ��" + start);
            for (int i = 0; i < totalCount; i++) {
                hasRead = raf.readLine();
                //saveDate(getDataSourceName(hasRead), hasRead);
                System.out.println(this.getName() + ":" + hasRead);
            }
            doneSignal.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public RandomAccessFile getRaf() {
        return raf;
    }

    public void setRaf(RandomAccessFile raf) {
        this.raf = raf;
    }

    public int getCurCount() {
        return curCount;
    }

    public void setCurCount(int curCount) {
        this.curCount = curCount;
    }

    public CountDownLatch getDoneSignal() {
        return doneSignal;
    }

    public void setDoneSignal(CountDownLatch doneSignal) {
        this.doneSignal = doneSignal;
    }

    public static String getDataSourceName(String qrCode) {
        String dataSourceName = "ds_";
        Long hashCode = hashcode(qrCode);
        return dataSourceName + (hashCode % 12);

    }

    public static long hashcode(String input) {

        long MAX_VALUE = 0xffffffffL;
        int hashvalue = 0;
        int len = input.length();
        for (int i = 0; i < len; i++) {
            hashvalue = 31 * hashvalue + input.charAt(i);
        }
        if (hashvalue < 0)
            return MAX_VALUE + hashvalue + 1;
        else
            return hashvalue;
    }
}
