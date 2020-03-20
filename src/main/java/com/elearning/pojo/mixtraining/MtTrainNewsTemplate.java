package com.elearning.pojo.mixtraining;

public class MtTrainNewsTemplate {
    private Integer newsTemplateId;

    private String newsTemplateName;

    private String newsTitle;

    private String newsAbstract;

    private String newsContent;

    public MtTrainNewsTemplate(Integer newsTemplateId, String newsTemplateName, String newsTitle, String newsAbstract, String newsContent) {
        this.newsTemplateId = newsTemplateId;
        this.newsTemplateName = newsTemplateName;
        this.newsTitle = newsTitle;
        this.newsAbstract = newsAbstract;
        this.newsContent = newsContent;
    }

    public MtTrainNewsTemplate() {
        super();
    }

    public Integer getNewsTemplateId() {
        return newsTemplateId;
    }

    public void setNewsTemplateId(Integer newsTemplateId) {
        this.newsTemplateId = newsTemplateId;
    }

    public String getNewsTemplateName() {
        return newsTemplateName;
    }

    public void setNewsTemplateName(String newsTemplateName) {
        this.newsTemplateName = newsTemplateName == null ? null : newsTemplateName.trim();
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract == null ? null : newsAbstract.trim();
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent == null ? null : newsContent.trim();
    }
}