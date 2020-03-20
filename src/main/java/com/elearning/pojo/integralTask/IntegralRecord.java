package com.elearning.pojo.integralTask;

import java.util.Date;

public class IntegralRecord {
    private Long id;

    private String updateCauseId;

    private String updateCause;

    private Long ruleId;

    private Double offerScore;

    private Long studentId;

    private String integralHistoryName;

    private Long integralHistoryId;

    private Date recordTime;

    private Long preRecordId;

    public IntegralRecord(Long id, String updateCauseId, String updateCause, Long ruleId, Double offerScore, Long studentId, String integralHistoryName, Long integralHistoryId, Date recordTime, Long preRecordId) {
        this.id = id;
        this.updateCauseId = updateCauseId;
        this.updateCause = updateCause;
        this.ruleId = ruleId;
        this.offerScore = offerScore;
        this.studentId = studentId;
        this.integralHistoryName = integralHistoryName;
        this.integralHistoryId = integralHistoryId;
        this.recordTime = recordTime;
        this.preRecordId = preRecordId;
    }

    public IntegralRecord() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdateCauseId() {
        return updateCauseId;
    }

    public void setUpdateCauseId(String updateCauseId) {
        this.updateCauseId = updateCauseId == null ? null : updateCauseId.trim();
    }

    public String getUpdateCause() {
        return updateCause;
    }

    public void setUpdateCause(String updateCause) {
        this.updateCause = updateCause == null ? null : updateCause.trim();
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Double getOfferScore() {
        return offerScore;
    }

    public void setOfferScore(Double offerScore) {
        this.offerScore = offerScore;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getIntegralHistoryName() {
        return integralHistoryName;
    }

    public void setIntegralHistoryName(String integralHistoryName) {
        this.integralHistoryName = integralHistoryName == null ? null : integralHistoryName.trim();
    }

    public Long getIntegralHistoryId() {
        return integralHistoryId;
    }

    public void setIntegralHistoryId(Long integralHistoryId) {
        this.integralHistoryId = integralHistoryId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Long getPreRecordId() {
        return preRecordId;
    }

    public void setPreRecordId(Long preRecordId) {
        this.preRecordId = preRecordId;
    }
}