package com.elearning.vo.mixtraining;

import java.util.Date;


public class MtTrainNewsTemplateForm {

    private Integer newsTemplateId;
    private String newsTemplateName;
    private Date newsTemplateDate;
    private Integer newsId;
    private Integer operatorId;
    private String newsTitle;
    private String newsAbstract;
    private String newsContent;

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
        this.newsTemplateName = newsTemplateName;
    }

    public Date getNewsTemplateDate() {
        return newsTemplateDate;
    }

    public void setNewsTemplateDate(Date newsTemplateDate) {
        this.newsTemplateDate = newsTemplateDate;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}
