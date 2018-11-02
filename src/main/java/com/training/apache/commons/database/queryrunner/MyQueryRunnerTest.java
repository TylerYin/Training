package com.training.apache.commons.database.queryrunner;

import java.sql.SQLException;

import com.training.apache.commons.database.domain.Account;
import com.util.DataSourceUtils;
import org.junit.Test;

/**
 * @Author Tyler Yin
 */
public class MyQueryRunnerTest {

    /**
     * 测试update方法
     *
     * @throws SQLException
     */
    @Test
    public void updateTest() throws SQLException {
        String sql = "delete from account where id=?";
        MyQueryRunner mqr = new MyQueryRunner();
        mqr.update(DataSourceUtils.getConnection(), sql, 4);
    }

    /**
     * 测试select
     *
     * @throws SQLException
     */
    @Test
    public void selectTest() throws SQLException {
        String sql = "select * from account where id=?";
        MyQueryRunner mqr = new MyQueryRunner();
        MyBeanHandler mbh = new MyBeanHandler(Account.class);
        Account a = (Account) mqr.query(DataSourceUtils.getConnection(), sql, mbh, 2);
        System.out.println(a);
    }
}
