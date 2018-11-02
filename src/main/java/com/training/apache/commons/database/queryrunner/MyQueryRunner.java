package com.training.apache.commons.database.queryrunner;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @Author Tyler Yin
 */
public class MyQueryRunner {
    private DataSource ds;

    public MyQueryRunner() {
        super();
    }

    public MyQueryRunner(DataSource ds) {
        this.ds = ds;
    }

    /**
     * 执行select操作
     *
     * @param con
     * @param sql
     * @param mrs
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    public <T> T query(Connection con, String sql, MyResultSetHandler<T> mrs,
                       Object... params) throws SQLException {
        // 得到一个预处理的Statement
        PreparedStatement pst = con.prepareStatement(sql);

        // 需要对参数赋值
        setParameters(pst, params);

        // 得到了结果集，要将结果集封装成用户想要的对象
        ResultSet rs = pst.executeQuery();
        return mrs.handle(rs);
    }

    /**
     * 执行update操作
     *
     * @param con
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int update(Connection con, String sql, Object... params)
            throws SQLException {
        PreparedStatement pst = con.prepareStatement(sql);

        // 需要对参数赋值
        setParameters(pst, params);

        int row = pst.executeUpdate();
        pst.close();
        return row;
    }

    public int update(String sql, Object... params) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        setParameters(pst, params);
        int row = pst.executeUpdate();

        pst.close();
        con.close();
        return row;
    }

    /**
     * 设置参数
     *
     * @param pst
     * @param params
     * @throws SQLException
     */
    private void setParameters(PreparedStatement pst, Object... params) throws SQLException {
        // 问题:sql语句中可能存在参数，需要对参数赋值。
        ParameterMetaData pmd = pst.getParameterMetaData();
        int count = pmd.getParameterCount();
        for (int i = 1; i <= count; i++) {
            pst.setObject(i, params[i - 1]);
        }
    }
}
