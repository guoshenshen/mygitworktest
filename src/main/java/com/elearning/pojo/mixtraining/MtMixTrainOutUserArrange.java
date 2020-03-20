package com.elearning.pojo.mixtraining;

public class MtMixTrainOutUserArrange {
    private Integer ID;

    private Integer trainID;

    private Integer userID;

    private Integer status;

    private String comment;

    public MtMixTrainOutUserArrange(Integer ID, Integer trainID, Integer userID, Integer status, String comment) {
        this.ID = ID;
        this.trainID = trainID;
        this.userID = userID;
        this.status = status;
        this.comment = comment;
    }

    public MtMixTrainOutUserArrange() {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}