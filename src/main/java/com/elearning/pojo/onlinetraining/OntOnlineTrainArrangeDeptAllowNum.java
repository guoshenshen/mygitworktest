package com.elearning.pojo.onlinetraining;

public class OntOnlineTrainArrangeDeptAllowNum {
    private Integer ID;

    private Integer arrangeID;

    private Integer deptID;

    private Integer num;

    public OntOnlineTrainArrangeDeptAllowNum(Integer ID, Integer arrangeID, Integer deptID, Integer num) {
        this.ID = ID;
        this.arrangeID = arrangeID;
        this.deptID = deptID;
        this.num = num;
    }

    public OntOnlineTrainArrangeDeptAllowNum() {
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}