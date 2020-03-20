package com.elearning.pojo.plan;

import java.util.Date;

public class TpTemplateWithBLOBs extends TpTemplate {
    private String trainGoal;

    private String trainContent;

    private String notedMaterial;

    public TpTemplateWithBLOBs(Integer id, Integer level, String templateName, Integer period, Integer orgID, String orgName, Integer organizerID, String organizerName, String organizerEmail, String telephone, Integer planType, Integer trainTypeID, Integer tenantId, Double fee, Double classHour, Integer ifBJ, String location, Integer attendantCount, String attendants, Integer sponsorType, String stationId, String comment, Integer sharedCourseNum, Double days, Integer isNoted, String feeChannel, Integer casSupport, Date lastOperateTime, Integer lastOperateId, String supportDelegate, Integer feeLevel, Integer sponsorId, Integer lastOperatorId, Date lastModifyTime, String trainGoal, String trainContent, String notedMaterial) {
        super(id, level, templateName, period, orgID, orgName, organizerID, organizerName, organizerEmail, telephone, planType, trainTypeID, tenantId, fee, classHour, ifBJ, location, attendantCount, attendants, sponsorType, stationId, comment, sharedCourseNum, days, isNoted, feeChannel, casSupport, lastOperateTime, lastOperateId, supportDelegate, feeLevel, sponsorId, lastOperatorId, lastModifyTime);
        this.trainGoal = trainGoal;
        this.trainContent = trainContent;
        this.notedMaterial = notedMaterial;
    }

    public TpTemplateWithBLOBs() {
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