package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtMixTrainSectionInfoWithBLOBs extends MtMixTrainSectionInfo {
    private String trainGoal;

    private String trainContent;

    public MtMixTrainSectionInfoWithBLOBs(Integer ID, Integer programBasicInfoID, Integer sectionNO, String sectionName, Integer sectionType, Date endTime, String location, Date startTime, Double classHour, Integer arrangeID, String trainGoal, String trainContent) {
        super(ID, programBasicInfoID, sectionNO, sectionName, sectionType, endTime, location, startTime, classHour, arrangeID);
        this.trainGoal = trainGoal;
        this.trainContent = trainContent;
    }

    public MtMixTrainSectionInfoWithBLOBs() {
        super();
    }

    public String getTrainGoal() {
        return trainGoal;
    }

    public void setTrainGoal(String trainGoal) {
        this.trainGoal = trainGoal == null ? null : trainGoal.trim();
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent == null ? null : trainContent.trim();
    }
}