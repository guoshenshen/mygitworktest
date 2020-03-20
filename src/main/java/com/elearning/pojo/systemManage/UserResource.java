package com.elearning.pojo.systemManage;

import java.util.Date;

public class UserResource {
    private Integer ID;

    private Integer operatorId;

    private Integer resourceId;

    private Date createTime;

    public UserResource(Integer ID, Integer operatorId, Integer resourceId, Date createTime) {
        this.ID = ID;
        this.operatorId = operatorId;
        this.resourceId = resourceId;
        this.createTime = createTime;
    }

    public UserResource() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}