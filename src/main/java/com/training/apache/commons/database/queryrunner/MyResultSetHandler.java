package com.training.apache.commons.database.queryrunner;

import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Tyler Yin
 */
public interface MyResultSetHandler<T> extends ResultSetHandler {

    /**
     * handle方法
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    @Override
    T handle(ResultSet rs) throws SQLException;
}
