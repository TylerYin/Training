package com.fwzs.area;

import com.util.DataSourceUtils;
import com.util.DateUtils;
import com.util.FileUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Tyler Yin
 */
public class ParseAreaJsonTest {

    List<String> sqls = new ArrayList<>();

    String sqlFilePathPrefix = "F:\\Area_SQL\\";

    @Test
    public void abstractSQLFromJSONFileTest() throws SQLException {
        Map<String, String> sqlMap = new HashMap<>(4);
        sqlMap.put("15000内蒙", "SELECT * FROM sys_area WHERE parent_ids LIKE '0,100000,150000,%'");
        sqlMap.put("21000辽宁", "SELECT * FROM sys_area WHERE parent_ids LIKE '0,100000,210000,%'");
        sqlMap.put("22000吉林", "SELECT * FROM sys_area WHERE parent_ids LIKE '0,100000,220000,%'");
        sqlMap.put("23000黑龙江", "SELECT * FROM sys_area WHERE parent_ids LIKE '0,100000,230000,%'");

        QueryRunner runner = new QueryRunner();
        BeanListHandler<Area> beanListHandler = new BeanListHandler<>(Area.class);
        sqlMap.keySet().stream().forEach(key -> {
            try {
                sqls.clear();
                List<Area> areaList = runner.query(DataSourceUtils.getConnection(), sqlMap.get(key), beanListHandler);
                areaList.stream().forEach(area -> {
                    File file = findTownAreaFile(area.getId());
                    if (null != file) {
                        List<String> transFileToSqls = transFileToSql(area, file);
                        if (CollectionUtils.isNotEmpty(transFileToSqls)) {
                            if (CollectionUtils.isNotEmpty(sqls)) {
                                sqls.add("");
                            }
                            sqls.addAll(transFileToSqls);
                        }
                    }
                });

                try {
                    FileUtils.writeLines(new File(sqlFilePathPrefix + key + ".sql"), "utf-8", sqls);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 查找文件
     *
     * @param fileName
     * @return
     */
    private File findTownAreaFile(String fileName) {
        String filePath = this.getClass().getClassLoader().getResource("data/town").getPath();
        File file = new File(filePath);
        List<File> listFiles = Arrays.asList(file.listFiles());
        for (File f : listFiles) {
            if (fileName.equals(f.getName().substring(0, f.getName().indexOf(".")))) {
                return f;
            }
        }
        return null;
    }

    /**
     * 根据区域JSON文件生成SQL
     *
     * @param area
     * @param file
     * @return
     */
    private List<String> transFileToSql(Area area, File file) {
        String sql;
        String json;
        List<String> insertSqls = new ArrayList<>();
        try {
            json = FileUtils.readFileToString(file, Charset.defaultCharset());
            json = json.substring(1, json.length() - 1);
            List<String> towns = Arrays.asList(json.split(","));
            for (String town : towns) {
                String id = town.split(":")[0];
                String parent_id = area.getId();
                String parent_ids = area.getParent_ids() + "," + parent_id;
                String name = town.split(":")[1];
                String sort = id;
                String code = null;
                String type = "5";
                String create_by = "1";
                String create_date = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
                String update_by = create_by;
                String update_date = create_date;
                String del_flag = "0";

                sql = "insert into sys_area(id, parent_id, parent_ids, name, sort, code, type, create_by, create_date, update_by, update_date, remarks, del_flag) " +
                        "values(" + id + ", '"
                        + parent_id + "', '"
                        + parent_ids + "', "
                        + name + ","
                        + sort + ","
                        + code + ",'"
                        + type + "','"
                        + create_by + "','"
                        + create_date + "','"
                        + update_by + "','"
                        + update_date + "',"
                        + "null,'"
                        + del_flag + "');";
                insertSqls.add(sql);
                System.out.println(town);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return insertSqls;
    }
}
