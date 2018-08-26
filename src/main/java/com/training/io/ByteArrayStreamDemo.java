package com.training.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @Description Byte Array Stream Demo
 * @Author Tyler Yin
 * @Create 2017-11-19 17:32
 **/
public class ByteArrayStreamDemo {
    public static void main(String[] args) {
        //源和目的都内存的读写过程。

        //源：内存。
        ByteArrayInputStream bis = new ByteArrayInputStream("abcde".getBytes());//用流的读写思想操作数组中的数据。

        //byte[] buf = "abcde".getBytes();
        //for(byte b : buf){
        //	bos.write(b);
        //}

        //目的：内存。
        ByteArrayOutputStream bos = new ByteArrayOutputStream();//内部有一个可自动增长的数组。

        //不断的读写。
        int ch = 0;
        while ((ch = bis.read()) != -1) {
            bos.write(ch);
        }

        //因为没有调用底层资源，所以不要关闭，即使调用了close，也没有任何效果，不会抛出IOException.
        System.out.println(bos.toString());
    }
}
