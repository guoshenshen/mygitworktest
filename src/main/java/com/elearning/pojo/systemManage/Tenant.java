package com.elearning.pojo.systemManage;

import java.util.Date;

public class Tenant {
    private Integer tenantId;

    private String tenantName;

    private Integer totalNum;

    private Date registerDate;

    private Integer orgId;

    private String enterUrl;

    private Integer isSingleLogin;

    private Integer isVirOrg;

    private Long serverId;

    private String storedAddress;

    private String httpAddress;

    private String storedContext;

    private String rtmpAddress;

    private Integer isRedirectToPortalWeb;

    private String faviconIcoUrl;

    private Byte workingStatus;

    public Tenant(Integer tenantId, String tenantName, Integer totalNum, Date registerDate, Integer orgId, String enterUrl, Integer isSingleLogin, Integer isVirOrg, Long serverId, String storedAddress, String httpAddress, String storedContext, String rtmpAddress, Integer isRedirectToPortalWeb, String faviconIcoUrl, Byte workingStatus) {
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.totalNum = totalNum;
        this.registerDate = registerDate;
        this.orgId = orgId;
        this.enterUrl = enterUrl;
        this.isSingleLogin = isSingleLogin;
        this.isVirOrg = isVirOrg;
        this.serverId = serverId;
        this.storedAddress = storedAddress;
        this.httpAddress = httpAddress;
        this.storedContext = storedContext;
        this.rtmpAddress = rtmpAddress;
        this.isRedirectToPortalWeb = isRedirectToPortalWeb;
        this.faviconIcoUrl = faviconIcoUrl;
        this.workingStatus = workingStatus;
    }

    public Tenant() {
        super();
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

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getEnterUrl() {
        return enterUrl;
    }

    public void setEnterUrl(String enterUrl) {
        this.enterUrl = enterUrl == null ? null : enterUrl.trim();
    }

    public Integer getIsSingleLogin() {
        return isSingleLogin;
    }

    public void setIsSingleLogin(Integer isSingleLogin) {
        this.isSingleLogin = isSingleLogin;
    }

    public Integer getIsVirOrg() {
        return isVirOrg;
    }

    public void setIsVirOrg(Integer isVirOrg) {
        this.isVirOrg = isVirOrg;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getStoredAddress() {
        return storedAddress;
    }

    public void setStoredAddress(String storedAddress) {
        this.storedAddress = storedAddress == null ? null : storedAddress.trim();
    }

    public String getHttpAddress() {
        return httpAddress;
    }

    public void setHttpAddress(String httpAddress) {
        this.httpAddress = httpAddress == null ? null : httpAddress.trim();
    }

    public String getStoredContext() {
        return storedContext;
    }

    public void setStoredContext(String storedContext) {
        this.storedContext = storedContext == null ? null : storedContext.trim();
    }

    public String getRtmpAddress() {
        return rtmpAddress;
    }

    public void setRtmpAddress(String rtmpAddress) {
        this.rtmpAddress = rtmpAddress == null ? null : rtmpAddress.trim();
    }

    public Integer getIsRedirectToPortalWeb() {
        return isRedirectToPortalWeb;
    }

    public void setIsRedirectToPortalWeb(Integer isRedirectToPortalWeb) {
        this.isRedirectToPortalWeb = isRedirectToPortalWeb;
    }

    public String getFaviconIcoUrl() {
        return faviconIcoUrl;
    }

    public void setFaviconIcoUrl(String faviconIcoUrl) {
        this.faviconIcoUrl = faviconIcoUrl == null ? null : faviconIcoUrl.trim();
    }

    public Byte getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(Byte workingStatus) {
        this.workingStatus = workingStatus;
    }
}