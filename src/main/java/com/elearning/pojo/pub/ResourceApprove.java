package com.elearning.pojo.pub;

import java.util.Date;

public class ResourceApprove {
    private Long id;

    private Long resourceId;

    private Long parentId;

    private Integer orgId;

    private String orgName;

    private Integer approverID;

    private String approverName;

    private Date approveDate;

    private Integer approveStatus;

    private String comment;

    private Integer upreportorID;

    private Integer upreportOrgId;

    private Byte type;

    private Boolean status;

    public ResourceApprove(Long id, Long resourceId, Long parentId, Integer orgId, String orgName, Integer approverID, String approverName, Date approveDate, Integer approveStatus, String comment, Integer upreportorID, Integer upreportOrgId, Byte type, Boolean status) {
        this.id = id;
        this.resourceId = resourceId;
        this.parentId = parentId;
        this.orgId = orgId;
        this.orgName = orgName;
        this.approverID = approverID;
        this.approverName = approverName;
        this.approveDate = approveDate;
        this.approveStatus = approveStatus;
        this.comment = comment;
        this.upreportorID = upreportorID;
        this.upreportOrgId = upreportOrgId;
        this.type = type;
        this.status = status;
    }

    public ResourceApprove() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getApproverID() {
        return approverID;
    }

    public void setApproverID(Integer approverID) {
        this.approverID = approverID;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getUpreportorID() {
        return upreportorID;
    }

    public void setUpreportorID(Integer upreportorID) {
        this.upreportorID = upreportorID;
    }

    public Integer getUpreportOrgId() {
        return upreportOrgId;
    }

    public void setUpreportOrgId(Integer upreportOrgId) {
        this.upreportOrgId = upreportOrgId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}