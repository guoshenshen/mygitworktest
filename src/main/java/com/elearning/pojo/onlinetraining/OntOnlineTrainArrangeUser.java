package com.elearning.pojo.onlinetraining;

public class OntOnlineTrainArrangeUser {
    private Integer ID;

    private Integer arrangeID;

    private Integer userID;

    public OntOnlineTrainArrangeUser(Integer ID, Integer arrangeID, Integer userID) {
        this.ID = ID;
        this.arrangeID = arrangeID;
        this.userID = userID;
    }

    public OntOnlineTrainArrangeUser() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getArrangeID() {
        return arrangeID;
    }

    public void setArrangeID(Integer arrangeID) {
        this.arrangeID = arrangeID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}