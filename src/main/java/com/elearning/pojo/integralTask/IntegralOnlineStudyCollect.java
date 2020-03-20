package com.elearning.pojo.integralTask;

import java.util.Date;

public class IntegralOnlineStudyCollect {
    private Long id;

    private Long userId;

    private String collectHistoryIds;

    private String dayId;

    private Long totalMillons;

    private Long ruleId;

    private Long totalScore;

    private String status;

    private Date updateTimeStamp;

    public IntegralOnlineStudyCollect(Long id, Long userId, String collectHistoryIds, String dayId, Long totalMillons, Long ruleId, Long totalScore, String status, Date updateTimeStamp) {
        this.id = id;
        this.userId = userId;
        this.collectHistoryIds = collectHistoryIds;
        this.dayId = dayId;
        this.totalMillons = totalMillons;
        this.ruleId = ruleId;
        this.totalScore = totalScore;
        this.status = status;
        this.updateTimeStamp = updateTimeStamp;
    }

    public IntegralOnlineStudyCollect() {
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

    public String getCollectHistoryIds() {
        return collectHistoryIds;
    }

    public void setCollectHistoryIds(String collectHistoryIds) {
        this.collectHistoryIds = collectHistoryIds == null ? null : collectHistoryIds.trim();
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId == null ? null : dayId.trim();
    }

    public Long getTotalMillons() {
        return totalMillons;
    }

    public void setTotalMillons(Long totalMillons) {
        this.totalMillons = totalMillons;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(Date updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }
}