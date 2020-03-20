package com.elearning.pojo.integralTask;

import java.util.Date;

public class IntegralSummaryHistory {
    private Long id;

    private Long userId;

    private Long integralRuleId;

    private Long trainId;

    private Long summaryId;

    private String summaryCount;

    private Date summaryTime;

    private String status;

    public IntegralSummaryHistory(Long id, Long userId, Long integralRuleId, Long trainId, Long summaryId, String summaryCount, Date summaryTime, String status) {
        this.id = id;
        this.userId = userId;
        this.integralRuleId = integralRuleId;
        this.trainId = trainId;
        this.summaryId = summaryId;
        this.summaryCount = summaryCount;
        this.summaryTime = summaryTime;
        this.status = status;
    }

    public IntegralSummaryHistory() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIntegralRuleId() {
        return integralRuleId;
    }

    public void setIntegralRuleId(Long integralRuleId) {
        this.integralRuleId = integralRuleId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Long summaryId) {
        this.summaryId = summaryId;
    }

    public String getSummaryCount() {
        return summaryCount;
    }

    public void setSummaryCount(String summaryCount) {
        this.summaryCount = summaryCount == null ? null : summaryCount.trim();
    }

    public Date getSummaryTime() {
        return summaryTime;
    }

    public void setSummaryTime(Date summaryTime) {
        this.summaryTime = summaryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}