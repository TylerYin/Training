package com.training.apache.commons.dbutils.mydbutils;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class MyQueryRunner {
	private DataSource ds;

	public MyQueryRunner() {
		super();
	}

	public MyQueryRunner(DataSource ds) {
		this.ds = ds;
	}

	// 执行select操作
	public <T> T query(Connection con, String sql, MyResultSetHandler<T> mrs,
			Object... params) throws SQLException {

		PreparedStatement pst = con.prepareStatement(sql); // 得到一个预处理的Statement.
		// 问题:sql语句中可能存在参数，需要对参数赋值。

		ParameterMetaData pmd = pst.getParameterMetaData();
		// 可以得到有几个参数
		int count = pmd.getParameterCount();
		for (int i = 1; i <= count; i++) {
			pst.setObject(i, params[i - 1]);
		}

		ResultSet rs = pst.executeQuery(); // 得到了结果集，要将结果集封装成用户想要的对象，但是，工具不可能知道用户需求。

		return mrs.handle(rs);
	}

	// 执行update操作
	public int update(Connection con, String sql, Object... params)
			throws SQLException {

		PreparedStatement pst = con.prepareStatement(sql); // 得到一个预处理的Statement.
		// 问题:sql语句中可能存在参数，需要对参数赋值。

		ParameterMetaData pmd = pst.getParameterMetaData();
		// 可以得到有几个参数
		int count = pmd.getParameterCount();
		for (int i = 1; i <= count; i++) {
			pst.setObject(i, params[i - 1]);
		}

		int row = pst.executeUpdate();
		// 关闭资源
		pst.close();
		return row;
	}

	public int update(String sql, Object... params) throws SQLException {
		Connection con = ds.getConnection();
		PreparedStatement pst = con.prepareStatement(sql); // 得到一个预处理的Statement.
		// 问题:sql语句中可能存在参数，需要对参数赋值。

		ParameterMetaData pmd = pst.getParameterMetaData();
		// 可以得到有几个参数
		int count = pmd.getParameterCount();
		for (int i = 1; i <= count; i++) {
			pst.setObject(i, params[i - 1]);
		}

		int row = pst.executeUpdate();
		// 关闭资源
		pst.close();
		con.close();
		return row;
	}
}
