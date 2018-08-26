package com.training.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Description Try With Resource Demo
 * @Author Tyler Yin
 * @Create 2017-11-19 20:55
 **/
public class TryWithResourceTest {
    public static void main(String[] args) throws IOException {

        /**
         * try(必须是java.lang.AutoCloseable的子类对象){}//资源的自动释放。不要close。只要将需要关闭资源的部分定义try()
         * 好处：省去了调用close，省去了finally。更多的是流技术的对象都是这个接口的子类。
         * 不要以为finally就不用了。
         * 代码更为简化。
         */

        function();
        function7();
    }

    public static void function7() throws IOException {
        try (FileReader fr = new FileReader("temp.txt");
             FileWriter fw = new FileWriter("temp2.txt")) {
            int ch = fr.read();
            fw.write(ch);
            System.out.println(ch);
        }
    }

    public static void function() throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader("temp.txt");
            int ch = fr.read();
            System.out.println(ch);
        } finally {
            if (fr != null)
                try {
                    fr.close();
                } catch (IOException e) {
                    throw new RuntimeException();
                }
        }
    }
}
