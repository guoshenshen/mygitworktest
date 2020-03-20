package com.elearning.pojo.systemManage;

import java.util.Date;

public class RoleResource {
    private Integer ID;

    private Integer role_ID;

    private Integer resource_ID;

    private Integer creatorID;

    private Date createTime;

    public RoleResource(Integer ID, Integer role_ID, Integer resource_ID, Integer creatorID, Date createTime) {
        this.ID = ID;
        this.role_ID = role_ID;
        this.resource_ID = resource_ID;
        this.creatorID = creatorID;
        this.createTime = createTime;
    }

    public RoleResource() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getRole_ID() {
        return role_ID;
    }

    public void setRole_ID(Integer role_ID) {
        this.role_ID = role_ID;
    }

    public Integer getResource_ID() {
        return resource_ID;
    }

    public void setResource_ID(Integer resource_ID) {
        this.resource_ID = resource_ID;
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
}