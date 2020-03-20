package com.elearning.pojo.notificationPush;

import java.util.Date;

public class NotificationPushClassHourMsg {
    private Integer tenantId;

    private String title;

    private Integer status;

    private Date createDate;

    private Integer isEmail;

    private Integer isSMS;

    private Date lastModifiedDate;

    private String lastModifiedOperatorName;

    private Integer lastModifiedOperatorId;

    private Integer notifyScope;

    private Integer frequency;

    private Integer day;

    public NotificationPushClassHourMsg(Integer tenantId, String title, Integer status, Date createDate, Integer isEmail, Integer isSMS, Date lastModifiedDate, String lastModifiedOperatorName, Integer lastModifiedOperatorId, Integer notifyScope, Integer frequency, Integer day) {
        this.tenantId = tenantId;
        this.title = title;
        this.status = status;
        this.createDate = createDate;
        this.isEmail = isEmail;
        this.isSMS = isSMS;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedOperatorName = lastModifiedOperatorName;
        this.lastModifiedOperatorId = lastModifiedOperatorId;
        this.notifyScope = notifyScope;
        this.frequency = frequency;
        this.day = day;
    }

    public NotificationPushClassHourMsg() {
        super();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(Integer isEmail) {
        this.isEmail = isEmail;
    }

    public Integer getIsSMS() {
        return isSMS;
    }

    public void setIsSMS(Integer isSMS) {
        this.isSMS = isSMS;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedOperatorName() {
        return lastModifiedOperatorName;
    }

    public void setLastModifiedOperatorName(String lastModifiedOperatorName) {
        this.lastModifiedOperatorName = lastModifiedOperatorName == null ? null : lastModifiedOperatorName.trim();
    }

    public Integer getLastModifiedOperatorId() {
        return lastModifiedOperatorId;
    }

    public void setLastModifiedOperatorId(Integer lastModifiedOperatorId) {
        this.lastModifiedOperatorId = lastModifiedOperatorId;
    }

    public Integer getNotifyScope() {
        return notifyScope;
    }

    public void setNotifyScope(Integer notifyScope) {
        this.notifyScope = notifyScope;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}