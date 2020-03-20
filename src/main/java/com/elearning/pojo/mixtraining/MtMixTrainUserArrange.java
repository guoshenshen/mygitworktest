package com.elearning.pojo.mixtraining;

public class MtMixTrainUserArrange {
    private Integer ID;

    private Integer userID;

    private Integer addrbookId;

    private Integer isJoin;

    public MtMixTrainUserArrange(Integer ID, Integer userID, Integer addrbookId, Integer isJoin) {
        this.ID = ID;
        this.userID = userID;
        this.addrbookId = addrbookId;
        this.isJoin = isJoin;
    }

    public MtMixTrainUserArrange() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getAddrbookId() {
        return addrbookId;
    }

    public void setAddrbookId(Integer addrbookId) {
        this.addrbookId = addrbookId;
    }

    public Integer getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(Integer isJoin) {
        this.isJoin = isJoin;
    }
}