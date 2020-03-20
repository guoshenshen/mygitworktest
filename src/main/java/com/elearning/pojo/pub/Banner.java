package com.elearning.pojo.pub;

import java.util.Date;

public class Banner {
    private Integer bannerId;

    private Integer bannerTemplateId;

    private Date createTime;

    private Date modifyTime;

    private Integer type;

    private String title;

    private String timeInfo;

    private String location;

    private String sponsor;

    private String url;

    private Integer operatorId;

    private String openOrgSEQ;

    private Integer tenantId;

    private Integer visible;

    private Integer needDecorate;

    private Integer isDefault;

    private Integer defaultMonth;

    private Long bannerInfoId;

    private Date publishTime;

    private String bannerDetail;

    private String bannerTag;

    private Integer isHomePageAlwaysVisible;

    private Long orgId;

    private String bannerTemplateUrl;

    public Banner(Integer bannerId, Integer bannerTemplateId, Date createTime, Date modifyTime, Integer type, String title, String timeInfo, String location, String sponsor, String url, Integer operatorId, String openOrgSEQ, Integer tenantId, Integer visible, Integer needDecorate, Integer isDefault, Integer defaultMonth, Long bannerInfoId, Date publishTime, String bannerDetail, String bannerTag, Integer isHomePageAlwaysVisible, Long orgId, String bannerTemplateUrl) {
        this.bannerId = bannerId;
        this.bannerTemplateId = bannerTemplateId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.type = type;
        this.title = title;
        this.timeInfo = timeInfo;
        this.location = location;
        this.sponsor = sponsor;
        this.url = url;
        this.operatorId = operatorId;
        this.openOrgSEQ = openOrgSEQ;
        this.tenantId = tenantId;
        this.visible = visible;
        this.needDecorate = needDecorate;
        this.isDefault = isDefault;
        this.defaultMonth = defaultMonth;
        this.bannerInfoId = bannerInfoId;
        this.publishTime = publishTime;
        this.bannerDetail = bannerDetail;
        this.bannerTag = bannerTag;
        this.isHomePageAlwaysVisible = isHomePageAlwaysVisible;
        this.orgId = orgId;
        this.bannerTemplateUrl = bannerTemplateUrl;
    }

    public Banner() {
        super();
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public Integer getBannerTemplateId() {
        return bannerTemplateId;
    }

    public void setBannerTemplateId(Integer bannerTemplateId) {
        this.bannerTemplateId = bannerTemplateId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(String timeInfo) {
        this.timeInfo = timeInfo == null ? null : timeInfo.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor == null ? null : sponsor.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getNeedDecorate() {
        return needDecorate;
    }

    public void setNeedDecorate(Integer needDecorate) {
        this.needDecorate = needDecorate;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getDefaultMonth() {
        return defaultMonth;
    }

    public void setDefaultMonth(Integer defaultMonth) {
        this.defaultMonth = defaultMonth;
    }

    public Long getBannerInfoId() {
        return bannerInfoId;
    }

    public void setBannerInfoId(Long bannerInfoId) {
        this.bannerInfoId = bannerInfoId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getBannerDetail() {
        return bannerDetail;
    }

    public void setBannerDetail(String bannerDetail) {
        this.bannerDetail = bannerDetail == null ? null : bannerDetail.trim();
    }

    public String getBannerTag() {
        return bannerTag;
    }

    public void setBannerTag(String bannerTag) {
        this.bannerTag = bannerTag == null ? null : bannerTag.trim();
    }

    public Integer getIsHomePageAlwaysVisible() {
        return isHomePageAlwaysVisible;
    }

    public void setIsHomePageAlwaysVisible(Integer isHomePageAlwaysVisible) {
        this.isHomePageAlwaysVisible = isHomePageAlwaysVisible;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getBannerTemplateUrl() {
        return bannerTemplateUrl;
    }

    public void setBannerTemplateUrl(String bannerTemplateUrl) {
        this.bannerTemplateUrl = bannerTemplateUrl == null ? null : bannerTemplateUrl.trim();
    }
}