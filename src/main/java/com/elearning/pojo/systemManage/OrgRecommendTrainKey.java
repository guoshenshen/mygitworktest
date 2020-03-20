package com.elearning.pojo.systemManage;

public class OrgRecommendTrainKey {
    private Integer trainId;

    private Integer tenantId;

    public OrgRecommendTrainKey(Integer trainId, Integer tenantId) {
        this.trainId = trainId;
        this.tenantId = tenantId;
    }

    public OrgRecommendTrainKey() {
        super();
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
}