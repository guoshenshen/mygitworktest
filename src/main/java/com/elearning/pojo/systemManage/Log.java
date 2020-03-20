package com.elearning.pojo.systemManage;

import java.util.Date;

public class Log {
    private Integer logid;

    private Date createdate;

    private Integer tenantId;

    private String tenantName;

    private Integer operatorID;

    private String operatorName;

    private String action;

    private String sourceUrl;

    private String targetUrl;

    private String ip;

    public Log(Integer logid, Date createdate, Integer tenantId, String tenantName, Integer operatorID, String operatorName, String action, String sourceUrl, String targetUrl, String ip) {
        this.logid = logid;
        this.createdate = createdate;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.operatorID = operatorID;
        this.operatorName = operatorName;
        this.action = action;
        this.sourceUrl = sourceUrl;
        this.targetUrl = targetUrl;
        this.ip = ip;
    }

    public Log() {
        super();
    }

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName == null ? null : tenantName.trim();
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl == null ? null : targetUrl.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}