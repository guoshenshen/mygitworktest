package com.elearning.pojo.integralTask;

import java.util.Date;

public class Integral {
    private Integer id;

    private Long totalScore;

    private Long userId;

    private Long ranking;

    private Long operatorId;

    private Date operateTime;

    private String operateCause;

    private Long orgId;

    private String userName;

    private Long signInScore;

    private Long commentScore;

    private Long replyScore;

    private Long summaryScore;

    private Long onlineStudyScore;

    private Double offlineTrainScore;

    private Long onlineExamScore;

    private String orgName;

    private String status;

    private Date updateTime;

    public Integral(Integer id, Long totalScore, Long userId, Long ranking, Long operatorId, Date operateTime, String operateCause, Long orgId, String userName, Long signInScore, Long commentScore, Long replyScore, Long summaryScore, Long onlineStudyScore, Double offlineTrainScore, Long onlineExamScore, String orgName, String status, Date updateTime) {
        this.id = id;
        this.totalScore = totalScore;
        this.userId = userId;
        this.ranking = ranking;
        this.operatorId = operatorId;
        this.operateTime = operateTime;
        this.operateCause = operateCause;
        this.orgId = orgId;
        this.userName = userName;
        this.signInScore = signInScore;
        this.commentScore = commentScore;
        this.replyScore = replyScore;
        this.summaryScore = summaryScore;
        this.onlineStudyScore = onlineStudyScore;
        this.offlineTrainScore = offlineTrainScore;
        this.onlineExamScore = onlineExamScore;
        this.orgName = orgName;
        this.status = status;
        this.updateTime = updateTime;
    }

    public Integral() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRanking() {
        return ranking;
    }

    public void setRanking(Long ranking) {
        this.ranking = ranking;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateCause() {
        return operateCause;
    }

    public void setOperateCause(String operateCause) {
        this.operateCause = operateCause == null ? null : operateCause.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Long getSignInScore() {
        return signInScore;
    }

    public void setSignInScore(Long signInScore) {
        this.signInScore = signInScore;
    }

    public Long getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Long commentScore) {
        this.commentScore = commentScore;
    }

    public Long getReplyScore() {
        return replyScore;
    }

    public void setReplyScore(Long replyScore) {
        this.replyScore = replyScore;
    }

    public Long getSummaryScore() {
        return summaryScore;
    }

    public void setSummaryScore(Long summaryScore) {
        this.summaryScore = summaryScore;
    }

    public Long getOnlineStudyScore() {
        return onlineStudyScore;
    }

    public void setOnlineStudyScore(Long onlineStudyScore) {
        this.onlineStudyScore = onlineStudyScore;
    }

    public Double getOfflineTrainScore() {
        return offlineTrainScore;
    }

    public void setOfflineTrainScore(Double offlineTrainScore) {
        this.offlineTrainScore = offlineTrainScore;
    }

    public Long getOnlineExamScore() {
        return onlineExamScore;
    }

    public void setOnlineExamScore(Long onlineExamScore) {
        this.onlineExamScore = onlineExamScore;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}