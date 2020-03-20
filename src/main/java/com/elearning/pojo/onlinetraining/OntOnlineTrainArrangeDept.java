package com.elearning.pojo.onlinetraining;

public class OntOnlineTrainArrangeDept {
    private Integer ID;

    private Integer arrangeID;

    private Integer deptID;

    public OntOnlineTrainArrangeDept(Integer ID, Integer arrangeID, Integer deptID) {
        this.ID = ID;
        this.arrangeID = arrangeID;
        this.deptID = deptID;
    }

    public OntOnlineTrainArrangeDept() {
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

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }
}