package com.laughing.snake.util;

import java.sql.*;

/**
 * @author : laughing
 * @create : 2021-10-04 15:18
 * @description : 数据库连接工具
 */
public class DBUtil {
    /**
     * 数据库用户名
     */
    private static final String USERNAME = PropertiesUtil.getProperties("db.username");

    /**
     * 数据库密码
     */
    private static final String PASSWORD = PropertiesUtil.getProperties("db.password");

    /**
     * 数据库地址
     */
    private static final String URL = PropertiesUtil.getProperties("db.url");

    /**
     * 数据库连接驱动
     */
    private static final String DRIVER = PropertiesUtil.getProperties("db.driver");

    /**
     * 连接数据库
     */
    public static Connection connection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 关闭数据库连接
     * @param preparedStatement 预编译
     * @param resultSet 结果集
     * @param connection 连接
     */
    public static void closeConnect(PreparedStatement preparedStatement, ResultSet resultSet, Connection connection) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
