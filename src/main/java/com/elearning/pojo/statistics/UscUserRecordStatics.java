package com.elearning.pojo.statistics;

import java.util.Date;

public class UscUserRecordStatics {
    private Long recordId;

    private Integer operatorId;

    private String empCode;

    private String operatorName;

    private Integer trainId;

    private String trainCode;

    private String trainName;

    private String trainTypeName;

    private Double classHour;

    private Date startTime;

    private Date endTime;

    private String year;

    private String achievement;

    private Integer isKeyFigure;

    private Integer tenantId;

    private String approveStatusName;

    public UscUserRecordStatics(Long recordId, Integer operatorId, String empCode, String operatorName, Integer trainId, String trainCode, String trainName, String trainTypeName, Double classHour, Date startTime, Date endTime, String year, String achievement, Integer isKeyFigure, Integer tenantId, String approveStatusName) {
        this.recordId = recordId;
        this.operatorId = operatorId;
        this.empCode = empCode;
        this.operatorName = operatorName;
        this.trainId = trainId;
        this.trainCode = trainCode;
        this.trainName = trainName;
        this.trainTypeName = trainTypeName;
        this.classHour = classHour;
        this.startTime = startTime;
        this.endTime = endTime;
        this.year = year;
        this.achievement = achievement;
        this.isKeyFigure = isKeyFigure;
        this.tenantId = tenantId;
        this.approveStatusName = approveStatusName;
    }

    public UscUserRecordStatics() {
        super();
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode == null ? null : empCode.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode == null ? null : trainCode.trim();
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName == null ? null : trainName.trim();
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }

    public void setTrainTypeName(String trainTypeName) {
        this.trainTypeName = trainTypeName == null ? null : trainTypeName.trim();
    }

    public Double getClassHour() {
        return classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement == null ? null : achievement.trim();
    }

    public Integer getIsKeyFigure() {
        return isKeyFigure;
    }

    public void setIsKeyFigure(Integer isKeyFigure) {
        this.isKeyFigure = isKeyFigure;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getApproveStatusName() {
        return approveStatusName;
    }

    public void setApproveStatusName(String approveStatusName) {
        this.approveStatusName = approveStatusName == null ? null : approveStatusName.trim();
    }
}