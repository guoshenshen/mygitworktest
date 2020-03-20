package com.elearning.pojo.requirement;

import java.util.Date;

public class UtnOrgTrainNeeds {
    private Long ID;

    private Integer orgID;

    private Integer userID;

    private String needsName;

    private Integer needsType;

    private String needsContent;

    private String target;

    private Date submitDate;

    private String approverName;

    private String approverContent;

    private Date pubTime;

    private Integer years;

    private Integer tenantId;

    public UtnOrgTrainNeeds(Long ID, Integer orgID, Integer userID, String needsName, Integer needsType, String needsContent, String target, Date submitDate, String approverName, String approverContent, Date pubTime, Integer years, Integer tenantId) {
        this.ID = ID;
        this.orgID = orgID;
        this.userID = userID;
        this.needsName = needsName;
        this.needsType = needsType;
        this.needsContent = needsContent;
        this.target = target;
        this.submitDate = submitDate;
        this.approverName = approverName;
        this.approverContent = approverContent;
        this.pubTime = pubTime;
        this.years = years;
        this.tenantId = tenantId;
    }

    public UtnOrgTrainNeeds() {
        super();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
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

    public Integer getNeedsType() {
        return needsType;
    }

    public void setNeedsType(Integer needsType) {
        this.needsType = needsType;
    }

    public String getNeedsContent() {
        return needsContent;
    }

    public void setNeedsContent(String needsContent) {
        this.needsContent = needsContent == null ? null : needsContent.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    public String getApproverContent() {
        return approverContent;
    }

    public void setApproverContent(String approverContent) {
        this.approverContent = approverContent == null ? null : approverContent.trim();
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}