package com.elearning.pojo.plan;

import java.util.Date;

public class PlanReport {
    private Integer id;

    private String title;

    private Long trainPlanId;

    private Integer operatorid;

    private String organizerName;

    private Integer approveStatus;

    private Integer approverID;

    private Integer approveOrgID;

    private String approverName;

    private Date approveDate;

    private String reason;

    private String comment;

    public PlanReport(Integer id, String title, Long trainPlanId, Integer operatorid, String organizerName, Integer approveStatus, Integer approverID, Integer approveOrgID, String approverName, Date approveDate, String reason, String comment) {
        this.id = id;
        this.title = title;
        this.trainPlanId = trainPlanId;
        this.operatorid = operatorid;
        this.organizerName = organizerName;
        this.approveStatus = approveStatus;
        this.approverID = approverID;
        this.approveOrgID = approveOrgID;
        this.approverName = approverName;
        this.approveDate = approveDate;
        this.reason = reason;
        this.comment = comment;
    }

    public PlanReport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getTrainPlanId() {
        return trainPlanId;
    }

    public void setTrainPlanId(Long trainPlanId) {
        this.trainPlanId = trainPlanId;
    }

    public Integer getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(Integer operatorid) {
        this.operatorid = operatorid;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName == null ? null : organizerName.trim();
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Integer getApproverID() {
        return approverID;
    }

    public void setApproverID(Integer approverID) {
        this.approverID = approverID;
    }

    public Integer getApproveOrgID() {
        return approveOrgID;
    }

    public void setApproveOrgID(Integer approveOrgID) {
        this.approveOrgID = approveOrgID;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}