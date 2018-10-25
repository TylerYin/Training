package com.training.annotation;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 注解测试类
 *
 * @Author Tyler Yin
 * @create 2017-11-04 19:26
 **/
public class DBUtilsAnnotationTest {
    public static Connection getMySQLConnectionByParameter() throws Exception {
        final String url = "jdbc:mysql:///thread";
        final String driverClassName = "com.mysql.jdbc.Driver";
        final String username = "root";
        final String password = "090214";

        Class.forName(driverClassName);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    @DBInfo(driverClassName = "com.mysql.jdbc.Driver", url = "jdbc:mysql:///mysql", username = "root", password = "yjf090214")
    public static Connection getMyConnectionByAnnotation() throws Exception {
        final Class clazz = DBUtilsAnnotationTest.class;
        final Method method = clazz.getDeclaredMethod("getMyConnectionByAnnotation");

        final boolean isAnnotationExist = method.isAnnotationPresent(DBInfo.class);
        if (isAnnotationExist) {
            final DBInfo dbInfo = method.getAnnotation(DBInfo.class);
            final String url = dbInfo.url();
            final String driverClassName = dbInfo.driverClassName();
            final String username = dbInfo.username();
            final String password = dbInfo.password();

            Class.forName(driverClassName);
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getMyConnectionByAnnotation());
    }
}