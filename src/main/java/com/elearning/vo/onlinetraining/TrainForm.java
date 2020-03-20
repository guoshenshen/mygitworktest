package com.elearning.vo.onlinetraining;


import com.elearning.common.CacheUtils;
import com.elearning.pojo.pub.Train;
import com.elearning.util.DateTimeUtil;

import java.io.Serializable;
import java.util.Date;

public class TrainForm implements Serializable {

	private Long trainPlanID = Long.valueOf(-1); //表示没有关联上“培训计划”
	
	private String trainId;

	private String trainName;

	private Integer trainWay;

	private Integer trainTypeId;

	private String startTime;

	private String endTime;

	private Integer programNo;

	private String sponsorName;
	
	private String orgsname; //和sponsorName 同义，为培训基本信息页面添加数据方便使用

	private String organizerName;
	
	private String organizerId;
	
	private Integer orgId;

	private String telephone;

	private String organizerEmail;

	private Double classHour;

	private String trainingContent;

	private String attendants;

	private Integer attendantCount;

	private String trainGoal;

	private String location;

	private Short isEnrolled;

	private Short isIssued;

	private Short isNeedCheck;

	private String programStartTime;

	private String programEndTime;
	
	private String keyWordsTag;
	
	private String trainTypeName;
	
	private String[] trainFormList;
	
	private String trainPlanName;
	
	private Integer picRecommend;
	
	private Integer titleRecommend;
	
	private Integer creamProject;
	
	private Integer trainMode;
	
	private String picRecommender;
	
	private Integer isNoted;
	
	private String comment;
	
	private Integer researchPostNum;
	
	private Integer managePostNum;
	
	private Integer supportPostNum;
	
	private Integer outSideNum;

	private Integer workerNum;
	
	private String feeChannel;

	private Double fee;
	
	private String approveStatusName;
	
	private Integer approveStatus;
	
	private Double days;
	
	private String stationId;
	
	private Integer ifBJ;  //本地、外埠

	private Integer pubstatus;
	
    private Integer implStatusId;
    
    private String implstatusName;
    
    private Integer itemType;  //用来标记人事处反馈回来结果是：精品项目、普通资助项目、普通项目
    
    /**
     * 参会人员类别
     */
    private Integer feeLevel;
    
    private Integer tenantId;

	private String tenantName;
	
	private Integer id;
	
	private Integer operatorId;

	private String operatorName;
	
	private String orgName;
	
	private Integer tagId;
	
	private Integer isStationTrain;
	
	private String year;
	
	private Integer subTrainTypeID;
	
	private Integer isPlaned;
	
	private Integer cad_report;

	private String learnStatus;   //1说明已报名，审批中，2.已报名审核通过 3.已参加 4说明这一培训可以报名 5报名已结束 6.不可报名

	private Integer totalLearnedUser;

	private String score;

	private Integer discussUser;

	private Integer openScope;

	private Integer attendedCount;  //实际参与人数

	private String iconUrl;//图标地址

	private Date startTime_date;

	private Date endTime_date;

	private String imgUrl;

	//-------------------------------------------------------------------------------------------------------------------

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getStartTime_date() {
		return startTime_date;
	}

	public void setStartTime_date(Date startTime_date) {
		this.startTime_date = startTime_date;
	}

	public Date getEndTime_date() {
		return endTime_date;
	}

