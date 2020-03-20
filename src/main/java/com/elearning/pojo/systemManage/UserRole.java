package com.elearning.pojo.systemManage;

import java.util.Date;

public class UserRole {
    private Integer ID;

    private Integer userID;

    private Integer roleID;

    private Integer creatorID;

    private Date createTime;

    private Integer tenantId;

    public UserRole(Integer ID, Integer userID, Integer roleID, Integer creatorID, Date createTime, Integer tenantId) {
        this.ID = ID;
        this.userID = userID;
        this.roleID = roleID;
        this.creatorID = creatorID;
        this.createTime = createTime;
        this.tenantId = tenantId;
    }

    public UserRole() {
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

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}