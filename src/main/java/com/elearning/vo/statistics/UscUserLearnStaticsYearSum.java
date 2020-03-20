package com.elearning.vo.statistics;



import com.elearning.pojo.statistics.UscUserLearnStatics;

/**
 * 按照年度汇总学时时长
 */
public class UscUserLearnStaticsYearSum extends UscUserLearnStatics {

	//	汇总学时时间区间的开始时间，形式yyyy-mm
	private String startTime;

	//	汇总学时时间区间的截止时间，形式yyyy-mm
	private String endTime;

	//  继续教育网上自动记录的在线学时(以小时为单位)
	private Double totalOnlineTime;

	//  培训学时(包括内部培训+外部培训学时)
	private Double totalOfflineTrainTime;

	//  外部网络学时
	private Double totalOnlineTime2;

	//  总网络学时
	private Double totalAllOnlineTime;

	//  总有效网络学时(网络学时每年最多计入50个学时)
	private Double totalEffectiveOnlineTime;

	//  在职自学学时
	private Double totalOfflineSelfLearningTime;

	//  在职自学有效学时(在职自学每年最多计入10个学时)
	private Double totalEffectiveOfflineSelfLearningTime;

	//  公派留学学时
	private Double totalOverseasStudyTime;
	//  公派留学有效学时(公派留学每年最多计入100个学时)
	private Double totalEffectiveOverseasStudyTime;

	//  总学时
	private Double totalTimeMutiMonth;

	//  总有效学时
	private Double totalEffectiveTime;

	//  剩余学时(距离目标学时还有多少学时)
	private Double remainingTrainingHours;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Double getTotalOnlineTime() {
		return totalOnlineTime;
	}

	public void setTotalOnlineTime(Double totalOnlineTime) {
		this.totalOnlineTime = totalOnlineTime;
	}

	public Double getTotalOfflineTrainTime() {
		return totalOfflineTrainTime;
	}

	public void setTotalOfflineTrainTime(Double totalOfflineTrainTime) {
		this.totalOfflineTrainTime = totalOfflineTrainTime;
	}

	public Double getTotalOnlineTime2() {
		return totalOnlineTime2;
	}

	public void setTotalOnlineTime2(Double totalOnlineTime2) {
		this.totalOnlineTime2 = totalOnlineTime2;
	}

	public Double getTotalAllOnlineTime() {
		return totalAllOnlineTime;
	}

	public void setTotalAllOnlineTime(Double totalAllOnlineTime) {
		this.totalAllOnlineTime = totalAllOnlineTime;
	}

	public Double getTotalEffectiveOnlineTime() {
		return totalEffectiveOnlineTime;
	}

	public void setTotalEffectiveOnlineTime(Double totalEffectiveOnlineTime) {
		this.totalEffectiveOnlineTime = totalEffectiveOnlineTime;
	}

	public Double getTotalOfflineSelfLearningTime() {
		return totalOfflineSelfLearningTime;
	}

	public void setTotalOfflineSelfLearningTime(Double totalOfflineSelfLearningTime) {
		this.totalOfflineSelfLearningTime = totalOfflineSelfLearningTime;
	}

	public Double getTotalEffectiveOfflineSelfLearningTime() {
		return totalEffectiveOfflineSelfLearningTime;
	}

	public void setTotalEffectiveOfflineSelfLearningTime(Double totalEffectiveOfflineSelfLearningTime) {
		this.totalEffectiveOfflineSelfLearningTime = totalEffectiveOfflineSelfLearningTime;
	}

	public Double getTotalOverseasStudyTime() {
		return totalOverseasStudyTime;
	}

	public void setTotalOverseasStudyTime(Double totalOverseasStudyTime) {
		this.totalOverseasStudyTime = totalOverseasStudyTime;
	}

	public Double getTotalEffectiveOverseasStudyTime() {
		return totalEffectiveOverseasStudyTime;
	}

	public void setTotalEffectiveOverseasStudyTime(Double totalEffectiveOverseasStudyTime) {
		this.totalEffectiveOverseasStudyTime = totalEffectiveOverseasStudyTime;
	}

	public Double getTotalTimeMutiMonth() {
		return totalTimeMutiMonth;
	}

	public void setTotalTimeMutiMonth(Double totalTimeMutiMonth) {
		this.totalTimeMutiMonth = totalTimeMutiMonth;
	}

	public Double getTotalEffectiveTime() {
		return totalEffectiveTime;
	}

	public void setTotalEffectiveTime(Double totalEffectiveTime) {
		this.totalEffectiveTime = totalEffectiveTime;
	}

	public Double getRemainingTrainingHours() {
		return remainingTrainingHours;
	}

	public void setRemainingTrainingHours(Double remainingTrainingHours) {
		this.remainingTrainingHours = remainingTrainingHours;
	}
}
