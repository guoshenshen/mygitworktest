package com.elearning.pojo.recommend;

import java.util.Date;

public class MailreCommend {
    private Long id;

    private Integer operatorId;

    private Integer orgId;

    private String mailName;

    private Date createDate;

    private Date modifyDate;

    private Integer sendTime;

    private Integer sendCount;

    private Integer sendInterval;

    private Boolean editFinished;

    private String mailContent;

    private Boolean hasSend;

    private Boolean isAuto;

    private Boolean classHourNotify;

    private Integer templateId;

    private Integer tenantId;

    public MailreCommend(Long id, Integer operatorId, Integer orgId, String mailName, Date createDate, Date modifyDate, Integer sendTime, Integer sendCount, Integer sendInterval, Boolean editFinished, String mailContent, Boolean hasSend, Boolean isAuto, Boolean classHourNotify, Integer templateId, Integer tenantId) {
        this.id = id;
        this.operatorId = operatorId;
        this.orgId = orgId;
        this.mailName = mailName;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.sendTime = sendTime;
        this.sendCount = sendCount;
        this.sendInterval = sendInterval;
        this.editFinished = editFinished;
        this.mailContent = mailContent;
        this.hasSend = hasSend;
        this.isAuto = isAuto;
        this.classHourNotify = classHourNotify;
        this.templateId = templateId;
        this.tenantId = tenantId;
    }

    public MailreCommend() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName == null ? null : mailName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getSendTime() {
        return sendTime;
    }

    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Integer getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(Integer sendInterval) {
        this.sendInterval = sendInterval;
    }

    public Boolean getEditFinished() {
        return editFinished;
    }

    public void setEditFinished(Boolean editFinished) {
        this.editFinished = editFinished;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent == null ? null : mailContent.trim();
    }

    public Boolean getHasSend() {
        return hasSend;
    }

    public void setHasSend(Boolean hasSend) {
        this.hasSend = hasSend;
    }

    public Boolean getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(Boolean isAuto) {
        this.isAuto = isAuto;
    }

    public Boolean getClassHourNotify() {
        return classHourNotify;
    }

    public void setClassHourNotify(Boolean classHourNotify) {
        this.classHourNotify = classHourNotify;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}