package com.training.io;

import com.fwzs.system.IdGen;
import com.util.DateUtils;
import com.util.FileUtils;
import com.util.Md5Utils;
import com.util.SystemUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description File Writer Demo
 * @Author Tyler Yin
 * @Create 2017-11-19 14:45
 **/
public class FileWriterTest {
    @Test
    public void test() throws IOException {
        //演示FileWriter 用于操作文件的便捷类。
        final FileWriter fw = new FileWriter(SystemUtils.getCurrentPath(new File("."), this.getClass()) + "/fw.txt");
        //这些文字都要先编码。都写入到了流的缓冲区中。
        fw.write("你好谢谢再见");
        fw.flush();
        fw.close();

        /**
         * flush()和close()的区别？
         *
         * flush():将流中的缓冲区缓冲的数据刷新到目的地中，刷新后，流还可以继续使用。
         * close():关闭资源，但在关闭前会将缓冲区中的数据先刷新到目的地，否则丢失数据，然后在关闭流。流不可以使用。
         *
         * 如果写入数据多，一定要一边写一边刷新，最后一次可以不刷新，由close完成刷新并关闭。
         */
    }

    @Test
    public void writeFile (){
        int rowNumber = 2000000;
        String sampleLine = "10200410003899180047498809972719,7221e96af0ca40da83467e8d09907f0c,9cc40cfa2e9449b593e885f59b225bf7,7683122d5aca46b8be28ae5fde791979,ad706ec2fb0b40d5bc75698a4f208be6,15a14e460ef84aaa93acf1a393a201a4,oVocE0o31jNIdxFr72BjTTItxBzU,32,1,20190121151541352102004,1493317872201901218352629567,NULL,SUCCESS,NULL,";
        String sampleLineSuffix = ",0,0";
        Collection collection = new ArrayList<>();

        int i = 1;
        while (i++ <= rowNumber) {
            collection.add(sampleLine + DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + sampleLineSuffix);
        }

        File file  = new File ("C:\\Users\\Administrator\\Desktop\\sample5.csv");
        try {
            FileUtils.writeLines(file, collection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void md5Qrcode() throws Exception {
        int number = 20000000;
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        StopWatch stopwatch = StopWatch.createStarted();
        for (int i = 0; i < number; i++) {
            list1.add(IdGen.uuid());
        }
        stopwatch.stop();
        System.out.println(stopwatch.getTime(TimeUnit.SECONDS));

        stopwatch.reset();
        stopwatch.start();
        for (int i = 0; i < number; i++) {
            list2.add(Md5Utils.md5(IdGen.uuid()));
        }
        stopwatch.stop();
        System.out.println(stopwatch.getTime(TimeUnit.SECONDS));
    }
}
