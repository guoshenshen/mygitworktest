package com.elearning.pojo.pub;

import java.util.Date;

public class TrainWithBLOBs extends Train {

    private String trainingContent;

    private String trainGoal;

    private String comment;

    private String train_ewm;

    private String schedule_ewm;

    public TrainWithBLOBs(Integer ID, Byte isPlaned, Long trainPlanID, String trainPlanName, String trainID, String trainName, Integer trainWay, Boolean isStationTrain, Integer trainTypeID, Integer subTrainTypeID, Integer programNo, Date startTime, Date endTime, String sponsorName, String organizerName, String telephone, String organizerEmail, Double classHour, Double days, String stationId, String attendants, Integer attendantCount, Integer ifBJ, String location, Boolean isEnrolled, Boolean isIssued, Boolean isNeedCheck, Date programStartTime, Date programEndTime, String sponsorID, String organizerID, Integer orgId, String orgName, String year, Integer invlCount, Integer operatorId, String keyWordsTag, Integer openScope, Integer iconId, Integer topbandId, Integer creamProject, Integer trainMode, Integer recommendpicId, Integer recommendFlag, Integer tenantId, Integer upTenantId, Integer attendedCount, Integer monitorId, String monitorName, Double fee, Integer isNoted, Integer researchPostNum, Integer managePostNum, Integer supportPostNum, Integer outSideNum, Integer workerNum, String feeChannel, String approveStatusName, Integer implStatusId, Integer cad_report, Integer item_type, Integer pubStatus, Integer approveStatus, Integer reported, Integer allowSubordinateViewStatistics, Integer feeLevel, String openOrgSEQ, String imgUrl, String auxiliary, Double expenseFee, String trainingContent, String trainGoal, String comment, String train_ewm, String schedule_ewm) {
        super(ID, isPlaned, trainPlanID, trainPlanName, trainID, trainName, trainWay, isStationTrain, trainTypeID, subTrainTypeID, programNo, startTime, endTime, sponsorName, organizerName, telephone, organizerEmail, classHour, days, stationId, attendants, attendantCount, ifBJ, location, isEnrolled, isIssued, isNeedCheck, programStartTime, programEndTime, sponsorID, organizerID, orgId, orgName, year, invlCount, operatorId, keyWordsTag, openScope, iconId, topbandId, creamProject, trainMode, recommendpicId, recommendFlag, tenantId, upTenantId, attendedCount, monitorId, monitorName, fee, isNoted, researchPostNum, managePostNum, supportPostNum, outSideNum, workerNum, feeChannel, approveStatusName, implStatusId, cad_report, item_type, pubStatus, approveStatus, reported, allowSubordinateViewStatistics, feeLevel, openOrgSEQ, imgUrl, auxiliary, expenseFee);
        this.trainingContent = trainingContent;
        this.trainGoal = trainGoal;
        this.comment = comment;
        this.train_ewm = train_ewm;
        this.schedule_ewm = schedule_ewm;
    }

    public TrainWithBLOBs() {
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

    public String getTrain_ewm() {
        return train_ewm;
    }

    public void setTrain_ewm(String train_ewm) {
        this.train_ewm = train_ewm == null ? null : train_ewm.trim();
    }

    public String getSchedule_ewm() {
        return schedule_ewm;
    }

    public void setSchedule_ewm(String schedule_ewm) {
        this.schedule_ewm = schedule_ewm == null ? null : schedule_ewm.trim();
    }
}