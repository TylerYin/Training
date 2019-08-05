package com.fwzs.system;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

/**
 * @Author Tyler Yin
 */
public class IdGenInThreadTest {

    @Test
    public void testIdGen() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        int count = 10;
        CountDownLatch doneSignal = new CountDownLatch(count);
        for (int i = 1; i <= count; i++) {
            new IdGenInThread("线程" + i, doneSignal).start();
        }

        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopWatch.stop();
        System.out.println(stopWatch.getTime());
        System.out.println("码文件生成完毕");
    }

    @Test
    public void testConsumeTime() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Collection<String> ids = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ids.add(IdGen.genNewRadom13());
        }

        stopWatch.stop();
        System.out.println(stopWatch.getTime());
        System.out.println(ids);
    }

    @Test
    public void parseDate() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(getQuarter());
        System.out.println(calendar.get(Calendar.YEAR));
    }

    private int getQuarter() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        switch (month) {
            case 0:
            case 1:
            case 2:
                return 1;
            case 3:
            case 4:
            case 5:
                return 2;
            case 6:
            case 7:
            case 8:
                return 3;
            default:
                return 4;
        }
    }

    @Test
    public void testIsNumeric(){
        String regEx = "^[1-9][0-9]*$";
        Pattern pattern = Pattern.compile(regEx);

        System.out.println(pattern.matcher("0100 ").matches());
        System.out.println(pattern.matcher("00100 ").matches());
        System.out.println(pattern.matcher(" 100 ").matches());
        System.out.println(pattern.matcher("100.44").matches());
        System.out.println(pattern.matcher(" 1000").matches());
    }
}
