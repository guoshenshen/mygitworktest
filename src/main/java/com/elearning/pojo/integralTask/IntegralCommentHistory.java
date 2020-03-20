package com.elearning.pojo.integralTask;

import java.util.Date;

public class IntegralCommentHistory {
    private Long id;

    private Integer type;

    private String isReply;

    private String content;

    private Long bookId;

    private Long discussId;

    private Long parentDiscussId;

    private Date commentTime;

    private String status;

    private Long userId;

    public IntegralCommentHistory(Long id, Integer type, String isReply, String content, Long bookId, Long discussId, Long parentDiscussId, Date commentTime, String status, Long userId) {
        this.id = id;
        this.type = type;
        this.isReply = isReply;
        this.content = content;
        this.bookId = bookId;
        this.discussId = discussId;
        this.parentDiscussId = parentDiscussId;
        this.commentTime = commentTime;
        this.status = status;
        this.userId = userId;
    }

    public IntegralCommentHistory() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIsReply() {
        return isReply;
    }

    public void setIsReply(String isReply) {
        this.isReply = isReply == null ? null : isReply.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Long discussId) {
        this.discussId = discussId;
    }

    public Long getParentDiscussId() {
        return parentDiscussId;
    }

    public void setParentDiscussId(Long parentDiscussId) {
        this.parentDiscussId = parentDiscussId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}