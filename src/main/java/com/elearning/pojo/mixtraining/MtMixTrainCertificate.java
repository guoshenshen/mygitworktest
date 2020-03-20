package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtMixTrainCertificate {
    private Integer id;

    private String certificateUrl;

    private Integer operatorId;

    private Integer trainId;

    private Date createDate;

    private Integer certificateTemplateId;

    private String certificateNo;

    private String certificateContent;

    private String certificateOrgName;

    private String certificateDate;

    private Integer certificateType;

    private Integer isApply;

    private Integer certificateStatus;

    private Integer applyStatus;

    private Integer approveOrgId;

    private Integer approveTenantId;

    private Integer orgId;

    private Integer tenantId;

    private Integer isTrainPoint;

    private Integer certificateStartNum;

    private Integer approveStatus;

    private String approveContent;

    private String userName;

    public MtMixTrainCertificate(Integer id, String certificateUrl, Integer operatorId, Integer trainId, Date createDate, Integer certificateTemplateId, String certificateNo, String certificateContent, String certificateOrgName, String certificateDate, Integer certificateType, Integer isApply, Integer certificateStatus, Integer applyStatus, Integer approveOrgId, Integer approveTenantId, Integer orgId, Integer tenantId, Integer isTrainPoint, Integer certificateStartNum, Integer approveStatus, String approveContent, String userName) {
        this.id = id;
        this.certificateUrl = certificateUrl;
        this.operatorId = operatorId;
        this.trainId = trainId;
        this.createDate = createDate;
        this.certificateTemplateId = certificateTemplateId;
        this.certificateNo = certificateNo;
        this.certificateContent = certificateContent;
        this.certificateOrgName = certificateOrgName;
        this.certificateDate = certificateDate;
        this.certificateType = certificateType;
        this.isApply = isApply;
        this.certificateStatus = certificateStatus;
        this.applyStatus = applyStatus;
        this.approveOrgId = approveOrgId;
        this.approveTenantId = approveTenantId;
        this.orgId = orgId;
        this.tenantId = tenantId;
        this.isTrainPoint = isTrainPoint;
        this.certificateStartNum = certificateStartNum;
        this.approveStatus = approveStatus;
        this.approveContent = approveContent;
        this.userName = userName;
    }

    public MtMixTrainCertificate() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl == null ? null : certificateUrl.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCertificateTemplateId() {
        return certificateTemplateId;
    }

    public void setCertificateTemplateId(Integer certificateTemplateId) {
        this.certificateTemplateId = certificateTemplateId;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo == null ? null : certificateNo.trim();
    }

    public String getCertificateContent() {
        return certificateContent;
    }

    public void setCertificateContent(String certificateContent) {
        this.certificateContent = certificateContent == null ? null : certificateContent.trim();
    }

    public String getCertificateOrgName() {
        return certificateOrgName;
    }

    public void setCertificateOrgName(String certificateOrgName) {
        this.certificateOrgName = certificateOrgName == null ? null : certificateOrgName.trim();
    }

    public String getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(String certificateDate) {
        this.certificateDate = certificateDate == null ? null : certificateDate.trim();
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public Integer getIsApply() {
        return isApply;
    }

    public void setIsApply(Integer isApply) {
        this.isApply = isApply;
    }

    public Integer getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(Integer certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Integer getApproveOrgId() {
        return approveOrgId;
    }

    public void setApproveOrgId(Integer approveOrgId) {
        this.approveOrgId = approveOrgId;
    }

    public Integer getApproveTenantId() {
        return approveTenantId;
    }

    public void setApproveTenantId(Integer approveTenantId) {
        this.approveTenantId = approveTenantId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getIsTrainPoint() {
        return isTrainPoint;
    }

    public void setIsTrainPoint(Integer isTrainPoint) {
        this.isTrainPoint = isTrainPoint;
    }

    public Integer getCertificateStartNum() {
        return certificateStartNum;
    }

    public void setCertificateStartNum(Integer certificateStartNum) {
        this.certificateStartNum = certificateStartNum;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveContent() {
        return approveContent;
    }

    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent == null ? null : approveContent.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}