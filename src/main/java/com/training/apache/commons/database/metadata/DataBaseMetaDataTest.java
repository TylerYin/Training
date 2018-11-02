package com.training.apache.commons.database.metadata;

import com.util.JdbcUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Tyler Yin
 */
public class DataBaseMetaDataTest {

    public static void main(String[] args) throws SQLException {
        Connection conn = JdbcUtils.getConnection();

        //获取DataBaseMetaData
        DatabaseMetaData dmd = conn.getMetaData();

        //获取驱动名称
        String driverName = dmd.getDriverName();
        System.out.println(driverName);

        //获取用户名
        String userName = dmd.getUserName();
        System.out.println(userName);

        //获取url
        String url = dmd.getURL();
        System.out.println(url);

        //获取数据库名称
        String databaseProductName = dmd.getDatabaseProductName();
        System.out.println(databaseProductName);

        //获取数据库版本
        String databaseProductVersion =
                dmd.getDatabaseProductVersion();
        System.out.println(databaseProductVersion);

        ResultSet rs = dmd.getPrimaryKeys(null, null, "account");
        while (rs.next()) {
            System.out.println(rs.getObject(3));
        }
    }
}
