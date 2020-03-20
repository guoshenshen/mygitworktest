package com.elearning.pojo.trainEva;

import java.util.Date;

public class VUserTrain {
    private Integer operatorID;

    private Long joinType;

    private Integer trainId;

    private String trainName;

    private Integer trainWay;

    private String year;

    private String location;

    private Integer attendantCount;

    private Date startTime;

    private Date endTime;

    private Byte isStationTrain;

    private String sponsorName;

    public VUserTrain(Integer operatorID, Long joinType, Integer trainId, String trainName, Integer trainWay, String year, String location, Integer attendantCount, Date startTime, Date endTime, Byte isStationTrain, String sponsorName) {
        this.operatorID = operatorID;
        this.joinType = joinType;
        this.trainId = trainId;
        this.trainName = trainName;
        this.trainWay = trainWay;
        this.year = year;
        this.location = location;
        this.attendantCount = attendantCount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isStationTrain = isStationTrain;
        this.sponsorName = sponsorName;
    }

    public VUserTrain() {
        super();
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public Long getJoinType() {
        return joinType;
    }

    public void setJoinType(Long joinType) {
        this.joinType = joinType;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName == null ? null : trainName.trim();
    }

    public Integer getTrainWay() {
        return trainWay;
    }

    public void setTrainWay(Integer trainWay) {
        this.trainWay = trainWay;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getAttendantCount() {
        return attendantCount;
    }

    public void setAttendantCount(Integer attendantCount) {
        this.attendantCount = attendantCount;
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

    public Byte getIsStationTrain() {
        return isStationTrain;
    }

    public void setIsStationTrain(Byte isStationTrain) {
        this.isStationTrain = isStationTrain;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName == null ? null : sponsorName.trim();
    }
}