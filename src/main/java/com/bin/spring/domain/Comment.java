package com.bin.spring.domain;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/7/9 5:03 PM
 */
public class Comment {

    private String content;
    private Integer userId;
    private String auditItemId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuditItemId() {
        return auditItemId;
    }

    public void setAuditItemId(String auditItemId) {
        this.auditItemId = auditItemId;
    }

    @Override
    public String toString() {
        return "[" + content + userId + auditItemId + "]";
    }
}
