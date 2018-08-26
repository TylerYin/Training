package com.training.apache.commons.dbutils.mydbutils;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class MyBeanHandler implements MyResultSetHandler {

	private Class clazz;

	public MyBeanHandler(Class clazz) {
		this.clazz = clazz;
	}

	public Object handle(ResultSet rs) throws SQLException {
		Object obj = null;

		Map<String, String[]> map = new HashMap<String, String[]>();

		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();

		if (rs.next()) {
			try {
				obj = clazz.newInstance();
				for (int i = 1; i <= count; i++) {
					map.put(md.getColumnName(i),
							new String[] { rs.getString(md.getColumnName(i)) });
				}
				BeanUtils.populate(obj, map);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}

		return obj;
	}

	// 从结果集的方向去封装数据
	// public Object handle(ResultSet rs) throws SQLException {
	// Object obj = null;
	// // 1.得到结果集元数据
	// ResultSetMetaData md = rs.getMetaData();
	// // 2.得到所有字段名称
	// int count = md.getColumnCount();
	//
	// if (rs.next()) {//遍历结果集
	// try {
	// BeanInfo bif = Introspector.getBeanInfo(clazz); //得到BeanInfo
	// PropertyDescriptor[] pds = bif.getPropertyDescriptors();
	// //得到javaBean的所有属性描述器
	// obj = clazz.newInstance();
	// for (int i = 1; i <= count; i++) {
	// String name = md.getColumnName(i); //得到每一列的名称
	//
	// for(PropertyDescriptor pd:pds){
	// if(name.equals(pd.getName())){ //与javaBean的属性比较
	//
	// pd.getWriteMethod().invoke(obj,
	// rs.getObject(name));//使用setXxx方法将结果集中的字段值封装到JavaBean的对应属性上。
	// }
	// }
	//
	//
	// }
	// } catch (InstantiationException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (IntrospectionException e) {
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// }
	//
	// }
	// return obj;
	// }

	// 从javaBean 的方向去封装数据
	// public Object handle(ResultSet rs) throws SQLException {
	//
	// Object obj = null;
	// // 1.得到clazz所有bean属性.
	// try {
	//
	// BeanInfo bif = Introspector.getBeanInfo(clazz);
	//
	// PropertyDescriptor[] pds = bif.getPropertyDescriptors();
	// if (rs.next()) {
	// obj = clazz.newInstance();
	// for (PropertyDescriptor pd : pds) {
	//
	// // 得到所有属性名称
	// String name = pd.getName();
	// System.out.println(name);
	// // 得到所有属性对应的set方法
	// Method setMethod = pd.getWriteMethod();
	// if (setMethod != null) { // 就可以将class对应的写方法去掉
	//
	// setMethod.invoke(obj, rs.getObject(name));
	// }
	// }
	//
	// }
	//
	// } catch (IntrospectionException e) {
	// e.printStackTrace();
	// } catch (InstantiationException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return obj;
	// }
}
