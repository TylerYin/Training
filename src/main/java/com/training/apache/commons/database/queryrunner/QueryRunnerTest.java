package com.training.apache.commons.database.queryrunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.apache.commons.database.domain.Account;
import com.util.DataSourceUtils;
import com.util.JdbcUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

/**
 * DBUtils测试类
 *
 * @Author Tyler Yin
 */
public class QueryRunnerTest {

    /**
     * 将结果封装到一个javaBean
     *
     * @throws SQLException
     */
    @Test
    public void testSelect() throws SQLException {
        String sql = "select * from account where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        Account a = (Account) runner.query(sql, new MyBeanHandler(Account.class), 2);
        System.out.println(a);
    }

    /**
     * 使用有参数的QueryRunner
     * 自动事务
     *
     * @throws SQLException
     */
    @Test
    public void testQueryByAutoTransaction() throws SQLException {
        String sql = "select * from account where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        List<Account> list = runner.query(sql, new BeanListHandler<>(
                Account.class), 2);
        System.out.println(list);
    }

    /**
     * 使用无参数的QueryRunner
     * 手动事务
     *
     * @throws SQLException
     */
    @Test
    public void testQueryByManualTransaction() {
        String sql = "select * from account where id>? and name=? ";
        QueryRunner runner = new QueryRunner();
        Connection conn = DataSourceUtils.getConnection();
        try {
            // 事务手动控制
            conn.setAutoCommit(false);
            List<Account> list = runner.query(conn, sql, new BeanListHandler<>(Account.class), 1, "lisi");
            System.out.println(list);
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询测试
     *
     * @throws SQLException
     */
    @Test
    public void selectListTest() throws SQLException {
        String sql = "select * from account";
        QueryRunner runner = new QueryRunner();
        Connection con = DataSourceUtils.getConnection();
        List<Account> as = runner.query(con, sql,
            (ResultSet rs) -> {
                Account account;
                List<Account> list = new ArrayList<>();
                while (rs.next()) {
                    account = new Account();
                    account.setId(rs.getInt("id"));
                    account.setName(rs.getString("name"));
                    account.setIdNumber(rs.getString("idNumber"));
                    list.add(account);
                }
            return list;
        });

        for (Account a : as) {
            System.out.println(a);
        }
        DbUtils.close(con);
    }

    /**
     * 插入测试
     *
     * @throws SQLException
     */
    @Test
    public void insertTest() throws SQLException {
        String sql = "insert into account values(null,?,?)";
        QueryRunner runner = new QueryRunner();
        int row = runner.update(JdbcUtils.getConnection(), sql, "张三", 1000d);
        System.out.println(row);
    }
}
