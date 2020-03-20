package com.elearning.pojo.courseStudy;

import java.util.Date;

public class TrainSummary {
    private Integer id;

    private String year;

    private Integer train_id;

    private Integer operatorId;

    private Date submitDate;

    private String summaryName;

    private String attachmentPath;

    private String orgName;

    private String parentOrgName;

    private String userName;

    private Integer isTrainEffect;

    private Integer multiplyTimes;

    private Double totalFee;

    private Integer completedNum;

    private Double keyCompletedRadio;

    private Integer totalNum;

    private Integer calibratedNum;

    private String comment;

    private Double trainPlanExeRate;

    private Double learnFinishedRate;

    private Integer needFinishedNum;

    private Double totalTime;

    private Double averageTime;

    private Integer tenantId;

    private Date lastModifiedDate;

    private Integer lastEditorOperatorId;

    private Date lastRefreshDataTime;

    public TrainSummary(Integer id, String year, Integer train_id, Integer operatorId, Date submitDate, String summaryName, String attachmentPath, String orgName, String parentOrgName, String userName, Integer isTrainEffect, Integer multiplyTimes, Double totalFee, Integer completedNum, Double keyCompletedRadio, Integer totalNum, Integer calibratedNum, String comment, Double trainPlanExeRate, Double learnFinishedRate, Integer needFinishedNum, Double totalTime, Double averageTime, Integer tenantId, Date lastModifiedDate, Integer lastEditorOperatorId, Date lastRefreshDataTime) {
        this.id = id;
        this.year = year;
        this.train_id = train_id;
        this.operatorId = operatorId;
        this.submitDate = submitDate;
        this.summaryName = summaryName;
        this.attachmentPath = attachmentPath;
        this.orgName = orgName;
        this.parentOrgName = parentOrgName;
        this.userName = userName;
        this.isTrainEffect = isTrainEffect;
        this.multiplyTimes = multiplyTimes;
        this.totalFee = totalFee;
        this.completedNum = completedNum;
        this.keyCompletedRadio = keyCompletedRadio;
        this.totalNum = totalNum;
        this.calibratedNum = calibratedNum;
        this.comment = comment;
        this.trainPlanExeRate = trainPlanExeRate;
        this.learnFinishedRate = learnFinishedRate;
        this.needFinishedNum = needFinishedNum;
        this.totalTime = totalTime;
        this.averageTime = averageTime;
        this.tenantId = tenantId;
        this.lastModifiedDate = lastModifiedDate;
        this.lastEditorOperatorId = lastEditorOperatorId;
        this.lastRefreshDataTime = lastRefreshDataTime;
    }

    public TrainSummary() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getTrain_id() {
        return train_id;
    }

    public void setTrain_id(Integer train_id) {
        this.train_id = train_id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getSummaryName() {
        return summaryName;
    }

    public void setSummaryName(String summaryName) {
        this.summaryName = summaryName == null ? null : summaryName.trim();
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath == null ? null : attachmentPath.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName == null ? null : parentOrgName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getIsTrainEffect() {
        return isTrainEffect;
    }

    public void setIsTrainEffect(Integer isTrainEffect) {
        this.isTrainEffect = isTrainEffect;
    }

    public Integer getMultiplyTimes() {
        return multiplyTimes;
    }

    public void setMultiplyTimes(Integer multiplyTimes) {
        this.multiplyTimes = multiplyTimes;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getCompletedNum() {
        return completedNum;
    }

    public void setCompletedNum(Integer completedNum) {
        this.completedNum = completedNum;
    }

    public Double getKeyCompletedRadio() {
        return keyCompletedRadio;
    }

    public void setKeyCompletedRadio(Double keyCompletedRadio) {
        this.keyCompletedRadio = keyCompletedRadio;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getCalibratedNum() {
        return calibratedNum;
    }

    public void setCalibratedNum(Integer calibratedNum) {
        this.calibratedNum = calibratedNum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Double getTrainPlanExeRate() {
        return trainPlanExeRate;
    }

    public void setTrainPlanExeRate(Double trainPlanExeRate) {
        this.trainPlanExeRate = trainPlanExeRate;
    }

    public Double getLearnFinishedRate() {
        return learnFinishedRate;
    }

    public void setLearnFinishedRate(Double learnFinishedRate) {
        this.learnFinishedRate = learnFinishedRate;
    }

    public Integer getNeedFinishedNum() {
        return needFinishedNum;
    }

    public void setNeedFinishedNum(Integer needFinishedNum) {
        this.needFinishedNum = needFinishedNum;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public Double getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(Double averageTime) {
        this.averageTime = averageTime;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getLastEditorOperatorId() {
        return lastEditorOperatorId;
    }

    public void setLastEditorOperatorId(Integer lastEditorOperatorId) {
        this.lastEditorOperatorId = lastEditorOperatorId;
    }

    public Date getLastRefreshDataTime() {
        return lastRefreshDataTime;
    }

    public void setLastRefreshDataTime(Date lastRefreshDataTime) {
        this.lastRefreshDataTime = lastRefreshDataTime;
    }
}