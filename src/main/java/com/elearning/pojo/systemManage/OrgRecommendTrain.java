package com.elearning.pojo.systemManage;

public class OrgRecommendTrain extends OrgRecommendTrainKey {
    private Integer orgId;

    private Integer recommendPicId;

    private Integer titleRecommend;

    private Integer picRecommend;

    private String picRecommender;

    public OrgRecommendTrain(Integer trainId, Integer tenantId, Integer orgId, Integer recommendPicId, Integer titleRecommend, Integer picRecommend, String picRecommender) {
        super(trainId, tenantId);
        this.orgId = orgId;
        this.recommendPicId = recommendPicId;
        this.titleRecommend = titleRecommend;
        this.picRecommend = picRecommend;
        this.picRecommender = picRecommender;
    }

    public OrgRecommendTrain() {
        super();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getRecommendPicId() {
        return recommendPicId;
    }

    public void setRecommendPicId(Integer recommendPicId) {
        this.recommendPicId = recommendPicId;
    }

    public Integer getTitleRecommend() {
        return titleRecommend;
    }

    public void setTitleRecommend(Integer titleRecommend) {
        this.titleRecommend = titleRecommend;
    }

    public Integer getPicRecommend() {
        return picRecommend;
    }

    public void setPicRecommend(Integer picRecommend) {
        this.picRecommend = picRecommend;
    }

    public String getPicRecommender() {
        return picRecommender;
    }

    public void setPicRecommender(String picRecommender) {
        this.picRecommender = picRecommender == null ? null : picRecommender.trim();
    }
}