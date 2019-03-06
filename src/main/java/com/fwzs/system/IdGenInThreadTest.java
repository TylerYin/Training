package com.fwzs.system;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

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
        for (int i = 0; i < 99999; i++) {
            ids.add(IdGen.genRadom13());
        }

        stopWatch.stop();
        System.out.println(stopWatch.getTime());
    }
}
