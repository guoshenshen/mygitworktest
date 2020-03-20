package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtMixTrainPhoto {
    private Long photoId;

    private String photoName;

    private Integer operatorId;

    private String operatorName;

    private Integer trainId;

    private String pictureUrl;

    private Date uploadDate;

    private String description;

    public MtMixTrainPhoto(Long photoId, String photoName, Integer operatorId, String operatorName, Integer trainId, String pictureUrl, Date uploadDate, String description) {
        this.photoId = photoId;
        this.photoName = photoName;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.trainId = trainId;
        this.pictureUrl = pictureUrl;
        this.uploadDate = uploadDate;
        this.description = description;
    }

    public MtMixTrainPhoto() {
        super();
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName == null ? null : photoName.trim();
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

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}