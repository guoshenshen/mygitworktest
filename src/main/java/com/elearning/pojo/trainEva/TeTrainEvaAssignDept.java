package com.elearning.pojo.trainEva;

public class TeTrainEvaAssignDept {
    private Integer ID;

    private Integer evaID;

    private Integer trainID;

    private Integer deptID;

    public TeTrainEvaAssignDept(Integer ID, Integer evaID, Integer trainID, Integer deptID) {
        this.ID = ID;
        this.evaID = evaID;
        this.trainID = trainID;
        this.deptID = deptID;
    }

    public TeTrainEvaAssignDept() {
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

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }
}