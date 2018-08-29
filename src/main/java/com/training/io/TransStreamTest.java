package com.training.io;

import com.util.SystemUtils;

import java.io.*;

/**
 * @Description Trans Stream Demo
 * @Author Tyler Yin
 * @Create 2017-11-19 14:45
 **/
public class TransStreamTest {
    public static void main(String[] args) throws Exception {
        final TransStreamTest transStreamTest = new TransStreamTest();
        transStreamTest.writeCN();
        transStreamTest.readCN();

        /**
         总结：
         发现继承关系是这样的。
         OutputStreamWriter:
         |--FileWriter:

         InputStreamReader:
         |--FileReader;

         父类和子类的功能有什么区别呢？

         OutputStreamWriter和InputStreamReader是字符和字节的桥梁：也可以称之为字符转换流。
         字符转换流原理：字节流+编码表。

         FileWriter和FileReader：作为子类，仅作为操作字符文件的便捷类存在。
         当操作的字符文件，使用的是默认编码表时可以不用父类，而直接用子类就完成操作了，简化了代码。

         InputStreamReader isr = new InputStreamReader(new FileInputStream("a.txt"));//默认字符集。
         InputStreamReader isr = new InputStreamReader(new FileInputStream("a.txt"),"GBK");//指定GBK字符集。
         FileReader fr = new FileReader("a.txt");
         这三句代码的功能是一样的，其中第三句最为便捷。

         注意：一旦要指定其他编码时，绝对不能用子类，必须使用字符转换流。
         什么时候用子类呢？
         条件：
         1，操作的是文件。
         2，使用默认编码。

         字节--->字符 ： 看不懂的--->看的懂的。  需要读。输入流。 InputStreamReader
         字符--->字节 ： 看的懂的--->看不懂的。  需要写。输出流。 OutputStreamWriter
         */
    }

    public void readCN() throws IOException {
        //创建InputStreamReader对象。
        final InputStreamReader isr = new InputStreamReader(new FileInputStream(SystemUtils.getCurrentPath(new File("."), this.getClass()) + "/u8cn.txt"), "UTF-8");
        char[] buf = new char[1024];
        int len = isr.read(buf);
        System.out.println(new String(buf, 0, len));
        isr.close();
    }

    //读取中文。
    public void readCN_no() throws IOException {
        // 使用FileReader没出来，因为文件是UTF-8编码。读取UTF-8字节时，用该指定用UTF-8解码。
        // 说明需要指定码表。那就需要使用InputStreamReader。
        final FileReader fr = new FileReader(SystemUtils.getCurrentPath(new File("."), this.getClass()) + "/u8cn.txt");
        //int ch = (char)fr.read();
        //System.out.println((char)ch);
        char[] buf = new char[1024];
        int len = fr.read(buf);
        System.out.println(new String(buf, 0, len));//浣犲ソ
        fr.close();
    }

    public void writeCN() throws Exception {
        //需求：既然识别中文的码表有两个，GBK UTF-8
        //能不能将中文数据按照utf-8的方式进行文件的存储呢？
        //还能使用FileWriter吗？不能使用了，因为FileWriter中默认的是GBK
        //通过FileWriter的api描述，要指定编码表这些值，需要使用OutputStreamWriter
        //OutputStreamWriter 是字符流通向字节流的桥梁：可使用指定的 charset 将要写入流中的字符编码成字节。
        //它的作用的就是，将字符串按照指定的编码表转成字节，在使用字节流将这些字节写出去。

        final OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(SystemUtils.getCurrentPath(new File("."), this.getClass()) + "/u8cn.txt"), "utf-8");
        osw.write("你好");//写入缓冲区。
        osw.close();
    }
}
