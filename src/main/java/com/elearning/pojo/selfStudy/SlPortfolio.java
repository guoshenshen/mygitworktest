package com.elearning.pojo.selfStudy;

import java.util.Date;

public class SlPortfolio {
    private Integer portfolioId;

    private Long slStudyId;

    private Integer operatorId;

    private Integer slNoteId;

    private Date logonTime;

    private Date logoutTime;

    private String byRate;

    public SlPortfolio(Integer portfolioId, Long slStudyId, Integer operatorId, Integer slNoteId, Date logonTime, Date logoutTime, String byRate) {
        this.portfolioId = portfolioId;
        this.slStudyId = slStudyId;
        this.operatorId = operatorId;
        this.slNoteId = slNoteId;
        this.logonTime = logonTime;
        this.logoutTime = logoutTime;
        this.byRate = byRate;
    }

    public SlPortfolio() {
        super();
    }

    public Integer getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Integer portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Long getSlStudyId() {
        return slStudyId;
    }

    public void setSlStudyId(Long slStudyId) {
        this.slStudyId = slStudyId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getSlNoteId() {
        return slNoteId;
    }

    public void setSlNoteId(Integer slNoteId) {
        this.slNoteId = slNoteId;
    }

    public Date getLogonTime() {
        return logonTime;
    }

    public void setLogonTime(Date logonTime) {
        this.logonTime = logonTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getByRate() {
        return byRate;
    }

    public void setByRate(String byRate) {
        this.byRate = byRate == null ? null : byRate.trim();
    }
}