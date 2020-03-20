package com.elearning.pojo.mixtraining;

public class MtMixTrainingTipTrainKey {
    private Integer tipId;

    private Integer trainId;

    public MtMixTrainingTipTrainKey(Integer tipId, Integer trainId) {
        this.tipId = tipId;
        this.trainId = trainId;
    }

    public MtMixTrainingTipTrainKey() {
        super();
    }

    public Integer getTipId() {
        return tipId;
    }

    public void setTipId(Integer tipId) {
        this.tipId = tipId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }
}