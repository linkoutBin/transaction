package com.bin.domain;

/**
 * @author xubin
 * @version 1.0
 * @since 2020/7/24 5:51 PM
 */

public class Comment {

    private Integer id;
    private Integer user_id;
    private String audit_item_id;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getAudit_item_id() {
        return audit_item_id;
    }

    public void setAudit_item_id(String audit_item_id) {
        this.audit_item_id = audit_item_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", audit_item_id='" + audit_item_id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
