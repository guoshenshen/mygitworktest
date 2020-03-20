package com.elearning.pojo.pub;

import java.util.Date;

public class BannerTemplate {
    private Integer banner_template_id;

    private Integer operatorId;

    private Integer tenantId;

    private String templatePicUrl;

    private String description;

    private String templateName;

    private Integer templateType;

    private String className;

    private Integer publish;

    private String openScopeOrgSeq;

    private Date modifyTime;

    public BannerTemplate(Integer banner_template_id, Integer operatorId, Integer tenantId, String templatePicUrl, String description, String templateName, Integer templateType, String className, Integer publish, String openScopeOrgSeq, Date modifyTime) {
        this.banner_template_id = banner_template_id;
        this.operatorId = operatorId;
        this.tenantId = tenantId;
        this.templatePicUrl = templatePicUrl;
        this.description = description;
        this.templateName = templateName;
        this.templateType = templateType;
        this.className = className;
        this.publish = publish;
        this.openScopeOrgSeq = openScopeOrgSeq;
        this.modifyTime = modifyTime;
    }

    public BannerTemplate() {
        super();
    }

    public Integer getBanner_template_id() {
        return banner_template_id;
    }

    public void setBanner_template_id(Integer banner_template_id) {
        this.banner_template_id = banner_template_id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTemplatePicUrl() {
        return templatePicUrl;
    }

    public void setTemplatePicUrl(String templatePicUrl) {
        this.templatePicUrl = templatePicUrl == null ? null : templatePicUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getPublish() {
        return publish;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    public String getOpenScopeOrgSeq() {
        return openScopeOrgSeq;
    }

    public void setOpenScopeOrgSeq(String openScopeOrgSeq) {
        this.openScopeOrgSeq = openScopeOrgSeq == null ? null : openScopeOrgSeq.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}