	public void setEndTime_date(Date endTime_date) {
		this.endTime_date = endTime_date;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Integer getDiscussUser() {
		return discussUser;
	}

	public void setDiscussUser(Integer discussUser) {
		this.discussUser = discussUser;
	}

	public Integer getOpenScope() {
		return openScope;
	}

	public void setOpenScope(Integer openScope) {
		this.openScope = openScope;
	}

	public Integer getAttendedCount() {
		return attendedCount;
	}

	public void setAttendedCount(Integer attendedCount) {
		this.attendedCount = attendedCount;
	}

	public Integer getTotalLearnedUser() {
		return totalLearnedUser;
	}

	public void setTotalLearnedUser(Integer totalLearnedUser) {
		this.totalLearnedUser = totalLearnedUser;
	}

	public String getLearnStatus() {
		return learnStatus;
	}

	public void setLearnStatus(String learnStatus) {
		this.learnStatus = learnStatus;
	}

	public TrainForm() {
	}

	public Long getTrainPlanID() {
		return trainPlanID;
	}

	public void setTrainPlanID(Long trainPlanID) {
		this.trainPlanID = trainPlanID;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public Integer getTrainWay() {
		return trainWay;
	}

	public void setTrainWay(Integer trainWay) {
		this.trainWay = trainWay;
	}

	public Integer getTrainTypeId() {
		return trainTypeId;
	}

	public void setTrainTypeId(Integer trainTypeId) {
		this.trainTypeId = trainTypeId;
	}

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

	public Integer getProgramNo() {
		return programNo;
	}

	public void setProgramNo(Integer programNo) {
		this.programNo = programNo;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public String getOrgsname() {
		return orgsname;
	}

	public void setOrgsname(String orgsname) {
		this.orgsname = orgsname;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public String getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(String organizerId) {
		this.organizerId = organizerId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOrganizerEmail() {
		return organizerEmail;
	}

	public void setOrganizerEmail(String organizerEmail) {
		this.organizerEmail = organizerEmail;
	}

	public Double getClassHour() {
		return classHour;
	}

	public void setClassHour(Double classHour) {
		this.classHour = classHour;
	}

	public String getTrainingContent() {
		return trainingContent;
	}

	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}

	public String getAttendants() {
		return attendants;
	}

	public void setAttendants(String attendants) {
		this.attendants = attendants;
	}

	public Integer getAttendantCount() {
		return attendantCount;
	}

	public void setAttendantCount(Integer attendantCount) {
		this.attendantCount = attendantCount;
	}

	public String getTrainGoal() {
		return trainGoal;
	}

	public void setTrainGoal(String trainGoal) {
		this.trainGoal = trainGoal;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Short getIsEnrolled() {
		return isEnrolled;
	}

	public void setIsEnrolled(Short isEnrolled) {
		this.isEnrolled = isEnrolled;
	}

	public Short getIsIssued() {
		return isIssued;
	}

	public void setIsIssued(Short isIssued) {
		this.isIssued = isIssued;
	}

	public Short getIsNeedCheck() {
		return isNeedCheck;
	}

	public void setIsNeedCheck(Short isNeedCheck) {
		this.isNeedCheck = isNeedCheck;
	}

	public String getProgramStartTime() {
		return programStartTime;
	}

	public void setProgramStartTime(String programStartTime) {
		this.programStartTime = programStartTime;
	}

	public String getProgramEndTime() {
		return programEndTime;
	}

	public void setProgramEndTime(String programEndTime) {
		this.programEndTime = programEndTime;
	}

	public String getKeyWordsTag() {
		return keyWordsTag;
	}

	public void setKeyWordsTag(String keyWordsTag) {
		this.keyWordsTag = keyWordsTag;
	}

	public String getTrainTypeName() {
		return trainTypeName;
	}

	public void setTrainTypeName(String trainTypeName) {
		this.trainTypeName = trainTypeName;
	}

	public String[] getTrainFormList() {
		return trainFormList;
	}

	public void setTrainFormList(String[] trainFormList) {
		this.trainFormList = trainFormList;
	}

	public String getTrainPlanName() {
		return trainPlanName;
	}

	public void setTrainPlanName(String trainPlanName) {
		this.trainPlanName = trainPlanName;
	}

	public Integer getPicRecommend() {
		return picRecommend;
	}

	public void setPicRecommend(Integer picRecommend) {
		this.picRecommend = picRecommend;
	}

	public Integer getTitleRecommend() {
		return titleRecommend;
	}

	public void setTitleRecommend(Integer titleRecommend) {
		this.titleRecommend = titleRecommend;
	}

	public Integer getCreamProject() {
		return creamProject;
	}

	public void setCreamProject(Integer creamProject) {
		this.creamProject = creamProject;
	}

	public Integer getTrainMode() {
		return trainMode;
	}

	public void setTrainMode(Integer trainMode) {
		this.trainMode = trainMode;
	}

	public String getPicRecommender() {
		return picRecommender;
	}

	public void setPicRecommender(String picRecommender) {
		this.picRecommender = picRecommender;
	}

	public Integer getIsNoted() {
		return isNoted;
	}

	public void setIsNoted(Integer isNoted) {
		this.isNoted = isNoted;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getResearchPostNum() {
		return researchPostNum;
	}

	public void setResearchPostNum(Integer researchPostNum) {
		this.researchPostNum = researchPostNum;
	}

	public Integer getManagePostNum() {
		return managePostNum;
	}

	public void setManagePostNum(Integer managePostNum) {
		this.managePostNum = managePostNum;
	}

	public Integer getSupportPostNum() {
		return supportPostNum;
	}

	public void setSupportPostNum(Integer supportPostNum) {
		this.supportPostNum = supportPostNum;
	}

	public Integer getOutSideNum() {
		return outSideNum;
	}

	public void setOutSideNum(Integer outSideNum) {
		this.outSideNum = outSideNum;
	}

	public Integer getWorkerNum() {
		return workerNum;
	}

	public void setWorkerNum(Integer workerNum) {
		this.workerNum = workerNum;
	}

	public String getFeeChannel() {
		return feeChannel;
	}

	public void setFeeChannel(String feeChannel) {
		this.feeChannel = feeChannel;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getApproveStatusName() {
		return approveStatusName;
	}

	public void setApproveStatusName(String approveStatusName) {
		this.approveStatusName = approveStatusName;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Double getDays() {
		return days;
	}

	public void setDays(Double days) {
		this.days = days;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public Integer getIfBJ() {
		return ifBJ;
	}

	public void setIfBJ(Integer ifBJ) {
		this.ifBJ = ifBJ;
	}

	public Integer getImplStatusId() {
		return implStatusId;
	}

	public void setImplStatusId(Integer implStatusId) {
		this.implStatusId = implStatusId;
	}

	public String getImplstatusName() {
		return implstatusName;
	}

	public void setImplstatusName(String implstatusName) {
		this.implstatusName = implstatusName;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Integer getFeeLevel() {
		return feeLevel;
	}

	public void setFeeLevel(Integer feeLevel) {
		this.feeLevel = feeLevel;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Integer getIsStationTrain() {
		return isStationTrain;
	}

	public void setIsStationTrain(Integer isStationTrain) {
		this.isStationTrain = isStationTrain;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getSubTrainTypeID() {
		return subTrainTypeID;
	}

	public void setSubTrainTypeID(Integer subTrainTypeID) {
		this.subTrainTypeID = subTrainTypeID;
	}

	public Integer getIsPlaned() {
		return isPlaned;
	}

	public void setIsPlaned(Integer isPlaned) {
		this.isPlaned = isPlaned;
	}

	public Integer getCad_report() {
		return cad_report;
	}

	public void setCad_report(Integer cad_report) {
		this.cad_report = cad_report;
	}

	public Integer getPubstatus() {
		return pubstatus;
	}

	public void setPubstatus(Integer pubstatus) {
		this.pubstatus = pubstatus;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public TrainForm(Train train) {
		this.trainId = train.getID().toString();
		this.trainName = train.getTrainName();
		if (train.getStartTime() != null) {
			this.startTime = DateTimeUtil.dateToStr(train.getStartTime());

			this.startTime_date = train.getStartTime();
		}
		this.sponsorName = train.getSponsorName();
		if (train.getEndTime() != null) {
			this.endTime = DateTimeUtil.dateToStr(train.getEndTime());
			this.endTime_date = train.getEndTime();
		}
		this.trainWay = train.getTrainWay();
		this.isEnrolled = Short.parseShort(train.getIsEnrolled()==true?"1":"0");
		if (train.getProgramStartTime() != null) {
			this.programStartTime = DateTimeUtil.dateToStr(train.getProgramStartTime());
		}
		if (train.getProgramEndTime() != null) {
			this.programEndTime = DateTimeUtil.dateToStr(train.getProgramEndTime());
		}
		//this.learnStatus = train.;
		this.orgId = train.getOrgId();
		//this.totalLearnedUser = train.;
		//this.discussUser = discussUser;
		//	this.score = train.gets
		//	this.creamProjectName = train;
		//	this.trainTypeName = train.getTrainTypeId();
		this.trainTypeId=train.getTrainTypeID();
		this.location = train.getLocation();
		this.openScope = train.getOpenScope();
		//	this.remainDate = train.;
		this.attendantCount = train.getAttendantCount();
		this.attendedCount = train.getAttendedCount();
		this.attendants = train.getAttendants();
		//this.iconUrl = ;
		//this.iconid = train.getIconId();
		this.tenantId = train.getTenantId();
		this.tenantName = CacheUtils.getTenantName(train.getTenantId());
		this.classHour = train.getClassHour();
		//this.upTenantId = train.getUpTenantId();
		this.isNoted = train.getIsNoted();
		this.itemType = train.getItemType();
		this.implStatusId = train.getImplStatusId();
	}


}
