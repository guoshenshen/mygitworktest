package com.elearning.pojo.pub;

import java.util.Date;

public class BackbonePeople {
    private Integer id;

    private Integer operatorId;

    private String operatorName;

    private Integer keyFigureYear;

    private Integer editorID;

    private Integer orgID;

    private Date createDate;

    private Date lastModifyDate;

    private Integer lastEditorId;

    private Integer status;

    private String orgName;

    private String tenantOrgName;

    private Integer tenantId;

    public BackbonePeople(Integer id, Integer operatorId, String operatorName, Integer keyFigureYear, Integer editorID, Integer orgID, Date createDate, Date lastModifyDate, Integer lastEditorId, Integer status, String orgName, String tenantOrgName, Integer tenantId) {
        this.id = id;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.keyFigureYear = keyFigureYear;
        this.editorID = editorID;
        this.orgID = orgID;
        this.createDate = createDate;
        this.lastModifyDate = lastModifyDate;
        this.lastEditorId = lastEditorId;
        this.status = status;
        this.orgName = orgName;
        this.tenantOrgName = tenantOrgName;
        this.tenantId = tenantId;
    }

    public BackbonePeople() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Integer getKeyFigureYear() {
        return keyFigureYear;
    }

    public void setKeyFigureYear(Integer keyFigureYear) {
        this.keyFigureYear = keyFigureYear;
    }

    public Integer getEditorID() {
        return editorID;
    }

    public void setEditorID(Integer editorID) {
        this.editorID = editorID;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Integer getLastEditorId() {
        return lastEditorId;
    }

    public void setLastEditorId(Integer lastEditorId) {
        this.lastEditorId = lastEditorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getTenantOrgName() {
        return tenantOrgName;
    }

    public void setTenantOrgName(String tenantOrgName) {
        this.tenantOrgName = tenantOrgName == null ? null : tenantOrgName.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}