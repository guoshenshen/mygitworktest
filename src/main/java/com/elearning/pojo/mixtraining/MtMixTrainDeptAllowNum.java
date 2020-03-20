package com.elearning.pojo.mixtraining;

public class MtMixTrainDeptAllowNum {
    private Integer id;

    private Integer trainID;

    private Integer deptID;

    private Integer num;

    public MtMixTrainDeptAllowNum(Integer id, Integer trainID, Integer deptID, Integer num) {
        this.id = id;
        this.trainID = trainID;
        this.deptID = deptID;
        this.num = num;
    }

    public MtMixTrainDeptAllowNum() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}