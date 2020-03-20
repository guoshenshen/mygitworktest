package com.elearning.pojo.resourceManage;

import java.util.Date;

public class RsmMyCollectBook {
    private Integer id;

    private Integer operatorId;

    private Integer bookId;

    private String bookName;

    private Date collectTime;

    private Integer recommenderId;

    private String recommenderName;

    private Date recommendTime;

    private String publisher;

    private String picurl;

    public RsmMyCollectBook(Integer id, Integer operatorId, Integer bookId, String bookName, Date collectTime, Integer recommenderId, String recommenderName, Date recommendTime, String publisher, String picurl) {
        this.id = id;
        this.operatorId = operatorId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.collectTime = collectTime;
        this.recommenderId = recommenderId;
        this.recommenderName = recommenderName;
        this.recommendTime = recommendTime;
        this.publisher = publisher;
        this.picurl = picurl;
    }

    public RsmMyCollectBook() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Integer getRecommenderId() {
        return recommenderId;
    }

    public void setRecommenderId(Integer recommenderId) {
        this.recommenderId = recommenderId;
    }

    public String getRecommenderName() {
        return recommenderName;
    }

    public void setRecommenderName(String recommenderName) {
        this.recommenderName = recommenderName == null ? null : recommenderName.trim();
    }

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }
}