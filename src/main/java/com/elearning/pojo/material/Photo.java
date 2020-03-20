package com.elearning.pojo.material;

import java.util.Date;

public class Photo {
    private Long photoId;

    private String photoName;

    private String operatorName;

    private Long albumId;

    private String pictureUrl;

    private Date uploadDate;

    private String description;

    public Photo(Long photoId, String photoName, String operatorName, Long albumId, String pictureUrl, Date uploadDate, String description) {
        this.photoId = photoId;
        this.photoName = photoName;
        this.operatorName = operatorName;
        this.albumId = albumId;
        this.pictureUrl = pictureUrl;
        this.uploadDate = uploadDate;
        this.description = description;
    }

    public Photo() {
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
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