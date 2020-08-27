package com.bin.jdbc.connection;

import java.sql.*;
import javax.sql.*;
import org.apache.commons.logging.*;
import org.springframework.beans.factory.*;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/7/13 6:41 PM
 */
public class JDBCConnectionPoolUtil implements InitializingBean {

    private static Log logger = LogFactory.getLog(JDBCConnectionPoolUtil.class);

    private DataSource dataSource;

    public JDBCConnectionPoolUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("连接池获取连接失败", e);
        }
        return null;
    }

    public void releaseConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("关闭连接失败");
        }
    }

    @Override
    public void afterPropertiesSet() {
        if (null == dataSource) {
            throw new RuntimeException("为初始化数据源");
        }
    }
}
