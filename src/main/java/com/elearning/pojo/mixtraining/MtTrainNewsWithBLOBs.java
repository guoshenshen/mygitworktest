package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtTrainNewsWithBLOBs extends MtTrainNews {

    private String newsContent;

    private String newsAbstract;

    public MtTrainNewsWithBLOBs(Integer newsId, Integer operatorId, String newsTitle, Date createTime, Integer trainId, Integer tenantId, Integer recommendTag, Integer status, Integer newsType, Integer orgId, String orgName, Integer subNewsType, String newsContent, String newsAbstract) {
        super(newsId, operatorId, newsTitle, createTime, trainId, tenantId, recommendTag, status, newsType, orgId, orgName, subNewsType);
        this.newsContent = newsContent;
        this.newsAbstract = newsAbstract;
    }

    public MtTrainNewsWithBLOBs() {
        super();
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent == null ? null : newsContent.trim();
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract == null ? null : newsAbstract.trim();
    }
}