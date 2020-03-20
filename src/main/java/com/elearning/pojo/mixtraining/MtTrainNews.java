package com.elearning.pojo.mixtraining;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MtTrainNews {
    private Integer newsId;

    private Integer operatorId;

    private String newsTitle;

    //@DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化 -- 未测试
    //@JSONField(format="yyyy-MM-dd")//数据库导出页面时json格式化 -- 未测试
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    private Integer trainId;

    private Integer tenantId;

    private Integer recommendTag;

    private Integer status;

    private Integer newsType;

    private Integer orgId;

    private String orgName;

    private Integer subNewsType;

    private String trainName;//数据库中没有，根据科协和继续教育网的实体类相应添加的

    public MtTrainNews(Integer newsId, Integer operatorId, String newsTitle, Date createTime, Integer trainId, Integer tenantId, Integer recommendTag, Integer status, Integer newsType, Integer orgId, String orgName, Integer subNewsType) {
        this.newsId = newsId;
        this.operatorId = operatorId;
        this.newsTitle = newsTitle;
        this.createTime = createTime;
        this.trainId = trainId;
        this.tenantId = tenantId;
        this.recommendTag = recommendTag;
        this.status = status;
        this.newsType = newsType;
        this.orgId = orgId;
        this.orgName = orgName;
        this.subNewsType = subNewsType;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public MtTrainNews() {
        super();
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
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getRecommendTag() {
        return recommendTag;
    }

    public void setRecommendTag(Integer recommendTag) {
        this.recommendTag = recommendTag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getSubNewsType() {
        return subNewsType;
    }

    public void setSubNewsType(Integer subNewsType) {
        this.subNewsType = subNewsType;
    }
}