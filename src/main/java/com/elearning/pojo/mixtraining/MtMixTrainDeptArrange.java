package com.elearning.pojo.mixtraining;

public class MtMixTrainDeptArrange {
    private Integer ID;

    private Integer trainID;

    private Integer deptID;

    public MtMixTrainDeptArrange(Integer ID, Integer trainID, Integer deptID) {
        this.ID = ID;
        this.trainID = trainID;
        this.deptID = deptID;
    }

    public MtMixTrainDeptArrange() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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