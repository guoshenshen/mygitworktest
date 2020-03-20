package com.elearning.pojo.trainEva;

import java.util.Date;

public class TeTrainEvaInfo {
    private Integer ID;

    private String name;

    private Date startTime;

    private Date endTime;

    private Integer pubStatus;

    private Long pubUserID;

    private Integer joinCount;

    private String evaType;

    private Integer finishCount;

    private Integer trainID;

    private Integer courseID;

    private String remark;

    public TeTrainEvaInfo(Integer ID, String name, Date startTime, Date endTime, Integer pubStatus, Long pubUserID, Integer joinCount, String evaType, Integer finishCount, Integer trainID, Integer courseID, String remark) {
        this.ID = ID;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pubStatus = pubStatus;
        this.pubUserID = pubUserID;
        this.joinCount = joinCount;
        this.evaType = evaType;
        this.finishCount = finishCount;
        this.trainID = trainID;
        this.courseID = courseID;
        this.remark = remark;
    }

    public TeTrainEvaInfo() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Integer getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(Integer pubStatus) {
        this.pubStatus = pubStatus;
    }

    public Long getPubUserID() {
        return pubUserID;
    }

    public void setPubUserID(Long pubUserID) {
        this.pubUserID = pubUserID;
    }

    public Integer getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(Integer joinCount) {
        this.joinCount = joinCount;
    }

    public String getEvaType() {
        return evaType;
    }

    public void setEvaType(String evaType) {
        this.evaType = evaType == null ? null : evaType.trim();
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}