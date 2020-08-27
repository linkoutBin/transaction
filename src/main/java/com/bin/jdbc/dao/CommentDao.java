package com.bin.jdbc.dao;

import java.sql.*;
import com.bin.jdbc.connection.*;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/6/30 5:19 PM
 */
public class CommentDao {

    private static Connection connection;

    static {
        connection = JdbcConnectionUtil.getConnection("localhost", 3306);
        if (null == connection) {
            throw new RuntimeException("未能初始化数据库连接");
        }
    }

    public static void queryById(int id) {
        String sql = "select * from t_comment where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int row = resultSet.getRow();
                System.out.println("row: " + row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void save(String sql) throws SQLException {

        PreparedStatement preparedStatement = connection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "aaaa");
        preparedStatement.setInt(2, 234);
        preparedStatement.setString(3, "ddddd");
        int count = preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int key = generatedKeys.getInt(1);
            System.out.println("保存结果：" + key);
            System.out.println("count:" + count);
        }
    }

    public static Connection getNewConnection() {
        Connection connection = JdbcConnectionUtil.getConnection("localhost", 3306);
        if (null == connection) {
            throw new RuntimeException("未能获取到数据库连接");
        }
        return connection;
    }

    public static void autoCommit(boolean autoCommit) {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void release() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
