package com.training.io;

import java.io.*;

/**
 * @Description Data Stream Demo，主要用于操作基本数据类型，并且保持字节的原样性
 * @Author Tyler Yin
 * @Create 2017-11-19 17:21
 **/
public class DataStreamTest {
    public static void main(String[] args) throws IOException {
        writeData();
        readData();
    }

    public static void readData() throws IOException {
        final FileInputStream fis = new FileInputStream("tempfile\\data.txt");

        //读取一个整数，需要额外功能。
        DataInputStream dis = new DataInputStream(fis);
        System.out.println("num = " + dis.readInt());
        dis.close();
    }

    public static void writeData() throws IOException {
        final FileOutputStream fos = new FileOutputStream("tempfile\\data.txt");
        //需要额外功能吗？需要，可以写一个基本数值的原字节不变。
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeInt(97);//00000000 00000000 00000000 01100001
        dos.close();
    }
}
