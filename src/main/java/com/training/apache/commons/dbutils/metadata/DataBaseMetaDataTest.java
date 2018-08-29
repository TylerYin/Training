package com.training.apache.commons.dbutils.metadata;

import com.util.JdbcUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseMetaDataTest {

	public static void main(String[] args) throws SQLException {
		Connection con= JdbcUtils.getConnection();
		
		//获取DataBaseMetaData
		DatabaseMetaData dmd = con.getMetaData();
		//
		// String driverName = dmd.getDriverName(); //获取驱动名称
		// System.out.println(driverName);
		//
		// String userName = dmd.getUserName();//获取用户名
		// System.out.println(userName);
		//
		// String url = dmd.getURL();//获取url
		// System.out.println(url);
		//
		// String databaseProductName = dmd.getDatabaseProductName(); //获取数据库名称
		// System.out.println(databaseProductName);
		//
		// String databaseProductVersion =
		// dmd.getDatabaseProductVersion();//获取数据库版本.
		// System.out.println(databaseProductVersion);
		
		ResultSet rs = dmd.getPrimaryKeys(null, null, "account");
		while(rs.next()){
			System.out.println(rs.getObject(3));
		}
	}
}
