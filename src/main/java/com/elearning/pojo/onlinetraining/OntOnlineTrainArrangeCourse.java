package com.elearning.pojo.onlinetraining;

public class OntOnlineTrainArrangeCourse {
    private Integer ID;

    private Integer arrangeID;

    private Long courseID;

    public OntOnlineTrainArrangeCourse(Integer ID, Integer arrangeID, Long courseID) {
        this.ID = ID;
        this.arrangeID = arrangeID;
        this.courseID = courseID;
    }

    public OntOnlineTrainArrangeCourse() {
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

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }
}