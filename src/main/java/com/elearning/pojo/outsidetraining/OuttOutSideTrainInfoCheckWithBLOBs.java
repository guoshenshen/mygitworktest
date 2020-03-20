package com.elearning.pojo.outsidetraining;

import java.util.Date;

public class OuttOutSideTrainInfoCheckWithBLOBs extends OuttOutSideTrainInfoCheck {
    private String trainGoal;

    private String trainingContent;

    private String documentUpload;

    public OuttOutSideTrainInfoCheckWithBLOBs(Integer ID, Integer userID, String trainName, Date startTime, Date endTime, Double classHour, String location, String sponsorDept, Integer approverID, Date approveDate, Integer approveStatus, String comment, String year, Integer trainTypeID, Integer subTrainTypeID, Integer tenantId, Long trainPlanID, Double fee, Integer outSideTrainingType, String muploadlink, String muploadname, String trainGoal, String trainingContent, String documentUpload) {
        super(ID, userID, trainName, startTime, endTime, classHour, location, sponsorDept, approverID, approveDate, approveStatus, comment, year, trainTypeID, subTrainTypeID, tenantId, trainPlanID, fee, outSideTrainingType, muploadlink, muploadname);
        this.trainGoal = trainGoal;
        this.trainingContent = trainingContent;
        this.documentUpload = documentUpload;
    }

    public OuttOutSideTrainInfoCheckWithBLOBs() {
        super();
    }

    public String getTrainGoal() {
        return trainGoal;
    }

    public void setTrainGoal(String trainGoal) {
        this.trainGoal = trainGoal == null ? null : trainGoal.trim();
    }

    public String getTrainingContent() {
        return trainingContent;
    }

    public void setTrainingContent(String trainingContent) {
        this.trainingContent = trainingContent == null ? null : trainingContent.trim();
    }

    public String getDocumentUpload() {
        return documentUpload;
    }

    public void setDocumentUpload(String documentUpload) {
        this.documentUpload = documentUpload == null ? null : documentUpload.trim();
    }
}