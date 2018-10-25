package com.training.apache.commons.dbutils.metadata;

import com.util.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @Author Tyler Yin
 */
public class ResultSetMetaDataTest {
    public static void main(String[] args) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        ResultSet rs = con.createStatement().executeQuery(
                "select * from account");

        // 得到结果集元数据
        ResultSetMetaData rsmd = rs.getMetaData();

        // System.out.println(rsmd.getColumnCount());//获取结果集中列数量
        // System.out.println(rsmd.getColumnName(2));//获取结果集中指定列的名称.
        // System.out.println(rsmd.getColumnTypeName(3));//获取结果集中指定列的类型。

        int count = rsmd.getColumnCount();
        for (int i = 1; i <= count; i++) {
            System.out.print(rsmd.getColumnName(i) + "(" + rsmd.getColumnTypeName(i) + ")" + "\t");
        }

        while (rs.next()) {
            for (int i = 1; i <= count; i++) {
                System.out.print(rs.getObject(i) + "\t\t");
            }
        }
    }
}
