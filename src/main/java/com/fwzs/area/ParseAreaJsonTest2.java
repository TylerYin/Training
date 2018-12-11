package com.fwzs.area;

import com.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Tyler Yin
 */
public class ParseAreaJsonTest2 {

    @Test
    public void abstractSQLFromJSONFileTest() throws SQLException {
        QueryRunner runner = new QueryRunner();
        BeanHandler<Area> beanHandler = new BeanHandler<>(Area.class);
        BeanListHandler<Area> beanListHandler = new BeanListHandler<>(Area.class);
        Connection connection = DataSourceUtils.getConnection();
        try {
            List<Area> areaList = runner.query(connection, "select id, parent_code from 2018_6_3 where type = '4'", beanListHandler);
            areaList.stream().forEach(area -> {
                try {
                    Area a = runner.query(connection, "select code from 2018_6_3 where id = '" + area.getParent_code() + "'", beanHandler);
                    if (null != a && StringUtils.isNotBlank(a.getCode())) {
                        runner.update(connection, "update 2018_6_3 set code ='" + a.getCode() + "' where id ='" + area.getId() + "'");
                    } else {
                        System.out.println("没有找到 " + area.getCode() + " 的code");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
