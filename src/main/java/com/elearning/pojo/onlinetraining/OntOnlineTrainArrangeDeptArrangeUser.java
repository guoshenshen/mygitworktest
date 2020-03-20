package com.elearning.pojo.onlinetraining;

public class OntOnlineTrainArrangeDeptArrangeUser {
    private Integer ID;

    private Integer arrangeID;

    private Integer userID;

    private Integer orgID;

    public OntOnlineTrainArrangeDeptArrangeUser(Integer ID, Integer arrangeID, Integer userID, Integer orgID) {
        this.ID = ID;
        this.arrangeID = arrangeID;
        this.userID = userID;
        this.orgID = orgID;
    }

    public OntOnlineTrainArrangeDeptArrangeUser() {
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

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }
}