package com.elearning.pojo.statistics;

public class UscUserLearnStatics extends UscUserLearnStaticsKey {

    private String operatorName;

    private Integer orgId;

    private String orgName;

    private Integer courseNum;

    private Long onlineTime;

    private Long offlineTrainTime;

    private Long totalTime;

    private Integer submitRscNum;

    private Integer submitBkNum;

    private Integer submitTtNum;

    private Integer tenantId;

    private Long onlineTime2;

    private Long offlineSelfLearningTime;

    private Long overseasStudyTime;

    public UscUserLearnStatics(Integer operatorId, Integer year, Integer month, String operatorName, Integer orgId, String orgName, Integer courseNum, Long onlineTime, Long offlineTrainTime, Long totalTime, Integer submitRscNum, Integer submitBkNum, Integer submitTtNum, Integer tenantId, Long onlineTime2, Long offlineSelfLearningTime, Long overseasStudyTime) {
        super(operatorId, year, month);
        this.operatorName = operatorName;
        this.orgId = orgId;
        this.orgName = orgName;
        this.courseNum = courseNum;
        this.onlineTime = onlineTime;
        this.offlineTrainTime = offlineTrainTime;
        this.totalTime = totalTime;
        this.submitRscNum = submitRscNum;
        this.submitBkNum = submitBkNum;
        this.submitTtNum = submitTtNum;
        this.tenantId = tenantId;
        this.onlineTime2 = onlineTime2;
        this.offlineSelfLearningTime = offlineSelfLearningTime;
        this.overseasStudyTime = overseasStudyTime;
    }

    public UscUserLearnStatics() {
        super();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public Long getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Long onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Long getOfflineTrainTime() {
        return offlineTrainTime;
    }

    public void setOfflineTrainTime(Long offlineTrainTime) {
        this.offlineTrainTime = offlineTrainTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getSubmitRscNum() {
        return submitRscNum;
    }

    public void setSubmitRscNum(Integer submitRscNum) {
        this.submitRscNum = submitRscNum;
    }

    public Integer getSubmitBkNum() {
        return submitBkNum;
    }

    public void setSubmitBkNum(Integer submitBkNum) {
        this.submitBkNum = submitBkNum;
    }

    public Integer getSubmitTtNum() {
        return submitTtNum;
    }

    public void setSubmitTtNum(Integer submitTtNum) {
        this.submitTtNum = submitTtNum;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Long getOnlineTime2() {
        return onlineTime2;
    }

    public void setOnlineTime2(Long onlineTime2) {
        this.onlineTime2 = onlineTime2;
    }

    public Long getOfflineSelfLearningTime() {
        return offlineSelfLearningTime;
    }

    public void setOfflineSelfLearningTime(Long offlineSelfLearningTime) {
        this.offlineSelfLearningTime = offlineSelfLearningTime;
    }

    public Long getOverseasStudyTime() {
        return overseasStudyTime;
    }

    public void setOverseasStudyTime(Long overseasStudyTime) {
        this.overseasStudyTime = overseasStudyTime;
    }
}