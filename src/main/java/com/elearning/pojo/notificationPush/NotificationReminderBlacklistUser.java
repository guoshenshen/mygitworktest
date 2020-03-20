package com.elearning.pojo.notificationPush;

public class NotificationReminderBlacklistUser {
    private Integer ID;

    private Integer tenantId;

    private Integer userID;

    private Integer role;

    private Integer type;

    public NotificationReminderBlacklistUser(Integer ID, Integer tenantId, Integer userID, Integer role, Integer type) {
        this.ID = ID;
        this.tenantId = tenantId;
        this.userID = userID;
        this.role = role;
        this.type = type;
    }

    public NotificationReminderBlacklistUser() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}