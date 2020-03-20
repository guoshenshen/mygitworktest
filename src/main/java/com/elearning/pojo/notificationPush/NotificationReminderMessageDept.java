package com.elearning.pojo.notificationPush;

public class NotificationReminderMessageDept {
    private Integer ID;

    private Integer tenantId;

    private Integer deptID;

    private Integer type;

    public NotificationReminderMessageDept(Integer ID, Integer tenantId, Integer deptID, Integer type) {
        this.ID = ID;
        this.tenantId = tenantId;
        this.deptID = deptID;
        this.type = type;
    }

    public NotificationReminderMessageDept() {
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

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}