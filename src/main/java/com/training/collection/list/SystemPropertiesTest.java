package com.training.collection.list;

import java.util.Properties;
import java.util.Set;

/**
 * @Description System Properties Test
 * @Author Tyler Yin
 * @Create 2017-11-19 8:14
 **/
public class SystemPropertiesTest {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         * System:
         * 1，不需要实例化，都是静态的属性和方法。
         * 2，out对应标准输出流(显示器)，int属性对应的是键盘。
         * 演示一些System类中的方法。
         * currentTimeMilles:获取当前时间。可以用于计算程序运行时间只要将开始时间和结束时间相减即可。
         */

        long time = System.currentTimeMillis();
        System.out.println(time);//毫秒值

        //演示getProperties()获取系统属性集。
        final Properties prop = System.getProperties();

        //获取系统属性集中的信息，遍历Properties集合。使用map的方法没问题，但是map有泛型取出时要强转。
        //Properties有没有提供自身获取数据的方法呢？
        //获取键集合。
        final Set<String> keySet = prop.stringPropertyNames();
        for (String key : keySet) {
            String value = prop.getProperty(key);//通过键获取值。
            System.out.println(key + "::::" + value);
        }

        //获取指定信息，比如：操作系统。
        String osname = System.getProperty("os.name");
        System.out.println(osname);

        //获取系统中的行分隔符。这样该程序在移植时，很方便。不同的系统，获取该系统上行分隔符
        System.out.println("hello" + LINE_SEPARATOR + "itcast");
    }
}
