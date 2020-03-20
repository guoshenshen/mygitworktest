package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtMixTrainAddrBook {
    private Integer addrbookId;

    private String addrbookName;

    private Integer addrbookRole;

    private Date addrbookDate;

    private Integer operatorId;

    private Integer openScope;

    private String orgSEQ;

    public MtMixTrainAddrBook(Integer addrbookId, String addrbookName, Integer addrbookRole, Date addrbookDate, Integer operatorId, Integer openScope, String orgSEQ) {
        this.addrbookId = addrbookId;
        this.addrbookName = addrbookName;
        this.addrbookRole = addrbookRole;
        this.addrbookDate = addrbookDate;
        this.operatorId = operatorId;
        this.openScope = openScope;
        this.orgSEQ = orgSEQ;
    }

    public MtMixTrainAddrBook() {
        super();
    }

    public Integer getAddrbookId() {
        return addrbookId;
    }

    public void setAddrbookId(Integer addrbookId) {
        this.addrbookId = addrbookId;
    }

    public String getAddrbookName() {
        return addrbookName;
    }

    public void setAddrbookName(String addrbookName) {
        this.addrbookName = addrbookName == null ? null : addrbookName.trim();
    }

    public Integer getAddrbookRole() {
        return addrbookRole;
    }

    public void setAddrbookRole(Integer addrbookRole) {
        this.addrbookRole = addrbookRole;
    }

    public Date getAddrbookDate() {
        return addrbookDate;
    }

    public void setAddrbookDate(Date addrbookDate) {
        this.addrbookDate = addrbookDate;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getOpenScope() {
        return openScope;
    }

    public void setOpenScope(Integer openScope) {
        this.openScope = openScope;
    }

    public String getOrgSEQ() {
        return orgSEQ;
    }

    public void setOrgSEQ(String orgSEQ) {
        this.orgSEQ = orgSEQ == null ? null : orgSEQ.trim();
    }
}