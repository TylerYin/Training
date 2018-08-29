package com.training.apache.commons.dbutils.metadata;

import com.util.JdbcUtils;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ParameterMetaDataTest {

    public static void main(String[] args) throws SQLException {
        Connection con = JdbcUtils.getConnection();

        String sql = "select * from account where id=? and name=?";
        PreparedStatement pst = con.prepareStatement(sql);

        // 获取一个ParameterMetaData
        ParameterMetaData pmd = pst.getParameterMetaData();

        int count = pmd.getParameterCount(); // 获取参数 个数
        System.out.println(count);

        String type1 = pmd.getParameterTypeName(1);
        System.out.println(type1);
    }
}
