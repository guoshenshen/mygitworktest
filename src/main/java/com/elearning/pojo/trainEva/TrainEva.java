package com.elearning.pojo.trainEva;

import java.util.Date;

public class TrainEva {
    private Integer ID;

    private Integer tenantId;

    private Integer orgId;

    private Integer operatorId;

    private String operatorName;

    private Integer itemId;

    private Double originalMark;

    private Double reviewMark;

    private Double originalTotalScore;

    private Double reviewTotalScore;

    private Date submitDate;

    private String comment;

    private String upreportOrgIds;

    public TrainEva(Integer ID, Integer tenantId, Integer orgId, Integer operatorId, String operatorName, Integer itemId, Double originalMark, Double reviewMark, Double originalTotalScore, Double reviewTotalScore, Date submitDate, String comment, String upreportOrgIds) {
        this.ID = ID;
        this.tenantId = tenantId;
        this.orgId = orgId;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.itemId = itemId;
        this.originalMark = originalMark;
        this.reviewMark = reviewMark;
        this.originalTotalScore = originalTotalScore;
        this.reviewTotalScore = reviewTotalScore;
        this.submitDate = submitDate;
        this.comment = comment;
        this.upreportOrgIds = upreportOrgIds;
    }

    public TrainEva() {
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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getOriginalMark() {
        return originalMark;
    }

    public void setOriginalMark(Double originalMark) {
        this.originalMark = originalMark;
    }

    public Double getReviewMark() {
        return reviewMark;
    }

    public void setReviewMark(Double reviewMark) {
        this.reviewMark = reviewMark;
    }

    public Double getOriginalTotalScore() {
        return originalTotalScore;
    }

    public void setOriginalTotalScore(Double originalTotalScore) {
        this.originalTotalScore = originalTotalScore;
    }

    public Double getReviewTotalScore() {
        return reviewTotalScore;
    }

    public void setReviewTotalScore(Double reviewTotalScore) {
        this.reviewTotalScore = reviewTotalScore;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getUpreportOrgIds() {
        return upreportOrgIds;
    }

    public void setUpreportOrgIds(String upreportOrgIds) {
        this.upreportOrgIds = upreportOrgIds == null ? null : upreportOrgIds.trim();
    }
}