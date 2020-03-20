package com.elearning.pojo.pub;

public class TrainOrganization {
    private Integer id;

    private Integer orgId;

    private Integer trainId;

    public TrainOrganization(Integer id, Integer orgId, Integer trainId) {
        this.id = id;
        this.orgId = orgId;
        this.trainId = trainId;
    }

    public TrainOrganization() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }
}