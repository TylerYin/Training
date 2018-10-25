package com.training.apache.commons.dbutils.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.training.apache.commons.dbutils.domain.Account;
import com.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

/**
 * @Author Tyler Yin
 */
public class QueryRunnerTest {

    /**
     * 使用无参数 的QueryRunner
     *
     * @throws SQLException
     */
    @Test
    public void testQueryRunner1() throws SQLException {
        String sql = "select * from account where id>? and name=? ";

        QueryRunner runner = new QueryRunner();
        Connection conn = DataSourceUtils.getConnection();

        // 事务手动控制
        // conn.setAutoCommit(false);

        List<Account> list = runner.query(conn, sql, new BeanListHandler<>(Account.class), 1, "lisi");
        // conn.rollback();
        System.out.println(list);
    }

    /**
     * 使用有参数 的QueryRunner
     *
     * @throws SQLException
     */
    @Test
    public void testQueryRunner2() throws SQLException {
        String sql = "select * from account where id=?";

        // 自动事务
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        List<Account> list = runner.query(sql, new BeanListHandler<>(
                Account.class), 2);
        System.out.println(list);
    }
}