package com.elearning.pojo.trainEva;

public class TeTrainEvaAssignUser {
    private Integer ID;

    private Integer evaID;

    private Integer trainID;

    private Integer userID;

    public TeTrainEvaAssignUser(Integer ID, Integer evaID, Integer trainID, Integer userID) {
        this.ID = ID;
        this.evaID = evaID;
        this.trainID = trainID;
        this.userID = userID;
    }

    public TeTrainEvaAssignUser() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getEvaID() {
        return evaID;
    }

    public void setEvaID(Integer evaID) {
        this.evaID = evaID;
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
}