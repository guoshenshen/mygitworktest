package com.elearning.pojo.onlinetraining;

public class TrainTagKey {
    private Integer trainID;

    private Integer tagID;

    public TrainTagKey(Integer trainID, Integer tagID) {
        this.trainID = trainID;
        this.tagID = tagID;
    }

    public TrainTagKey() {
        super();
    }

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }
}