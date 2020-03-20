package com.elearning.pojo.resourceManage;

import java.util.Date;

public class RsmRcmbook {
    private Integer bookId;

    private String bookName;

    private String author;

    private String translator;

    private String publisher;

    private String publishTime;

    private Integer pageNumber;

    private Double price;

    private String content;

    private Integer operatorId;

    private Date recommendTime;

    private String operatorName;

    private String keyword;

    private String picurl;

    private String reason;

    private Integer tenantId;

    private Integer expertAreaId;

    private Integer subExpertAreaId;

    private Integer collectedNum;

    public RsmRcmbook(Integer bookId, String bookName, String author, String translator, String publisher, String publishTime, Integer pageNumber, Double price, String content, Integer operatorId, Date recommendTime, String operatorName, String keyword, String picurl, String reason, Integer tenantId, Integer expertAreaId, Integer subExpertAreaId, Integer collectedNum) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.translator = translator;
        this.publisher = publisher;
        this.publishTime = publishTime;
        this.pageNumber = pageNumber;
        this.price = price;
        this.content = content;
        this.operatorId = operatorId;
        this.recommendTime = recommendTime;
        this.operatorName = operatorName;
        this.keyword = keyword;
        this.picurl = picurl;
        this.reason = reason;
        this.tenantId = tenantId;
        this.expertAreaId = expertAreaId;
        this.subExpertAreaId = subExpertAreaId;
        this.collectedNum = collectedNum;
    }

    public RsmRcmbook() {
        super();
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator == null ? null : translator.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime == null ? null : publishTime.trim();
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getExpertAreaId() {
        return expertAreaId;
    }

    public void setExpertAreaId(Integer expertAreaId) {
        this.expertAreaId = expertAreaId;
    }

    public Integer getSubExpertAreaId() {
        return subExpertAreaId;
    }

    public void setSubExpertAreaId(Integer subExpertAreaId) {
        this.subExpertAreaId = subExpertAreaId;
    }

    public Integer getCollectedNum() {
        return collectedNum;
    }

    public void setCollectedNum(Integer collectedNum) {
        this.collectedNum = collectedNum;
    }
}