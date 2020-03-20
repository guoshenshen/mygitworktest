package com.elearning.pojo.TopicClassStudy;

public class TopicBanner {
    private Integer id;

    private String bannerPicUrl;

    private String description;

    private String title;

    private String bannerClass;

    private String templateClass;

    private Long orgId;

    public TopicBanner(Integer id, String bannerPicUrl, String description, String title, String bannerClass, String templateClass, Long orgId) {
        this.id = id;
        this.bannerPicUrl = bannerPicUrl;
        this.description = description;
        this.title = title;
        this.bannerClass = bannerClass;
        this.templateClass = templateClass;
        this.orgId = orgId;
    }

    public TopicBanner() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannerPicUrl() {
        return bannerPicUrl;
    }

    public void setBannerPicUrl(String bannerPicUrl) {
        this.bannerPicUrl = bannerPicUrl == null ? null : bannerPicUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBannerClass() {
        return bannerClass;
    }

    public void setBannerClass(String bannerClass) {
        this.bannerClass = bannerClass == null ? null : bannerClass.trim();
    }

    public String getTemplateClass() {
        return templateClass;
    }

    public void setTemplateClass(String templateClass) {
        this.templateClass = templateClass == null ? null : templateClass.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}