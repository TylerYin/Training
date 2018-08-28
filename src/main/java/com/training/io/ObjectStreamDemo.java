package com.training.io;

import com.training.util.SystemUtils;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @Description Object Stream Demo
 * @Author Tyler Yin
 * @Create 2017-11-19 16:30
 **/
public class ObjectStreamDemo {
    @Test
    public void test() throws IOException, ClassNotFoundException {
        //需求：想要将封装了数据的对象进行持久化。当写入的对象很多对象会按照顺序排列，也称之为对象的序列化。
        //1,应该先有对象。Person name age。
        //2,往硬盘写数据，进行持久化，需要io技术。输出流。FileOutputStream。
        //3,在字节输出流中按照名称规律在api找到一个子类 ObjectOutputStream
        //4,在基础流对象上使用额外功能。

        final ObjectStreamDemo osd = new ObjectStreamDemo();
        osd.writeObj();

        //需求：读取已有的对象文件，并获取对象中的数据。
        //通过阅读ObjectOutputStream对象的文档，发现有一个对应的对象ObjectInputStream可以用于读区存储对象的文件
        //对象的反序列化。
        osd.readObj();
    }

    public void writeObj() throws IOException {
        final Person p = new Person("lisi", 20);
        final FileOutputStream fos = new FileOutputStream(SystemUtils.getCurrentPath(new File("."), this.getClass()) + "/obj.object");
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(p);
        oos.close();
    }

    public void readObj() throws IOException, ClassNotFoundException {
        final FileInputStream fis = new FileInputStream(SystemUtils.getCurrentPath(new File("."), this.getClass()) + "/obj.object");
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final Object obj = ois.readObject();
        System.out.println(obj.toString());
    }
}
