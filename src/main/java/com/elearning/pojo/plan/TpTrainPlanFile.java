package com.elearning.pojo.plan;

import java.util.Date;

public class TpTrainPlanFile {
    private Long fileID;

    private Long trainPlanID;

    private String fileName;

    private Date uploadTime;

    private String fileURL;

    public TpTrainPlanFile(Long fileID, Long trainPlanID, String fileName, Date uploadTime, String fileURL) {
        this.fileID = fileID;
        this.trainPlanID = trainPlanID;
        this.fileName = fileName;
        this.uploadTime = uploadTime;
        this.fileURL = fileURL;
    }

    public TpTrainPlanFile() {
        super();
    }

    public Long getFileID() {
        return fileID;
    }

    public void setFileID(Long fileID) {
        this.fileID = fileID;
    }

    public Long getTrainPlanID() {
        return trainPlanID;
    }

    public void setTrainPlanID(Long trainPlanID) {
        this.trainPlanID = trainPlanID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL == null ? null : fileURL.trim();
    }
}