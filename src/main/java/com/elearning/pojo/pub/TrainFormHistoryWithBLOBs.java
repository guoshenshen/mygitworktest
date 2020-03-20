package com.elearning.pojo.pub;

import java.util.Date;

public class TrainFormHistoryWithBLOBs extends TrainFormHistory {
    private String trainingContent;

    private String trainGoal;

    private String comment;

    public TrainFormHistoryWithBLOBs(Integer ID, Integer train_Id, String trainName, Integer trainWay, Boolean isStationTrain, Integer trainTypeID, Integer subTrainTypeID, Integer programNo, Date startTime, Date endTime, String sponsorName, String organizerName, String telephone, String organizerEmail, Double classHour, Double days, String stationId, String attendants, Integer attendantCount, Integer ifBJ, String location, Boolean isEnrolled, Boolean isIssued, Boolean isNeedCheck, Date programStartTime, Date programEndTime, String sponsorID, String organizerID, Integer orgId, String orgName, String year, Integer invlCount, Integer operatorId, String keyWordsTag, Integer openScope, Integer iconId, Integer topbandId, Integer creamProject, Integer trainMode, Integer tenantId, Integer upTenantId, Integer attendedCount, Integer monitorId, String monitorName, Double fee, Integer isNoted, Integer researchPostNum, Integer managePostNum, Integer supportPostNum, Integer outSideNum, Integer workerNum, String feeChannel, Integer cad_report, Integer item_type, Integer feeLevel, Date generateTime, String trainingContent, String trainGoal, String comment) {
        super(ID, train_Id, trainName, trainWay, isStationTrain, trainTypeID, subTrainTypeID, programNo, startTime, endTime, sponsorName, organizerName, telephone, organizerEmail, classHour, days, stationId, attendants, attendantCount, ifBJ, location, isEnrolled, isIssued, isNeedCheck, programStartTime, programEndTime, sponsorID, organizerID, orgId, orgName, year, invlCount, operatorId, keyWordsTag, openScope, iconId, topbandId, creamProject, trainMode, tenantId, upTenantId, attendedCount, monitorId, monitorName, fee, isNoted, researchPostNum, managePostNum, supportPostNum, outSideNum, workerNum, feeChannel, cad_report, item_type, feeLevel, generateTime);
        this.trainingContent = trainingContent;
        this.trainGoal = trainGoal;
        this.comment = comment;
    }

    public TrainFormHistoryWithBLOBs() {
        super();
    }

    public String getTrainingContent() {
        return trainingContent;
    }

    public void setTrainingContent(String trainingContent) {
        this.trainingContent = trainingContent == null ? null : trainingContent.trim();
    }

    public String getTrainGoal() {
        return trainGoal;
    }

    public void setTrainGoal(String trainGoal) {
        this.trainGoal = trainGoal == null ? null : trainGoal.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}