package com.elearning.pojo.resourceManage;

import java.util.Date;

public class RsmRcmBookMaterial {
    private Integer id;

    private String materialName;

    private String url;

    private Integer bookId;

    private Date uploadTime;

    private Integer operatorId;

    private Integer downloadTimes;

    public RsmRcmBookMaterial(Integer id, String materialName, String url, Integer bookId, Date uploadTime, Integer operatorId, Integer downloadTimes) {
        this.id = id;
        this.materialName = materialName;
        this.url = url;
        this.bookId = bookId;
        this.uploadTime = uploadTime;
        this.operatorId = operatorId;
        this.downloadTimes = downloadTimes;
    }

    public RsmRcmBookMaterial() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }
}