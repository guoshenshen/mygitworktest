package com.elearning.pojo.trainEva;

public class TeCourseEvaAssignUser {
    private Integer ID;

    private Integer evaID;

    private Long courseID;

    private Integer userID;

    private Integer trainID;

    public TeCourseEvaAssignUser(Integer ID, Integer evaID, Long courseID, Integer userID, Integer trainID) {
        this.ID = ID;
        this.evaID = evaID;
        this.courseID = courseID;
        this.userID = userID;
        this.trainID = trainID;
    }

    public TeCourseEvaAssignUser() {
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

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }
}