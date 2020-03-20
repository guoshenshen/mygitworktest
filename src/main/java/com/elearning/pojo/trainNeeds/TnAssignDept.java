package com.elearning.pojo.trainNeeds;

public class TnAssignDept {
    private Integer ID;

    private Integer basicInfoID;

    private Integer deptID;

    public TnAssignDept(Integer ID, Integer basicInfoID, Integer deptID) {
        this.ID = ID;
        this.basicInfoID = basicInfoID;
        this.deptID = deptID;
    }

    public TnAssignDept() {
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

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }
}