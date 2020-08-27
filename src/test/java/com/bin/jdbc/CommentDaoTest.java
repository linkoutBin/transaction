package com.bin.jdbc;

import java.sql.*;
import com.bin.jdbc.dao.*;
import org.junit.*;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/6/30 5:42 PM
 */
public class CommentDaoTest {

    @Test
    public void testOneConnectionForCommit() throws SQLException {
        String sql = "insert into t_comment (content, user_id, audit_item_id) values (?,?,?)";
        CommentDao.autoCommit(false);
        CommentDao.save(sql);
        CommentDao.autoCommit(true);
        CommentDao.autoCommit(false);
        CommentDao.save(sql);
        CommentDao.release();
    }

    @Test
    public void testTwoConnectionForCommit() throws SQLException {
        String sql = "insert into t_comment (content, user_id, audit_item_id) values (?,?,?)";
        CommentDao.autoCommit(false);
        CommentDao.save(sql);
        Connection newConnection = CommentDao.getNewConnection();
        newConnection.setAutoCommit(false);
        PreparedStatement preparedStatement = newConnection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "this");
        preparedStatement.setInt(2, 34);
        preparedStatement.setString(3, "aaa");
        int count = preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int key = generatedKeys.getInt(1);
            System.out.println("new 保存结果：" + key);
            System.out.println("new count:" + count);
        }

        newConnection.commit();
        newConnection.close();
        CommentDao.commit();
        CommentDao.release();

    }
}
