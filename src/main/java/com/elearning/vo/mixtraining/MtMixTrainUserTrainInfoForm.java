package com.elearning.vo.mixtraining;

import java.util.Date;


public class MtMixTrainUserTrainInfoForm {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer trainId;

    private Integer sectionId;

    private Integer userId;

    private Double classHour;

    private Double score;

    private String remark;

    private String userName;

    private Integer attendable;

    private String attendableName;

    private Integer status;

    private String statusName;

    private Integer attended;

    private String attendedName;

    private Integer attendType;

    private String attendTypeName;

    private String email;

    private String orgName;

    private Integer hurrySummary;

    private String hurrySummaryName;

    private Integer trainSummaryId;

    private String parentOrgName;

    private String statistics;//学时统计

    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getClassHour() {
        return classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAttendable() {
        return attendable;
    }

    public void setAttendable(Integer attendable) {
        this.attendable = attendable;
    }

    public String getAttendableName() {
        return attendableName;
    }

    public void setAttendableName(String attendableName) {
        this.attendableName = attendableName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getAttended() {
        return attended;
    }

    public void setAttended(Integer attended) {
        this.attended = attended;
    }

    public String getAttendedName() {
        return attendedName;
    }

    public void setAttendedName(String attendedName) {
        this.attendedName = attendedName;
    }

    public Integer getAttendType() {
        return attendType;
    }

    public void setAttendType(Integer attendType) {
        this.attendType = attendType;
    }

    public String getAttendTypeName() {
        return attendTypeName;
    }

    public void setAttendTypeName(String attendTypeName) {
        this.attendTypeName = attendTypeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getHurrySummary() {
        return hurrySummary;
    }

    public void setHurrySummary(Integer hurrySummary) {
        this.hurrySummary = hurrySummary;
    }

    public String getHurrySummaryName() {
        return hurrySummaryName;
    }

    public void setHurrySummaryName(String hurrySummaryName) {
        this.hurrySummaryName = hurrySummaryName;
    }

    public Integer getTrainSummaryId() {
        return trainSummaryId;
    }

    public void setTrainSummaryId(Integer trainSummaryId) {
        this.trainSummaryId = trainSummaryId;
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }
}
