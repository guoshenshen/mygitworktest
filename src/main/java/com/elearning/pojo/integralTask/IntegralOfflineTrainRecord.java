package com.elearning.pojo.integralTask;

import java.util.Date;

public class IntegralOfflineTrainRecord {
    private Long id;

    private String type;

    private Long trainId;

    private Date startTime;

    private Date endTime;

    private Double classHour;

    private Long userId;

    private Date recordTime;

    private String status;

    public IntegralOfflineTrainRecord(Long id, String type, Long trainId, Date startTime, Date endTime, Double classHour, Long userId, Date recordTime, String status) {
        this.id = id;
        this.type = type;
        this.trainId = trainId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classHour = classHour;
        this.userId = userId;
        this.recordTime = recordTime;
        this.status = status;
    }

    public IntegralOfflineTrainRecord() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getClassHour() {
        return classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}