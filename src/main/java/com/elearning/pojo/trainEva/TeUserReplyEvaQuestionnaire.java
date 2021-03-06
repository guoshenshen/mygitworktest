package com.elearning.pojo.trainEva;

public class TeUserReplyEvaQuestionnaire {
    private Integer ID;

    private Integer userID;

    private Integer sex;

    private Integer ageSection;

    private Integer business;

    private Integer evaID;

    public TeUserReplyEvaQuestionnaire(Integer ID, Integer userID, Integer sex, Integer ageSection, Integer business, Integer evaID) {
        this.ID = ID;
        this.userID = userID;
        this.sex = sex;
        this.ageSection = ageSection;
        this.business = business;
        this.evaID = evaID;
    }

    public TeUserReplyEvaQuestionnaire() {
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

    public Integer getEvaID() {
        return evaID;
    }

    public void setEvaID(Integer evaID) {
        this.evaID = evaID;
    }
}