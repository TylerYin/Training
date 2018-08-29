package com.training.apache.commons.dbutils.mydbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.training.apache.commons.dbutils.domain.Account;
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

        Account a = mqr.query(DataSourceUtils.getConnection(), sql,
                new MyResultSetHandler<Account>() {
                    @Override
                    public Account handle(ResultSet rs) throws SQLException {
                        Account a = null;
                        if (rs.next()) {
                            a = new Account();
                            a.setId(rs.getInt("id"));
                            a.setName(rs.getString("name"));
                            a.setIdNumber(rs.getString("idNumber"));
                        }
                        return a;
                    }
                }, 2);
        System.out.println(a);
    }
}
