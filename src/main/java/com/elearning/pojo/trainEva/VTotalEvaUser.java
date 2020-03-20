package com.elearning.pojo.trainEva;

public class VTotalEvaUser {
    private Integer ID;

    private Integer userID;

    public VTotalEvaUser(Integer ID, Integer userID) {
        this.ID = ID;
        this.userID = userID;
    }

    public VTotalEvaUser() {
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