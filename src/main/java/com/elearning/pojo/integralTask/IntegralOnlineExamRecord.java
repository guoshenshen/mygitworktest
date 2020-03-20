package com.elearning.pojo.integralTask;

import java.util.Date;

public class IntegralOnlineExamRecord {
    private Long id;

    private Long examid;

    private Long paperId;

    private Long replyId;

    private Long userId;

    private Double paperScore;

    private Double percentAge;

    private String dealWithSingleToken;

    private Date recordTime;

    private String status;

    public IntegralOnlineExamRecord(Long id, Long examid, Long paperId, Long replyId, Long userId, Double paperScore, Double percentAge, String dealWithSingleToken, Date recordTime, String status) {
        this.id = id;
        this.examid = examid;
        this.paperId = paperId;
        this.replyId = replyId;
        this.userId = userId;
        this.paperScore = paperScore;
        this.percentAge = percentAge;
        this.dealWithSingleToken = dealWithSingleToken;
        this.recordTime = recordTime;
        this.status = status;
    }

    public IntegralOnlineExamRecord() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamid() {
        return examid;
    }

    public void setExamid(Long examid) {
        this.examid = examid;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(Double paperScore) {
        this.paperScore = paperScore;
    }

    public Double getPercentAge() {
        return percentAge;
    }

    public void setPercentAge(Double percentAge) {
        this.percentAge = percentAge;
    }

    public String getDealWithSingleToken() {
        return dealWithSingleToken;
    }

    public void setDealWithSingleToken(String dealWithSingleToken) {
        this.dealWithSingleToken = dealWithSingleToken == null ? null : dealWithSingleToken.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}