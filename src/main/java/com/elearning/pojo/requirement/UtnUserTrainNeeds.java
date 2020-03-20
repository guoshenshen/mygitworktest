package com.elearning.pojo.requirement;

import java.util.Date;

public class UtnUserTrainNeeds {
    private Integer ID;

    private Integer userID;

    private String needsName;

    private String needsContent;

    private String needsGoal;

    private Integer needsType_id;

    private Integer year;

    private Date pubTime;

    private Date submitDate;

    private String approverContent;

    private String approverName;

    private Integer tenantId;

    public UtnUserTrainNeeds(Integer ID, Integer userID, String needsName, String needsContent, String needsGoal, Integer needsType_id, Integer year, Date pubTime, Date submitDate, String approverContent, String approverName, Integer tenantId) {
        this.ID = ID;
        this.userID = userID;
        this.needsName = needsName;
        this.needsContent = needsContent;
        this.needsGoal = needsGoal;
        this.needsType_id = needsType_id;
        this.year = year;
        this.pubTime = pubTime;
        this.submitDate = submitDate;
        this.approverContent = approverContent;
        this.approverName = approverName;
        this.tenantId = tenantId;
    }

    public UtnUserTrainNeeds() {
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

    public String getNeedsName() {
        return needsName;
    }

    public void setNeedsName(String needsName) {
        this.needsName = needsName == null ? null : needsName.trim();
    }

    public String getNeedsContent() {
        return needsContent;
    }

    public void setNeedsContent(String needsContent) {
        this.needsContent = needsContent == null ? null : needsContent.trim();
    }

    public String getNeedsGoal() {
        return needsGoal;
    }

    public void setNeedsGoal(String needsGoal) {
        this.needsGoal = needsGoal == null ? null : needsGoal.trim();
    }

    public Integer getNeedsType_id() {
        return needsType_id;
    }

    public void setNeedsType_id(Integer needsType_id) {
        this.needsType_id = needsType_id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getApproverContent() {
        return approverContent;
    }

    public void setApproverContent(String approverContent) {
        this.approverContent = approverContent == null ? null : approverContent.trim();
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}