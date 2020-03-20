package com.elearning.pojo.integralTask;

import java.util.Date;

public class IntegralSignInHistory {
    private Long id;

    private Long integralRuleId;

    private Long userId;

    private Date signInTime;

    private Long previousSignId;

    private String status;

    public IntegralSignInHistory(Long id, Long integralRuleId, Long userId, Date signInTime, Long previousSignId, String status) {
        this.id = id;
        this.integralRuleId = integralRuleId;
        this.userId = userId;
        this.signInTime = signInTime;
        this.previousSignId = previousSignId;
        this.status = status;
    }

    public IntegralSignInHistory() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIntegralRuleId() {
        return integralRuleId;
    }

    public void setIntegralRuleId(Long integralRuleId) {
        this.integralRuleId = integralRuleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public Long getPreviousSignId() {
        return previousSignId;
    }

    public void setPreviousSignId(Long previousSignId) {
        this.previousSignId = previousSignId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}