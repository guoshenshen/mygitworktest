package com.elearning.pojo.resourceManage;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Set;

public class RsmRcmBookDiscuss {
    private Integer discussId;

    private Integer operatorId;

    private String operatorName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date discussTime;

    private Integer score;

    private Long bookId;

    private String bookName;

    private String picurl;

    private Integer discussType;

    private Integer parentDiscussId;

    private Integer isReply;

    private String content;

    private Long count;

    private Integer gender;

    private RsmRcmBookDiscuss parentDiscuss;

    private Set<RsmRcmBookDiscuss> discussReplies;

    public RsmRcmBookDiscuss(Integer discussId, Integer operatorId, String operatorName, Date discussTime, Integer score, Long bookId, String bookName, String picurl, Integer discussType, Integer parentDiscussId, Integer isReply, String content) {
        this.discussId = discussId;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.discussTime = discussTime;
        this.score = score;
        this.bookId = bookId;
        this.bookName = bookName;
        this.picurl = picurl;
        this.discussType = discussType;
        this.parentDiscussId = parentDiscussId;
        this.isReply = isReply;
        this.content = content;
    }

    public RsmRcmBookDiscuss() {
        super();
    }

    public Integer getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Integer discussId) {
        this.discussId = discussId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getDiscussTime() {
        return discussTime;
    }

    public void setDiscussTime(Date discussTime) {
        this.discussTime = discussTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public Integer getDiscussType() {
        return discussType;
    }

    public void setDiscussType(Integer discussType) {
        this.discussType = discussType;
    }

    public Integer getParentDiscussId() {
        return parentDiscussId;
    }

    public void setParentDiscussId(Integer parentDiscussId) {
        this.parentDiscussId = parentDiscussId;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Set<RsmRcmBookDiscuss> getDiscussReplies() {
        return discussReplies;
    }

    public void setDiscussReplies(Set<RsmRcmBookDiscuss> discussReplies) {
        this.discussReplies = discussReplies;
    }

    public RsmRcmBookDiscuss getParentDiscuss() {
        return parentDiscuss;
    }

    public void setParentDiscuss(RsmRcmBookDiscuss parentDiscuss) {
        this.parentDiscuss = parentDiscuss;
    }
}