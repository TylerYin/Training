package com.training.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Description random Access File Demo
 * @Author Tyler Yin
 * @Create 2017-11-19 17:32
 **/
public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        /**
         * RandomAccessFile：
         * 1，随机访问。
         * 2，操作文件。
         * 3，既可以读又可以写，
         * 4，内部维护了用于存储数据的byte数组。
         * 5，提供了一个对数组操作的文件指针。
         * 6， 文件指针可以通过getFilePointer 方法读取，并通过 seek 方法设置。
         *
         * 注意：随机读写，数据需要规律，用RandomAccessFile类需要明确要操作的数据的位置。
         */

        writeFile();
        readFile();
    }

    public static void readFile() throws IOException {
        final RandomAccessFile raf = new RandomAccessFile("tempfile\\random.txt", "r");
        //改变指针的位置，想读谁就读谁。
        raf.seek(10 * 1);

        byte[] buf = new byte[6];
        raf.read(buf);
        String name = new String(buf);
        System.out.println("name = " + name);

        int age = raf.readInt();
        System.out.println("age = " + age);

        raf.close();
    }

    public static void writeFile() throws IOException {
        final RandomAccessFile raf = new RandomAccessFile("tempfile\\random.txt", "rw");

        //写一些字符信息，姓名 +年龄。
        raf.write("张三".getBytes());
        raf.writeInt(97);//保证字节的原样性。
        raf.write("李四".getBytes());
        raf.writeInt(99);//保证字节的原样性。

        /**
         * 指针的位置变化和文件的存储格式有关系；因为我这里设置文件的存储格式编码为UTF-8
         * 故每个汉字字符会用3个字节表示，一个整型还是用4个字节表示
         * 若文件存储的编码格式为GBK，那么一个汉字在存储的时候只会占用2个字节
         */
        raf.seek(10);
        raf.write("王武".getBytes());
        raf.writeInt(102);

        System.out.println("当前文件指针位置：" + raf.getFilePointer());
        raf.close();
    }
}
