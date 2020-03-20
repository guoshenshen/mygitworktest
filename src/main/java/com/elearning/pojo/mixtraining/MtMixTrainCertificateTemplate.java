package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtMixTrainCertificateTemplate {
    private Integer id;

    private String certificateTemplateName;

    private String certificateTemplatePath;

    private Integer operatorId;

    private Integer isDefault;

    private Integer tenantId;

    private Integer orgId;

    private Date createDate;

    private Integer certificateTemplateType;

    private Integer usedTimes;

    private Integer openScope;

    private Integer status;

    public MtMixTrainCertificateTemplate(Integer id, String certificateTemplateName, String certificateTemplatePath, Integer operatorId, Integer isDefault, Integer tenantId, Integer orgId, Date createDate, Integer certificateTemplateType, Integer usedTimes, Integer openScope, Integer status) {
        this.id = id;
        this.certificateTemplateName = certificateTemplateName;
        this.certificateTemplatePath = certificateTemplatePath;
        this.operatorId = operatorId;
        this.isDefault = isDefault;
        this.tenantId = tenantId;
        this.orgId = orgId;
        this.createDate = createDate;
        this.certificateTemplateType = certificateTemplateType;
        this.usedTimes = usedTimes;
        this.openScope = openScope;
        this.status = status;
    }

    public MtMixTrainCertificateTemplate() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCertificateTemplateName() {
        return certificateTemplateName;
    }

    public void setCertificateTemplateName(String certificateTemplateName) {
        this.certificateTemplateName = certificateTemplateName == null ? null : certificateTemplateName.trim();
    }

    public String getCertificateTemplatePath() {
        return certificateTemplatePath;
    }

    public void setCertificateTemplatePath(String certificateTemplatePath) {
        this.certificateTemplatePath = certificateTemplatePath == null ? null : certificateTemplatePath.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCertificateTemplateType() {
        return certificateTemplateType;
    }

    public void setCertificateTemplateType(Integer certificateTemplateType) {
        this.certificateTemplateType = certificateTemplateType;
    }

    public Integer getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(Integer usedTimes) {
        this.usedTimes = usedTimes;
    }

    public Integer getOpenScope() {
        return openScope;
    }

    public void setOpenScope(Integer openScope) {
        this.openScope = openScope;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}