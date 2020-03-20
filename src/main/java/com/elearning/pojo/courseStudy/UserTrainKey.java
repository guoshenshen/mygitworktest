package com.elearning.pojo.courseStudy;

public class UserTrainKey {
    private Integer operatorID;

    private Integer trainId;

    private String year;

    public UserTrainKey(Integer operatorID, Integer trainId, String year) {
        this.operatorID = operatorID;
        this.trainId = trainId;
        this.year = year;
    }

    public UserTrainKey() {
        super();
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }
}