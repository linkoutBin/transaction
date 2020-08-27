package com.bin.jdbc.connection;

import java.sql.*;

/**
 * //TODO 需要新增从连接池获取连接方法
 * @author xubin
 * @version 1.0
 * @since 2020/6/30 5:02 PM
 */
public class JdbcConnectionUtil {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("未找到驱动类");
        }
    }

    public static Connection getConnection(String host, int port) {
        String url = "jdbc:mysql://"+ host + ":" + port + "/XslForm?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        try {
            return DriverManager.getConnection(url, "root", "123456");
        } catch (SQLException e) {
            throw new RuntimeException("数据库连接获取失败", e);
        }
    }
}
