package com.elearning.pojo.systemManage;

import java.util.Date;

public class PictureBase {
    private Integer id;

    private String picName;

    private Integer operatorId;

    private Date uploadTime;

    private Integer status;

    private String comment;

    private String pictureUrl;

    private Integer picType;

    private Integer needWords;

    public PictureBase(Integer id, String picName, Integer operatorId, Date uploadTime, Integer status, String comment, String pictureUrl, Integer picType, Integer needWords) {
        this.id = id;
        this.picName = picName;
        this.operatorId = operatorId;
        this.uploadTime = uploadTime;
        this.status = status;
        this.comment = comment;
        this.pictureUrl = pictureUrl;
        this.picType = picType;
        this.needWords = needWords;
    }

    public PictureBase() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public Integer getPicType() {
        return picType;
    }

    public void setPicType(Integer picType) {
        this.picType = picType;
    }

    public Integer getNeedWords() {
        return needWords;
    }

    public void setNeedWords(Integer needWords) {
        this.needWords = needWords;
    }
}