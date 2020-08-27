package com.bin.spring.dao;

import java.util.*;
import org.apache.commons.logging.*;
import org.springframework.beans.factory.*;
import org.springframework.jdbc.core.*;
import org.springframework.transaction.*;
import org.springframework.transaction.support.*;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/7/14 3:23 PM
 */
public class CommentTxDao implements InitializingBean {

    private static Log logger = LogFactory.getLog(CommentTxDao.class);

    private JdbcTemplate jdbcTemplate;

    private PlatformTransactionManager transactionManager;

    public int save() {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        int update = 0;
        try {
            String sql = "insert into t_comment (content,user_id,audit_item_id) values (?,?,?)";
            update = jdbcTemplate.update(sql, "content", 123, "auditItemId");
            if (update != 1) {
                throw new RuntimeException("设置回滚结果");
            }
            transactionManager.commit(status);
        } catch (Exception e) {
            logger.error("插入数据失败回滚");
            transactionManager.rollback(status);
        }
        return update;

    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(
            PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (Objects.isNull(getJdbcTemplate()) || Objects.isNull(getTransactionManager())) {
            throw new RuntimeException("未初始化参数");
        }
    }
}
