package com.elearning.pojo.trainEva;

public class TeCourseEvaAssignDept {
    private Integer ID;

    private Integer evaID;

    private Integer courseID;

    private Integer deptID;

    private Integer trainID;

    public TeCourseEvaAssignDept(Integer ID, Integer evaID, Integer courseID, Integer deptID, Integer trainID) {
        this.ID = ID;
        this.evaID = evaID;
        this.courseID = courseID;
        this.deptID = deptID;
        this.trainID = trainID;
    }

    public TeCourseEvaAssignDept() {
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

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }
}