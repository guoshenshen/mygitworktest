package com.elearning.pojo.trainNeeds;

import java.util.Date;

public class TnNeedsQuestionnAire {
    private Integer ID;

    private String theme;

    private Date startTime;

    private Date endTime;

    private Integer pubStatus;

    private Long pubUserID;

    private Integer joinCount;

    private Integer finishCount;

    private Integer orgId;

    private Integer trainId;

    private Integer sendMail;

    private Integer ifAnonymous;

    private String publishAddr;

    private Integer tenantId;

    private String publishOrgName;

    private String emailTitle;

    private String remark;

    public TnNeedsQuestionnAire(Integer ID, String theme, Date startTime, Date endTime, Integer pubStatus, Long pubUserID, Integer joinCount, Integer finishCount, Integer orgId, Integer trainId, Integer sendMail, Integer ifAnonymous, String publishAddr, Integer tenantId, String publishOrgName, String emailTitle, String remark) {
        this.ID = ID;
        this.theme = theme;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pubStatus = pubStatus;
        this.pubUserID = pubUserID;
        this.joinCount = joinCount;
        this.finishCount = finishCount;
        this.orgId = orgId;
        this.trainId = trainId;
        this.sendMail = sendMail;
        this.ifAnonymous = ifAnonymous;
        this.publishAddr = publishAddr;
        this.tenantId = tenantId;
        this.publishOrgName = publishOrgName;
        this.emailTitle = emailTitle;
        this.remark = remark;
    }

    public TnNeedsQuestionnAire() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
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

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getSendMail() {
        return sendMail;
    }

    public void setSendMail(Integer sendMail) {
        this.sendMail = sendMail;
    }

    public Integer getIfAnonymous() {
        return ifAnonymous;
    }

    public void setIfAnonymous(Integer ifAnonymous) {
        this.ifAnonymous = ifAnonymous;
    }

    public String getPublishAddr() {
        return publishAddr;
    }

    public void setPublishAddr(String publishAddr) {
        this.publishAddr = publishAddr == null ? null : publishAddr.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getPublishOrgName() {
        return publishOrgName;
    }

    public void setPublishOrgName(String publishOrgName) {
        this.publishOrgName = publishOrgName == null ? null : publishOrgName.trim();
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle == null ? null : emailTitle.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}