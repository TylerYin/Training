package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @Author Tyler Yin
 */
public class JdbcUtils {
    private static final String DRIVERCLASS;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("config/jdbc");

    static {
        DRIVERCLASS = resourceBundle.getString("driverClass");
        URL = resourceBundle.getString("url");
        USERNAME = resourceBundle.getString("username");
        PASSWORD = resourceBundle.getString("password");
    }

    static {
        try {
            // 将加载驱动操作，放置在静态代码块中.这样就保证了只加载一次.
            Class.forName(DRIVERCLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * 关闭操作
     *
     * @param con
     * @throws SQLException
     */
    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public static void closeStatement(Statement st) throws SQLException {
        if (st != null) {
            st.close();
        }
    }

    public static void closeResultSet(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }
}
