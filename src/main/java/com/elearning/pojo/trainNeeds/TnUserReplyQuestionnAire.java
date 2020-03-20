package com.elearning.pojo.trainNeeds;

import java.util.Date;

public class TnUserReplyQuestionnAire {
    private Integer ID;

    private Integer userID;

    private Integer sex;

    private Integer ageSection;

    private Integer business;

    private Integer basicInfoID;

    private Date submitDate;

    public TnUserReplyQuestionnAire(Integer ID, Integer userID, Integer sex, Integer ageSection, Integer business, Integer basicInfoID, Date submitDate) {
        this.ID = ID;
        this.userID = userID;
        this.sex = sex;
        this.ageSection = ageSection;
        this.business = business;
        this.basicInfoID = basicInfoID;
        this.submitDate = submitDate;
    }

    public TnUserReplyQuestionnAire() {
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAgeSection() {
        return ageSection;
    }

    public void setAgeSection(Integer ageSection) {
        this.ageSection = ageSection;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public Integer getBasicInfoID() {
        return basicInfoID;
    }

    public void setBasicInfoID(Integer basicInfoID) {
        this.basicInfoID = basicInfoID;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }
}