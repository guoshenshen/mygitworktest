package com.elearning.pojo.courseStudy;

public class VUserSelectCourse {
    private Integer ID;

    private String year;

    private Integer courseArrangeID;

    private Long courseID;

    private Integer userID;

    public VUserSelectCourse(Integer ID, String year, Integer courseArrangeID, Long courseID, Integer userID) {
        this.ID = ID;
        this.year = year;
        this.courseArrangeID = courseArrangeID;
        this.courseID = courseID;
        this.userID = userID;
    }

    public VUserSelectCourse() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getCourseArrangeID() {
        return courseArrangeID;
    }

    public void setCourseArrangeID(Integer courseArrangeID) {
        this.courseArrangeID = courseArrangeID;
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
}