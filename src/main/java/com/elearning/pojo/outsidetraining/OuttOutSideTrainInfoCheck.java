package com.elearning.pojo.outsidetraining;

import java.util.Date;

public class OuttOutSideTrainInfoCheck {
    private Integer ID;

    private Integer userID;

    private String trainName;

    private Date startTime;

    private Date endTime;

    private Double classHour;

    private String location;

    private String sponsorDept;

    private Integer approverID;

    private Date approveDate;

    private Integer approveStatus;

    private String comment;

    private String year;

    private Integer trainTypeID;

    private Integer subTrainTypeID;

    private Integer tenantId;

    private Long trainPlanID;

    private Double fee;

    private Integer outSideTrainingType;

    private String muploadlink;

    private String muploadname;

    public OuttOutSideTrainInfoCheck(Integer ID, Integer userID, String trainName, Date startTime, Date endTime, Double classHour, String location, String sponsorDept, Integer approverID, Date approveDate, Integer approveStatus, String comment, String year, Integer trainTypeID, Integer subTrainTypeID, Integer tenantId, Long trainPlanID, Double fee, Integer outSideTrainingType, String muploadlink, String muploadname) {
        this.ID = ID;
        this.userID = userID;
        this.trainName = trainName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classHour = classHour;
        this.location = location;
        this.sponsorDept = sponsorDept;
        this.approverID = approverID;
        this.approveDate = approveDate;
        this.approveStatus = approveStatus;
        this.comment = comment;
        this.year = year;
        this.trainTypeID = trainTypeID;
        this.subTrainTypeID = subTrainTypeID;
        this.tenantId = tenantId;
        this.trainPlanID = trainPlanID;
        this.fee = fee;
        this.outSideTrainingType = outSideTrainingType;
        this.muploadlink = muploadlink;
        this.muploadname = muploadname;
    }

    public OuttOutSideTrainInfoCheck() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName == null ? null : trainName.trim();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getSponsorDept() {
        return sponsorDept;
    }

    public void setSponsorDept(String sponsorDept) {
        this.sponsorDept = sponsorDept == null ? null : sponsorDept.trim();
    }

    public Integer getApproverID() {
        return approverID;
    }

    public void setApproverID(Integer approverID) {
        this.approverID = approverID;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getTrainTypeID() {
        return trainTypeID;
    }

    public void setTrainTypeID(Integer trainTypeID) {
        this.trainTypeID = trainTypeID;
    }

    public Integer getSubTrainTypeID() {
        return subTrainTypeID;
    }

    public void setSubTrainTypeID(Integer subTrainTypeID) {
        this.subTrainTypeID = subTrainTypeID;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Long getTrainPlanID() {
        return trainPlanID;
    }

    public void setTrainPlanID(Long trainPlanID) {
        this.trainPlanID = trainPlanID;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getOutSideTrainingType() {
        return outSideTrainingType;
    }

    public void setOutSideTrainingType(Integer outSideTrainingType) {
        this.outSideTrainingType = outSideTrainingType;
    }

    public String getMuploadlink() {
        return muploadlink;
    }

    public void setMuploadlink(String muploadlink) {
        this.muploadlink = muploadlink == null ? null : muploadlink.trim();
    }

    public String getMuploadname() {
        return muploadname;
    }

    public void setMuploadname(String muploadname) {
        this.muploadname = muploadname == null ? null : muploadname.trim();
    }
}