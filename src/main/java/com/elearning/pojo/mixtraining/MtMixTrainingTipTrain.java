package com.elearning.pojo.mixtraining;

public class MtMixTrainingTipTrain extends MtMixTrainingTipTrainKey {
    private String status;

    private String isEmail;

    public MtMixTrainingTipTrain(Integer tipId, Integer trainId, String status, String isEmail) {
        super(tipId, trainId);
        this.status = status;
        this.isEmail = isEmail;
    }

    public MtMixTrainingTipTrain() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(String isEmail) {
        this.isEmail = isEmail == null ? null : isEmail.trim();
    }
}