package com.training.apache.commons.dbutils.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.training.apache.commons.dbutils.domain.Account;
import com.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

public class QueryRunnerTest {

	@Test
	// 使用无参数 的QueryRunner
	public void fun1() throws SQLException {
		String sql = "select * from account where id>? and name=? ";
		QueryRunner runner = new QueryRunner(); // 事务手动控制
		Connection con = DataSourceUtils.getConnection();
		// con.setAutoCommit(false);

		List<Account> list = runner.query(con, sql,
				new BeanListHandler<>(Account.class),2,"ccc");
		// con.rollback();
		System.out.println(list);
	}

	@Test
	// 使用有参数 的QueryRunner
	public void fun2() throws SQLException {
		String sql = "select * from account where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource()); // 自动事务
		List<Account> list = runner.query(sql, new BeanListHandler<>(
				Account.class),2);
		System.out.println(list);
	}
}
