package com.training.apache.commons.database.queryrunner;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @Author Tyler Yin
 */
public class MyBeanHandler implements MyResultSetHandler {

    private Class clazz;

    public MyBeanHandler(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object handle(ResultSet rs) throws SQLException {
        Map<String, String> map = new HashMap<>(10);
        ResultSetMetaData md = rs.getMetaData();
        int count = md.getColumnCount();
        Object object = null;
        if (rs.next()) {
            try {
                object = clazz.newInstance();
                for (int i = 1; i <= count; i++) {
                    map.put(md.getColumnName(i), rs.getString(md.getColumnName(i)));
                }
                BeanUtils.populate(object, map);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
