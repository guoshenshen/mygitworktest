package com.elearning.pojo.trainEva;

public class TeTotalEvaAssignUser {
    private Integer ID;

    private Integer evaID;

    private Integer userID;

    private Integer isAssigned;

    public TeTotalEvaAssignUser(Integer ID, Integer evaID, Integer userID, Integer isAssigned) {
        this.ID = ID;
        this.evaID = evaID;
        this.userID = userID;
        this.isAssigned = isAssigned;
    }

    public TeTotalEvaAssignUser() {
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Integer isAssigned) {
        this.isAssigned = isAssigned;
    }
}