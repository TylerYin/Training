package com.fwzs.area;

import com.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tyler Yin
 */
public class ParseAreaJsonTest1 {

    @Test
    public void abstractSQLFromJSONFileTest() throws SQLException {
        QueryRunner runner = new QueryRunner();
        BeanHandler<Area> beanHandler = new BeanHandler<>(Area.class);
        BeanListHandler<Area> beanListHandler = new BeanListHandler<>(Area.class);
        Connection connection = DataSourceUtils.getConnection();
        try {
            List<String> deleteList = new ArrayList<>();
            List<Area> areaList = runner.query(connection, "select id, name, code, parent_id from sys_area where type = '5'", beanListHandler);
            areaList.stream().forEach(area -> {
                try {
                    Area as = runner.query(connection, "select id from sys_area where id = '" + area.getParent_id() + "'", beanHandler);
                    if (null == as || StringUtils.isBlank(as.getId())) {
                        deleteList.add("'" + area.getId() + "'");
                        System.out.println(area.getName());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(deleteList.size());
            System.out.println(StringUtils.join(deleteList,","));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
