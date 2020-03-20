package com.elearning.pojo.material;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AssignUser {

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private Long id;

    private Long assignId;

    private Integer operatorid;

    private Integer status;

    private Date submitTime;

    private Boolean valid;

    public AssignUser(Long id, Long assignId, Integer operatorid, Integer status, Date submitTime, Boolean valid) {
        this.id = id;
        this.assignId = assignId;
        this.operatorid = operatorid;
        this.status = status;
        this.submitTime = submitTime;
        this.valid = valid;
    }

    public AssignUser() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssignId() {
        return assignId;
    }

    public void setAssignId(Long assignId) {
        this.assignId = assignId;
    }

    public Integer getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(Integer operatorid) {
        this.operatorid = operatorid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}