package com.elearning.pojo.courseStudy;

import java.util.Date;

public class UserTrain extends UserTrainKey {
    private Long joinType;

    private String trainName;

    private Integer trainWay;

    private String location;

    private Integer attendantCount;

    private Date startTime;

    private Date endTime;

    private Byte isStationTrain;

    private String sponsorName;

    public UserTrain(Integer operatorID, Integer train_id, String year, Long joinType, String trainName, Integer trainWay, String location, Integer attendantCount, Date startTime, Date endTime, Byte isStationTrain, String sponsorName) {
        super(operatorID, train_id, year);
        this.joinType = joinType;
        this.trainName = trainName;
        this.trainWay = trainWay;
        this.location = location;
        this.attendantCount = attendantCount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isStationTrain = isStationTrain;
        this.sponsorName = sponsorName;
    }


    public UserTrain(Integer operatorID, Integer train_id, String year, String trainName, Integer trainWay,
                     String location, Integer attendantCount, Date startTime,
                     Date endTime, Byte isStationTrain, String sponsorName) {
        super(operatorID, train_id, year);
        this.trainName = trainName;
        this.trainWay = trainWay;
        this.location = location;
        this.attendantCount = attendantCount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isStationTrain = isStationTrain;
        this.sponsorName = sponsorName;
    }




    public UserTrain() {
        super();
    }

    public Long getJoinType() {
        return joinType;
    }

    public void setJoinType(Long joinType) {
        this.joinType = joinType;
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