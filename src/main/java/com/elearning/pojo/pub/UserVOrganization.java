package com.elearning.pojo.pub;

import java.util.Date;

public class UserVOrganization extends UserVOrganizationKey {
    private Integer status;

    private String orgSeq;

    private Date statusModifyDate;

    private Integer createID;

    private Date createDate;

    public UserVOrganization(Integer operatorID, Integer orgID, Integer status, String orgSeq, Date statusModifyDate, Integer createID, Date createDate) {
        super(operatorID, orgID);
        this.status = status;
        this.orgSeq = orgSeq;
        this.statusModifyDate = statusModifyDate;
        this.createID = createID;
        this.createDate = createDate;
    }

    public UserVOrganization() {
        super();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrgSeq() {
        return orgSeq;
    }

    public void setOrgSeq(String orgSeq) {
        this.orgSeq = orgSeq == null ? null : orgSeq.trim();
    }

    public Date getStatusModifyDate() {
        return statusModifyDate;
    }

    public void setStatusModifyDate(Date statusModifyDate) {
        this.statusModifyDate = statusModifyDate;
    }

    public Integer getCreateID() {
        return createID;
    }

    public void setCreateID(Integer createID) {
        this.createID = createID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}