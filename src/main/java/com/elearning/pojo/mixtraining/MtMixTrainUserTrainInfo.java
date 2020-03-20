package com.elearning.pojo.mixtraining;

public class MtMixTrainUserTrainInfo {
    private Integer ID;

    private Integer trainID;

    private Integer section_id;

    private Integer userID;

    private Double classHour;

    private Double score;

    private String remark;

    private String userName;

    private Integer attendable;

    private Integer status;

    private Integer attended;

    private Integer attendType;

    private Integer hurrySummary;

    private String parentOrgName;

    private String orgName;

    //该字段数据库中暂时没有
    private String statistics;//学时统计

    public MtMixTrainUserTrainInfo(Integer ID, Integer trainID, Integer section_id, Integer userID, Double classHour, Double score, String remark, String userName, Integer attendable, Integer status, Integer attended, Integer attendType, Integer hurrySummary, String parentOrgName, String orgName) {
        this.ID = ID;
        this.trainID = trainID;
        this.section_id = section_id;
        this.userID = userID;
        this.classHour = classHour;
        this.score = score;
        this.remark = remark;
        this.userName = userName;
        this.attendable = attendable;
        this.status = status;
        this.attended = attended;
        this.attendType = attendType;
        this.hurrySummary = hurrySummary;
        this.parentOrgName = parentOrgName;
        this.orgName = orgName;
    }

    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }

    public MtMixTrainUserTrainInfo() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getAttendable() {
        return attendable;
    }

    public void setAttendable(Integer attendable) {
        this.attendable = attendable;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAttended() {
        return attended;
    }

    public void setAttended(Integer attended) {
        this.attended = attended;
    }

    public Integer getAttendType() {
        return attendType;
    }

    public void setAttendType(Integer attendType) {
        this.attendType = attendType;
    }

    public Integer getHurrySummary() {
        return hurrySummary;
    }

    public void setHurrySummary(Integer hurrySummary) {
        this.hurrySummary = hurrySummary;
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName == null ? null : parentOrgName.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }
}