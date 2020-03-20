package com.elearning.pojo.trainNeeds;

public class VNeedQuestUser {
    private Integer ID;

    private Integer userID;

    public VNeedQuestUser(Integer ID, Integer userID) {
        this.ID = ID;
        this.userID = userID;
    }

    public VNeedQuestUser() {
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
}