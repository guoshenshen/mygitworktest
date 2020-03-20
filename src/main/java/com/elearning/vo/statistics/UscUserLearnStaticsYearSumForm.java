package com.elearning.vo.statistics;


import java.util.Date;

/**
 * 汇总某段时间内的学时统计情况（可能是跨年度查询）
 */
public class UscUserLearnStaticsYearSumForm {

	private Integer operatorId;

	private String operatorName;

	private Integer orgId;

	private String orgName;

	private String parentOrgName;

	private Integer totalCourseNum;

	private String tenantName;

	private Integer submitRscNum;

	private Integer submitBkNum;

	private Integer submitTtNum;

	private String startTime;

	private Date startTimeDate;

	private String endTime;

	private Date endTimeDate;

	//   继续教育网上自动记录的在线学时
	private String totalOnlineTime;

	//   培训学时(包括内部培训+外部培训学时)
	private String totalOfflineTrainTime;

	//   外部网络学时
	private String totalOnlineTime2;

	//   总网络学时
	private String totalAllOnlineTime;

	//   总有效网络学时(网络学时每年最多计入50个学时)
	private String totalEffectiveOnlineTime;

	//   在职自学学时
	private String totalOfflineSelfLearningTime;

	//   在职自学有效学时(在职自学每年最多计入10个学时)
	private String totalEffectiveOfflineSelfLearningTime;

	//   公派留学
	private String totalOverseasStudyTime;

	//   公派留学有效学时
	private String totalEffectiveOverseasStudyTime;

	//   总学时
	private String totalTime;

	//   总有效学时
	private String totalEffectiveTime;

	//   培训总学时
	private Double totalTrainingHours;

	//   剩余学时(距离目标学时还有多少学时)
	private Double remainingTrainingHours;

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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
		this.orgName = orgName;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public Integer getTotalCourseNum() {
		return totalCourseNum;
	}

	public void setTotalCourseNum(Integer totalCourseNum) {
		this.totalCourseNum = totalCourseNum;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Date getStartTimeDate() {
		return startTimeDate;
	}

	public void setStartTimeDate(Date startTimeDate) {
		this.startTimeDate = startTimeDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getEndTimeDate() {
		return endTimeDate;
	}

	public void setEndTimeDate(Date endTimeDate) {
		this.endTimeDate = endTimeDate;
	}

	public String getTotalOnlineTime() {
		return totalOnlineTime;
	}

	public void setTotalOnlineTime(String totalOnlineTime) {
		this.totalOnlineTime = totalOnlineTime;
	}

	public String getTotalOfflineTrainTime() {
		return totalOfflineTrainTime;
	}

	public void setTotalOfflineTrainTime(String totalOfflineTrainTime) {
		this.totalOfflineTrainTime = totalOfflineTrainTime;
	}

	public String getTotalOnlineTime2() {
		return totalOnlineTime2;
	}

	public void setTotalOnlineTime2(String totalOnlineTime2) {
		this.totalOnlineTime2 = totalOnlineTime2;
	}

	public String getTotalAllOnlineTime() {
		return totalAllOnlineTime;
	}

	public void setTotalAllOnlineTime(String totalAllOnlineTime) {
		this.totalAllOnlineTime = totalAllOnlineTime;
	}

	public String getTotalEffectiveOnlineTime() {
		return totalEffectiveOnlineTime;
	}

	public void setTotalEffectiveOnlineTime(String totalEffectiveOnlineTime) {
		this.totalEffectiveOnlineTime = totalEffectiveOnlineTime;
	}

	public String getTotalOfflineSelfLearningTime() {
		return totalOfflineSelfLearningTime;
	}

	public void setTotalOfflineSelfLearningTime(String totalOfflineSelfLearningTime) {
		this.totalOfflineSelfLearningTime = totalOfflineSelfLearningTime;
	}

	public String getTotalEffectiveOfflineSelfLearningTime() {
		return totalEffectiveOfflineSelfLearningTime;
	}

	public void setTotalEffectiveOfflineSelfLearningTime(String totalEffectiveOfflineSelfLearningTime) {
		this.totalEffectiveOfflineSelfLearningTime = totalEffectiveOfflineSelfLearningTime;
	}

	public String getTotalOverseasStudyTime() {
		return totalOverseasStudyTime;
	}

	public void setTotalOverseasStudyTime(String totalOverseasStudyTime) {
		this.totalOverseasStudyTime = totalOverseasStudyTime;
	}

	public String getTotalEffectiveOverseasStudyTime() {
		return totalEffectiveOverseasStudyTime;
	}

	public void setTotalEffectiveOverseasStudyTime(String totalEffectiveOverseasStudyTime) {
		this.totalEffectiveOverseasStudyTime = totalEffectiveOverseasStudyTime;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getTotalEffectiveTime() {
		return totalEffectiveTime;
	}

	public void setTotalEffectiveTime(String totalEffectiveTime) {
		this.totalEffectiveTime = totalEffectiveTime;
	}

	public Double getTotalTrainingHours() {
		return totalTrainingHours;
	}

	public void setTotalTrainingHours(Double totalTrainingHours) {
		this.totalTrainingHours = totalTrainingHours;
	}

	public Double getRemainingTrainingHours() {
		return remainingTrainingHours;
	}

	public void setRemainingTrainingHours(Double remainingTrainingHours) {
		this.remainingTrainingHours = remainingTrainingHours;
	}
}
