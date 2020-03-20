package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtMixTrainApprove {
    private Long id;

    private Integer trainId;

    private Long planId;

    private Integer operatorId;

    private String year;

    private String approverName;

    private Date approveDate;

    private String approveStatusName;

    private String comment;

    private Integer type;

    public MtMixTrainApprove(Long id, Integer trainId, Long planId, Integer operatorId, String year, String approverName, Date approveDate, String approveStatusName, String comment, Integer type) {
        this.id = id;
        this.trainId = trainId;
        this.planId = planId;
        this.operatorId = operatorId;
        this.year = year;
        this.approverName = approverName;
        this.approveDate = approveDate;
        this.approveStatusName = approveStatusName;
        this.comment = comment;
        this.type = type;
    }

    public MtMixTrainApprove() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
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

    public String getApproveStatusName() {
        return approveStatusName;
    }

    public void setApproveStatusName(String approveStatusName) {
        this.approveStatusName = approveStatusName == null ? null : approveStatusName.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}