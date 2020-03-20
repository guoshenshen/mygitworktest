package com.elearning.pojo.recommend;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RecommendSeries {
    private Integer id;

    private Integer outstandingStandard;

    private Boolean isPublish;

    private String title;

    private String sponsorInfo;

    private String detail;

    private String url;

    private String picUrl;

    private String mainPicUrl;

    private Integer operatorId;

    private String openOrgSEQ;

    private Integer tenantId;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date publishTime;

    private Integer orgId;

    private Short openScope;

    public RecommendSeries(Integer id, Integer outstandingStandard, Boolean isPublish, String title, String sponsorInfo, String detail, String url, String picUrl, String mainPicUrl, Integer operatorId, String openOrgSEQ, Integer tenantId, Date createTime, Date publishTime, Integer orgId, Short openScope) {
        this.id = id;
        this.outstandingStandard = outstandingStandard;
        this.isPublish = isPublish;
        this.title = title;
        this.sponsorInfo = sponsorInfo;
        this.detail = detail;
        this.url = url;
        this.picUrl = picUrl;
        this.mainPicUrl = mainPicUrl;
        this.operatorId = operatorId;
        this.openOrgSEQ = openOrgSEQ;
        this.tenantId = tenantId;
        this.createTime = createTime;
        this.publishTime = publishTime;
        this.orgId = orgId;
        this.openScope = openScope;
    }

    public RecommendSeries() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOutstandingStandard() {
        return outstandingStandard;
    }

    public void setOutstandingStandard(Integer outstandingStandard) {
        this.outstandingStandard = outstandingStandard;
    }

    public Boolean getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Boolean isPublish) {
        this.isPublish = isPublish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSponsorInfo() {
        return sponsorInfo;
    }

    public void setSponsorInfo(String sponsorInfo) {
        this.sponsorInfo = sponsorInfo == null ? null : sponsorInfo.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getMainPicUrl() {
        return mainPicUrl;
    }

    public void setMainPicUrl(String mainPicUrl) {
        this.mainPicUrl = mainPicUrl == null ? null : mainPicUrl.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOpenOrgSEQ() {
        return openOrgSEQ;
    }

    public void setOpenOrgSEQ(String openOrgSEQ) {
        this.openOrgSEQ = openOrgSEQ == null ? null : openOrgSEQ.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Short getOpenScope() {
        return openScope;
    }

    public void setOpenScope(Short openScope) {
        this.openScope = openScope;
    }
}