package com.elearning.pojo.mixtraining;

public class MtMixTrainCertificateUser {
    private Integer id;

    private Integer trainId;

    private Integer userId;

    private String userCertificateUrl;

    private Integer certificateId;

    private String userCertificateNo;

    private Integer tenantId;

    private String userName;

    private Boolean finalVersion;

    private Boolean source;

    private Integer templateId;

    private String userCertificateNoPattern;

    public MtMixTrainCertificateUser(Integer id, Integer trainId, Integer userId, String userCertificateUrl, Integer certificateId, String userCertificateNo, Integer tenantId, String userName, Boolean finalVersion, Boolean source, Integer templateId, String userCertificateNoPattern) {
        this.id = id;
        this.trainId = trainId;
        this.userId = userId;
        this.userCertificateUrl = userCertificateUrl;
        this.certificateId = certificateId;
        this.userCertificateNo = userCertificateNo;
        this.tenantId = tenantId;
        this.userName = userName;
        this.finalVersion = finalVersion;
        this.source = source;
        this.templateId = templateId;
        this.userCertificateNoPattern = userCertificateNoPattern;
    }

    public MtMixTrainCertificateUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCertificateUrl() {
        return userCertificateUrl;
    }

    public void setUserCertificateUrl(String userCertificateUrl) {
        this.userCertificateUrl = userCertificateUrl == null ? null : userCertificateUrl.trim();
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public String getUserCertificateNo() {
        return userCertificateNo;
    }

    public void setUserCertificateNo(String userCertificateNo) {
        this.userCertificateNo = userCertificateNo == null ? null : userCertificateNo.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Boolean getFinalVersion() {
        return finalVersion;
    }

    public void setFinalVersion(Boolean finalVersion) {
        this.finalVersion = finalVersion;
    }

    public Boolean getSource() {
        return source;
    }

    public void setSource(Boolean source) {
        this.source = source;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getUserCertificateNoPattern() {
        return userCertificateNoPattern;
    }

    public void setUserCertificateNoPattern(String userCertificateNoPattern) {
        this.userCertificateNoPattern = userCertificateNoPattern == null ? null : userCertificateNoPattern.trim();
    }
}