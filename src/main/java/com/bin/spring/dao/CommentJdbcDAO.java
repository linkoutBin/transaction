package com.bin.spring.dao;

import java.util.*;
import com.bin.spring.domain.*;
import org.springframework.beans.factory.*;
import org.springframework.jdbc.core.*;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/7/9 4:13 PM
 */
public class CommentJdbcDAO implements InitializingBean {

    private JdbcTemplate jdbcTemplate;

    public List<Integer> getAllCommentIds(int id) {
        String sql = "select id from t_comment";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    public List<Comment> getAllComment() {
        String sql = "select * from t_comment";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setUserId(rs.getInt("user_id"));
            comment.setAuditItemId(rs.getString("audit_item_id"));
            comment.setContent(rs.getString("content"));
            return comment;
        });
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() {
        if (Objects.isNull(getJdbcTemplate())) {
            throw new RuntimeException("未初始化jdbcTemplate");
        }
    }
}
