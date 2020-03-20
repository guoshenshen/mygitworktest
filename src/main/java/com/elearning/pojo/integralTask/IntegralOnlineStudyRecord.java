package com.elearning.pojo.integralTask;

import java.util.Date;

public class IntegralOnlineStudyRecord {
    private Long id;

    private Long courseId;

    private String reflectWatchBeginTime;

    private String reflectWatchEndTime;

    private Long watchTotalMillons;

    private Date recordTime;

    private Long userId;

    private String dayId;

    private Integer status;

    public IntegralOnlineStudyRecord(Long id, Long courseId, String reflectWatchBeginTime, String reflectWatchEndTime, Long watchTotalMillons, Date recordTime, Long userId, String dayId, Integer status) {
        this.id = id;
        this.courseId = courseId;
        this.reflectWatchBeginTime = reflectWatchBeginTime;
        this.reflectWatchEndTime = reflectWatchEndTime;
        this.watchTotalMillons = watchTotalMillons;
        this.recordTime = recordTime;
        this.userId = userId;
        this.dayId = dayId;
        this.status = status;
    }

    public IntegralOnlineStudyRecord() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getReflectWatchBeginTime() {
        return reflectWatchBeginTime;
    }

    public void setReflectWatchBeginTime(String reflectWatchBeginTime) {
        this.reflectWatchBeginTime = reflectWatchBeginTime == null ? null : reflectWatchBeginTime.trim();
    }

    public String getReflectWatchEndTime() {
        return reflectWatchEndTime;
    }

    public void setReflectWatchEndTime(String reflectWatchEndTime) {
        this.reflectWatchEndTime = reflectWatchEndTime == null ? null : reflectWatchEndTime.trim();
    }

    public Long getWatchTotalMillons() {
        return watchTotalMillons;
    }

    public void setWatchTotalMillons(Long watchTotalMillons) {
        this.watchTotalMillons = watchTotalMillons;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId == null ? null : dayId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}