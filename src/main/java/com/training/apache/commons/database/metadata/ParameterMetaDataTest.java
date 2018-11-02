package com.training.apache.commons.database.metadata;

import com.util.JdbcUtils;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author Tyler Yin
 */
public class ParameterMetaDataTest {

    public static void main(String[] args) throws SQLException {
        Connection con = JdbcUtils.getConnection();

        String sql = "select * from account where id=? and name=?";
        PreparedStatement pst = con.prepareStatement(sql);

        // 获取一个ParameterMetaData
        ParameterMetaData pmd = pst.getParameterMetaData();

        // 获取参数个数
        int count = pmd.getParameterCount();
        System.out.println(count);
    }
}
