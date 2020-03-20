package com.elearning.pojo.trainEva;

public class TeTotalEvaAssignDept {
    private Integer ID;

    private Integer evaID;

    private Integer deptID;

    private Integer isAssigned;

    public TeTotalEvaAssignDept(Integer ID, Integer evaID, Integer deptID, Integer isAssigned) {
        this.ID = ID;
        this.evaID = evaID;
        this.deptID = deptID;
        this.isAssigned = isAssigned;
    }

    public TeTotalEvaAssignDept() {
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

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Integer getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Integer isAssigned) {
        this.isAssigned = isAssigned;
    }
}