package com.elearning.pojo.snsManage;

import java.util.Date;

public class SnsMainPage {
    private Integer infoId;

    private Integer operatorId;

    private Integer infoType;

    private String url;

    private Date publishDate;

    private String infoFromUserId;

    private String debateRecordUserId;

    private String deleteRecordUserId;

    private Integer resourceId;

    private String infoName;

    private String content;

    public SnsMainPage(Integer infoId, Integer operatorId, Integer infoType, String url, Date publishDate, String infoFromUserId, String debateRecordUserId, String deleteRecordUserId, Integer resourceId, String infoName, String content) {
        this.infoId = infoId;
        this.operatorId = operatorId;
        this.infoType = infoType;
        this.url = url;
        this.publishDate = publishDate;
        this.infoFromUserId = infoFromUserId;
        this.debateRecordUserId = debateRecordUserId;
        this.deleteRecordUserId = deleteRecordUserId;
        this.resourceId = resourceId;
        this.infoName = infoName;
        this.content = content;
    }

    public SnsMainPage() {
        super();
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getInfoFromUserId() {
        return infoFromUserId;
    }

    public void setInfoFromUserId(String infoFromUserId) {
        this.infoFromUserId = infoFromUserId == null ? null : infoFromUserId.trim();
    }

    public String getDebateRecordUserId() {
        return debateRecordUserId;
    }

    public void setDebateRecordUserId(String debateRecordUserId) {
        this.debateRecordUserId = debateRecordUserId == null ? null : debateRecordUserId.trim();
    }

    public String getDeleteRecordUserId() {
        return deleteRecordUserId;
    }

    public void setDeleteRecordUserId(String deleteRecordUserId) {
        this.deleteRecordUserId = deleteRecordUserId == null ? null : deleteRecordUserId.trim();
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName == null ? null : infoName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}