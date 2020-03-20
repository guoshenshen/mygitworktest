package com.elearning.pojo.mixtraining;

public class MtMixTrainArrangeDeptArrangeUser {
    private Integer ID;

    private Integer trainID;

    private Integer userID;

    private Integer orgId;

    public MtMixTrainArrangeDeptArrangeUser(Integer ID, Integer trainID, Integer userID, Integer orgId) {
        this.ID = ID;
        this.trainID = trainID;
        this.userID = userID;
        this.orgId = orgId;
    }

    public MtMixTrainArrangeDeptArrangeUser() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}