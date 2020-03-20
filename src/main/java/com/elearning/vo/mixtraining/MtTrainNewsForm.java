package com.elearning.vo.mixtraining;

import java.io.File;
import java.util.Date;


public class MtTrainNewsForm {

    private Integer newsId;
    private Integer operatorId;
    private String newsTitle;
    private String newsContent;
    private String newsAbstract;
    private Date createTime;
    private String recommendTag;
    private String newsType;
    private String trainName;
    private Integer tenantId;
    private Integer status;
    private String orgName;
    private Integer orgId;
    private Integer newsNum;//发布新闻总数
    private Integer newsTotalNum;//新闻总数
    private String author;//作者
    private String subNewsType;//工作流程下一级

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

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRecommendTag() {
        return recommendTag;
    }

    public void setRecommendTag(String recommendTag) {
        this.recommendTag = recommendTag;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getNewsNum() {
        return newsNum;
    }

    public void setNewsNum(Integer newsNum) {
        this.newsNum = newsNum;
    }

    public Integer getNewsTotalNum() {
        return newsTotalNum;
    }

    public void setNewsTotalNum(Integer newsTotalNum) {
        this.newsTotalNum = newsTotalNum;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubNewsType() {
        return subNewsType;
    }

    public void setSubNewsType(String subNewsType) {
        this.subNewsType = subNewsType;
    }
}
