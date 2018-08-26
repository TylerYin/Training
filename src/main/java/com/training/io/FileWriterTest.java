package com.training.io;

import com.training.utils.SystemHelper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Description File Writer Demo
 * @Author Tyler Yin
 * @Create 2017-11-19 14:45
 **/
public class FileWriterTest {
    @Test
    public void test() throws IOException {
        //演示FileWriter 用于操作文件的便捷类。
        final FileWriter fw = new FileWriter(SystemHelper.getCurrentPath(new File("."), this.getClass()) + "/fw.txt");
        fw.write("你好谢谢再见");//这些文字都要先编码。都写入到了流的缓冲区中。
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
}
