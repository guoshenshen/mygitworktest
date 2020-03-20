package com.elearning.pojo.pub;

import java.util.Date;

public class BackbonePeopleReportSituation {
    private Integer id;

    private Integer tenantId;

    private Integer year;

    private Integer isCommitted;

    private Date reportStartTime;

    private Date reportEndTime;

    private Integer backbonepeopleNum;

    private Integer submitOperatorId;

    private Date submitTime;

    private Date createTime;

    public BackbonePeopleReportSituation(Integer id, Integer tenantId, Integer year, Integer isCommitted, Date reportStartTime, Date reportEndTime, Integer backbonepeopleNum, Integer submitOperatorId, Date submitTime, Date createTime) {
        this.id = id;
        this.tenantId = tenantId;
        this.year = year;
        this.isCommitted = isCommitted;
        this.reportStartTime = reportStartTime;
        this.reportEndTime = reportEndTime;
        this.backbonepeopleNum = backbonepeopleNum;
        this.submitOperatorId = submitOperatorId;
        this.submitTime = submitTime;
        this.createTime = createTime;
    }

    public BackbonePeopleReportSituation() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getIsCommitted() {
        return isCommitted;
    }

    public void setIsCommitted(Integer isCommitted) {
        this.isCommitted = isCommitted;
    }

    public Date getReportStartTime() {
        return reportStartTime;
    }

    public void setReportStartTime(Date reportStartTime) {
        this.reportStartTime = reportStartTime;
    }

    public Date getReportEndTime() {
        return reportEndTime;
    }

    public void setReportEndTime(Date reportEndTime) {
        this.reportEndTime = reportEndTime;
    }

    public Integer getBackbonepeopleNum() {
        return backbonepeopleNum;
    }

    public void setBackbonepeopleNum(Integer backbonepeopleNum) {
        this.backbonepeopleNum = backbonepeopleNum;
    }

    public Integer getSubmitOperatorId() {
        return submitOperatorId;
    }

    public void setSubmitOperatorId(Integer submitOperatorId) {
        this.submitOperatorId = submitOperatorId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}