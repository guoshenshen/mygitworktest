package com.elearning.pojo.trainNeeds;

import java.util.Date;

public class TnAssignUser {
    private Integer ID;

    private Integer basicInfoID;

    private Integer userID;

    private Boolean valid;

    private Date submitTime;

    public TnAssignUser(Integer ID, Integer basicInfoID, Integer userID, Boolean valid, Date submitTime) {
        this.ID = ID;
        this.basicInfoID = basicInfoID;
        this.userID = userID;
        this.valid = valid;
        this.submitTime = submitTime;
    }

    public TnAssignUser() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getBasicInfoID() {
        return basicInfoID;
    }

    public void setBasicInfoID(Integer basicInfoID) {
        this.basicInfoID = basicInfoID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}