package com.elearning.pojo.message;

public class VMsgUser {
    private Integer ID;

    private Integer userID;

    public VMsgUser(Integer ID, Integer userID) {
        this.ID = ID;
        this.userID = userID;
    }

    public VMsgUser() {
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