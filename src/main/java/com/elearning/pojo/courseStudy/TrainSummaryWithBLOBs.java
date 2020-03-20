package com.elearning.pojo.courseStudy;

import com.elearning.pojo.courseStudy.TrainSummary;

import java.util.Date;

public class TrainSummaryWithBLOBs extends TrainSummary {
    private String conclusion;

    private String overallProfile;

    public TrainSummaryWithBLOBs(Integer id, String year, Integer train_id, Integer operatorId, Date submitDate, String summaryName, String attachmentPath, String orgName, String parentOrgName, String userName, Integer isTrainEffect, Integer multiplyTimes, Double totalFee, Integer completedNum, Double keyCompletedRadio, Integer totalNum, Integer calibratedNum, String comment, Double trainPlanExeRate, Double learnFinishedRate, Integer needFinishedNum, Double totalTime, Double averageTime, Integer tenantId, Date lastModifiedDate, Integer lastEditorOperatorId, Date lastRefreshDataTime, String conclusion, String overallProfile) {
        super(id, year, train_id, operatorId, submitDate, summaryName, attachmentPath, orgName, parentOrgName, userName, isTrainEffect, multiplyTimes, totalFee, completedNum, keyCompletedRadio, totalNum, calibratedNum, comment, trainPlanExeRate, learnFinishedRate, needFinishedNum, totalTime, averageTime, tenantId, lastModifiedDate, lastEditorOperatorId, lastRefreshDataTime);
        this.conclusion = conclusion;
        this.overallProfile = overallProfile;
    }

    public TrainSummaryWithBLOBs() {
        super();
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion == null ? null : conclusion.trim();
    }

    public String getOverallProfile() {
        return overallProfile;
    }

    public void setOverallProfile(String overallProfile) {
        this.overallProfile = overallProfile == null ? null : overallProfile.trim();
    }
}