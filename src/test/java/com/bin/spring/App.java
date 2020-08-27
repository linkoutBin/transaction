package com.bin.spring;

import java.sql.*;
import java.util.*;
import com.bin.jdbc.connection.*;
import com.bin.mybatis.*;
import com.bin.spring.dao.*;
import com.bin.spring.domain.*;
import com.bin.utils.*;
import org.apache.commons.logging.*;
import org.junit.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/7/9 3:33 PM
 */
public class App {

    private static Log logger = LogFactory.getLog(App.class);

    private ApplicationContext applicationContext;

    @Before
    public void before() {
        applicationContext = new ClassPathXmlApplicationContext("Beans.xml");
    }


    @Test
    public void test() {
        int[] nums = {1, 3, 5, 99, 10, 1, 9};
        int target = 9;
        int num1;
        Map<Integer, Integer> map = new HashMap<>();
        num1 = nums[0];
        map.put(num1, 0);
        for (int j = 1; j < nums.length; j++) {
            if (num1 + nums[j] == target) {
                System.out.println(0 + " + " + j + " = " + target);
                return;
            }
            map.put(nums[j], j);
        }
        for (int i = 1; i < nums.length; i++) {
            num1 = nums[i];
            Integer num2 = map.get(target - num1);
            if (null != num2) {
                System.out.println(i + " + " + num2 + " = " + target);
                return;
            }
        }

        System.out.println("未找到匹配项，map内容为：" + map);
    }

    @Test
    public void testReadProperties() {
        PropertyReader propertyUtil = (PropertyReader) applicationContext.getBean("propertyUtil");
        Properties properties = propertyUtil.read("db.properties");
        logger.info(properties);
        logger.debug(properties);
        logger.error(properties);
        logger.warn(properties);
    }

    @Test
    public void testWithTransaction() {
        CommentTxDao commentTxDao = (CommentTxDao) applicationContext.getBean("commentTxDao");
        commentTxDao.save();
    }

    @Test
    public void testSingleConnection() {
        CommentJdbcDAO commentJdbcTemplate = (CommentJdbcDAO) applicationContext
                .getBean("commentJdbcTemplate");
        List<Comment> allComment = commentJdbcTemplate.getAllComment();
        logger.info(allComment);
    }

    @Test
    public void testConnectionPool() {
        JDBCConnectionPoolUtil jdbcConnectionPoolUtil = (JDBCConnectionPoolUtil) applicationContext
                .getBean("connectionPool");
        Connection connection = jdbcConnectionPoolUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from t_comment");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String audit_item_id = rs.getString("audit_item_id");
                String content = rs.getString("content");
                logger.info(id + " : " + user_id + " : " + audit_item_id + " : " + content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommentMapper() {
        CommentMapper commentMapper = (CommentMapper) applicationContext.getBean("commentMapper");
        com.bin.domain.Comment comment = commentMapper.getById(2);
        logger.info(comment);
    }

    @Test
    public void testForFactoryBean() {
        String binFactoryBean = (String) applicationContext
                .getBean("binFactoryBean");
        logger.info(binFactoryBean);
    }

    @After
    public void after() {
        applicationContext = null;
    }
}
