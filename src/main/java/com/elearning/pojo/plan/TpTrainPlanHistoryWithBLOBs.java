package com.elearning.pojo.plan;

import java.util.Date;

public class TpTrainPlanHistoryWithBLOBs extends TpTrainPlanHistory {
    private String trainGoal;

    private String trainContent;

    private String notedMaterial;

    public TpTrainPlanHistoryWithBLOBs(Long ID, Long planId, String year, Integer level, String trainName, String planTime, Date startTime, Date endTime, Integer orgID, String orgName, String organizerID, String organizerName, String organizerEmail, String sponsorID, String sponsorName, String telephone, Integer planType, Integer trainTypeID, Integer subTrainTypeID, Integer openScope, Integer creamProject, Integer trainMode, Integer tenantId, Double fee, Double classHour, Integer ifBJ, String location, Integer attendantCount, String attendants, Integer operatorId, String operatorName, Integer sponsorType, String stationId, String comment, Double fee1, Double fee2, Double fee3, Double fee4, Integer isShared, Integer sharedCourseNum, Double days, Integer isNoted, String feeChannel, String trainCode, Integer cad_report, Integer casSupport, Date lastOperateTime, Integer lastOperateId, String supportDelegate, Integer feeLevel, Date generateTime, String trainGoal, String trainContent, String notedMaterial) {
        super(ID, planId, year, level, trainName, planTime, startTime, endTime, orgID, orgName, organizerID, organizerName, organizerEmail, sponsorID, sponsorName, telephone, planType, trainTypeID, subTrainTypeID, openScope, creamProject, trainMode, tenantId, fee, classHour, ifBJ, location, attendantCount, attendants, operatorId, operatorName, sponsorType, stationId, comment, fee1, fee2, fee3, fee4, isShared, sharedCourseNum, days, isNoted, feeChannel, trainCode, cad_report, casSupport, lastOperateTime, lastOperateId, supportDelegate, feeLevel, generateTime);
        this.trainGoal = trainGoal;
        this.trainContent = trainContent;
        this.notedMaterial = notedMaterial;
    }

    public TpTrainPlanHistoryWithBLOBs() {
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

    public String getNotedMaterial() {
        return notedMaterial;
    }

    public void setNotedMaterial(String notedMaterial) {
        this.notedMaterial = notedMaterial == null ? null : notedMaterial.trim();
    }
}