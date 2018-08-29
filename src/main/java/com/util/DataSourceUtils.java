package com.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * DataSource工具类
 *
 * @author Tyler Yin
 */
public class DataSourceUtils {

    private static BasicDataSource bds = new BasicDataSource();

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("config/jdbc");

    static {
        bds.setUrl(resourceBundle.getString("url"));
        bds.setDriverClassName(resourceBundle.getString("driver"));
        bds.setUsername(resourceBundle.getString("username"));
        bds.setPassword(resourceBundle.getString("password"));

        /**
         * 初始化时创建10个链接
         */
        bds.setInitialSize(10);

        /**
         * 设置最大连接数
         */
        bds.setMaxTotal(8);

        /**
         * 这只最大的空闲连接数
         */
        bds.setMaxIdle(5);

        /**
         * 设置最小空闲连接数字
         */
        bds.setMinIdle(1);
    }

    public static DataSource getDataSource() {
        return bds;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
