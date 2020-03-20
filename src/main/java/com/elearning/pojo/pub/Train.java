package com.elearning.pojo.pub;

import java.util.Date;

public class Train {
    private Integer ID;

    private Byte isPlaned;

    private Long trainPlanID;

    private String trainPlanName;

    private String trainID;

    private String trainName;

    private Integer trainWay;

    private Boolean isStationTrain;

    private Integer trainTypeID;

    private Integer subTrainTypeID;

    private Integer programNo;

    private Date startTime;

    private Date endTime;

    private String sponsorName;

    private String organizerName;

    private String telephone;

    private String organizerEmail;

    private Double classHour;

    private Double days;

    private String stationId;

    private String attendants;

    private Integer attendantCount;

    private Integer ifBJ;

    private String location;

    private Boolean isEnrolled;

    private Boolean isIssued;

    private Boolean isNeedCheck;

    private Date programStartTime;

    private Date programEndTime;

    private String sponsorID;

    private String organizerID;

    private Integer orgId;

    private String orgName;

    private String year;

    private Integer invlCount;

    private Integer operatorId;

    private String keyWordsTag;

    private Integer openScope;

    private Integer iconId;

    private Integer topbandId;

    private Integer creamProject;

    private Integer trainMode;

    private Integer recommendpicId;

    private Integer recommendFlag;

    private Integer tenantId;

    private Integer upTenantId;

    private Integer attendedCount;

    private Integer monitorId;

    private String monitorName;

    private Double fee;

    private Integer isNoted;

    private Integer researchPostNum;

    private Integer managePostNum;

    private Integer supportPostNum;

    private Integer outSideNum;

    private Integer workerNum;

    private String feeChannel;

    private String approveStatusName;

    private Integer implStatusId;

    private Integer cad_report;

    private Integer item_type;

    private Integer pubStatus;

    private Integer approveStatus;

    private Integer reported;

    private Integer allowSubordinateViewStatistics;

    private Integer feeLevel;

    private String openOrgSEQ;

    private String imgUrl;

    private String auxiliary;

    private Double expenseFee;

    private Integer itemType;  //用来标记人事处反馈回来结果是：精品项目、普通资助项目、普通项目

    private String comment;

    //private Integer isAllowedUnderAdminReg;

    //-----------------------------------数据库中还没有改字段--------------------------------
    private Double neededHours;

    public Double getNeededHours() {
        return neededHours;
    }

    public void setNeededHours(Double neededHours) {
        this.neededHours = neededHours;
    }

    public Train(Integer ID, Byte isPlaned, Long trainPlanID, String trainPlanName, String trainID, String trainName, Integer trainWay, Boolean isStationTrain, Integer trainTypeID, Integer subTrainTypeID, Integer programNo, Date startTime, Date endTime, String sponsorName, String organizerName, String telephone, String organizerEmail, Double classHour, Double days, String stationId, String attendants, Integer attendantCount, Integer ifBJ, String location, Boolean isEnrolled, Boolean isIssued, Boolean isNeedCheck, Date programStartTime, Date programEndTime, String sponsorID, String organizerID, Integer orgId, String orgName, String year, Integer invlCount, Integer operatorId, String keyWordsTag, Integer openScope, Integer iconId, Integer topbandId, Integer creamProject, Integer trainMode, Integer recommendpicId, Integer recommendFlag, Integer tenantId, Integer upTenantId, Integer attendedCount, Integer monitorId, String monitorName, Double fee, Integer isNoted, Integer researchPostNum, Integer managePostNum, Integer supportPostNum, Integer outSideNum, Integer workerNum, String feeChannel, String approveStatusName, Integer implStatusId, Integer cad_report, Integer item_type, Integer pubStatus, Integer approveStatus, Integer reported, Integer allowSubordinateViewStatistics, Integer feeLevel, String openOrgSEQ, String imgUrl, String auxiliary, Double expenseFee) {
        this.ID = ID;
        this.isPlaned = isPlaned;
        this.trainPlanID = trainPlanID;
        this.trainPlanName = trainPlanName;
        this.trainID = trainID;
        this.trainName = trainName;
        this.trainWay = trainWay;
        this.isStationTrain = isStationTrain;
        this.trainTypeID = trainTypeID;
        this.subTrainTypeID = subTrainTypeID;
        this.programNo = programNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sponsorName = sponsorName;
        this.organizerName = organizerName;
        this.telephone = telephone;
        this.organizerEmail = organizerEmail;
        this.classHour = classHour;
        this.days = days;
        this.stationId = stationId;
        this.attendants = attendants;
        this.attendantCount = attendantCount;
        this.ifBJ = ifBJ;
        this.location = location;
        this.isEnrolled = isEnrolled;
        this.isIssued = isIssued;
        this.isNeedCheck = isNeedCheck;
        this.programStartTime = programStartTime;
        this.programEndTime = programEndTime;
        this.sponsorID = sponsorID;
        this.organizerID = organizerID;
        this.orgId = orgId;
        this.orgName = orgName;
        this.year = year;
        this.invlCount = invlCount;
        this.operatorId = operatorId;
        this.keyWordsTag = keyWordsTag;
        this.openScope = openScope;
        this.iconId = iconId;
        this.topbandId = topbandId;
        this.creamProject = creamProject;
        this.trainMode = trainMode;
        this.recommendpicId = recommendpicId;
        this.recommendFlag = recommendFlag;
        this.tenantId = tenantId;
        this.upTenantId = upTenantId;
        this.attendedCount = attendedCount;
        this.monitorId = monitorId;
        this.monitorName = monitorName;
        this.fee = fee;
        this.isNoted = isNoted;
        this.researchPostNum = researchPostNum;
        this.managePostNum = managePostNum;
        this.supportPostNum = supportPostNum;
        this.outSideNum = outSideNum;
        this.workerNum = workerNum;
        this.feeChannel = feeChannel;
        this.approveStatusName = approveStatusName;
        this.implStatusId = implStatusId;
        this.cad_report = cad_report;
        this.item_type = item_type;
        this.pubStatus = pubStatus;
        this.approveStatus = approveStatus;
        this.reported = reported;
        this.allowSubordinateViewStatistics = allowSubordinateViewStatistics;
        this.feeLevel = feeLevel;
        this.openOrgSEQ = openOrgSEQ;
        this.imgUrl = imgUrl;
        this.auxiliary = auxiliary;
        this.expenseFee = expenseFee;
        //this.isAllowedUnderAdminReg = isAllowedUnderAdminReg;

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Train() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Byte getIsPlaned() {
        return isPlaned;
    }

    public void setIsPlaned(Byte isPlaned) {
        this.isPlaned = isPlaned;
    }

    public Long getTrainPlanID() {
        return trainPlanID;
    }

    public void setTrainPlanID(Long trainPlanID) {
        this.trainPlanID = trainPlanID;
    }

    public String getTrainPlanName() {
        return trainPlanName;
    }

    public void setTrainPlanName(String trainPlanName) {
        this.trainPlanName = trainPlanName == null ? null : trainPlanName.trim();
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID == null ? null : trainID.trim();
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName == null ? null : trainName.trim();
    }

    public Integer getTrainWay() {
        return trainWay;
    }

    public void setTrainWay(Integer trainWay) {
        this.trainWay = trainWay;
    }

    public Boolean getIsStationTrain() {
        return isStationTrain;
    }

    public void setIsStationTrain(Boolean isStationTrain) {
        this.isStationTrain = isStationTrain;
    }

    public Integer getTrainTypeID() {
        return trainTypeID;
    }

    public void setTrainTypeID(Integer trainTypeID) {
        this.trainTypeID = trainTypeID;
    }

    public Integer getSubTrainTypeID() {
        return subTrainTypeID;
    }

    public void setSubTrainTypeID(Integer subTrainTypeID) {
        this.subTrainTypeID = subTrainTypeID;
    }

    public Integer getProgramNo() {
        return programNo;
    }

    public void setProgramNo(Integer programNo) {
        this.programNo = programNo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName == null ? null : sponsorName.trim();
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName == null ? null : organizerName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail == null ? null : organizerEmail.trim();
    }

    public Double getClassHour() {
        return classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
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
        this.stationId = stationId == null ? null : stationId.trim();
    }

    public String getAttendants() {
        return attendants;
    }

    public void setAttendants(String attendants) {
        this.attendants = attendants == null ? null : attendants.trim();
    }

    public Integer getAttendantCount() {
        return attendantCount;
    }

    public void setAttendantCount(Integer attendantCount) {
        this.attendantCount = attendantCount;
    }

    public Integer getIfBJ() {
        return ifBJ;
    }

    public void setIfBJ(Integer ifBJ) {
        this.ifBJ = ifBJ;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Boolean getIsEnrolled() {
        return isEnrolled;
    }

    public void setIsEnrolled(Boolean isEnrolled) {
        this.isEnrolled = isEnrolled;
    }

    public Boolean getIsIssued() {
        return isIssued;
    }

    public void setIsIssued(Boolean isIssued) {
        this.isIssued = isIssued;
    }

    public Boolean getIsNeedCheck() {
        return isNeedCheck;
    }

    public void setIsNeedCheck(Boolean isNeedCheck) {
        this.isNeedCheck = isNeedCheck;
    }

    public Date getProgramStartTime() {
        return programStartTime;
    }

    public void setProgramStartTime(Date programStartTime) {
        this.programStartTime = programStartTime;
    }

    public Date getProgramEndTime() {
        return programEndTime;
    }

    public void setProgramEndTime(Date programEndTime) {
        this.programEndTime = programEndTime;
    }

    public String getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID == null ? null : sponsorID.trim();
    }

    public String getOrganizerID() {
        return organizerID;
    }

    public void setOrganizerID(String organizerID) {
        this.organizerID = organizerID == null ? null : organizerID.trim();
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getInvlCount() {
        return invlCount;
    }

    public void setInvlCount(Integer invlCount) {
        this.invlCount = invlCount;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getKeyWordsTag() {
        return keyWordsTag;
    }

    public void setKeyWordsTag(String keyWordsTag) {
        this.keyWordsTag = keyWordsTag == null ? null : keyWordsTag.trim();
    }

    public Integer getOpenScope() {
        return openScope;
    }

    public void setOpenScope(Integer openScope) {
        this.openScope = openScope;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public Integer getTopbandId() {
        return topbandId;
    }

    public void setTopbandId(Integer topbandId) {
        this.topbandId = topbandId;
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

    public Integer getRecommendpicId() {
        return recommendpicId;
    }

    public void setRecommendpicId(Integer recommendpicId) {
        this.recommendpicId = recommendpicId;
    }

    public Integer getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(Integer recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getUpTenantId() {
        return upTenantId;
    }

    public void setUpTenantId(Integer upTenantId) {
        this.upTenantId = upTenantId;
    }

    public Integer getAttendedCount() {
        return attendedCount;
    }

    public void setAttendedCount(Integer attendedCount) {
        this.attendedCount = attendedCount;
    }

    public Integer getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName == null ? null : monitorName.trim();
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getIsNoted() {
        return isNoted;
    }

    public void setIsNoted(Integer isNoted) {
        this.isNoted = isNoted;
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
        this.feeChannel = feeChannel == null ? null : feeChannel.trim();
    }

    public String getApproveStatusName() {
        return approveStatusName;
    }

    public void setApproveStatusName(String approveStatusName) {
        this.approveStatusName = approveStatusName == null ? null : approveStatusName.trim();
    }

    public Integer getImplStatusId() {
        return implStatusId;
    }

    public void setImplStatusId(Integer implStatusId) {
        this.implStatusId = implStatusId;
    }

    public Integer getCad_report() {
        return cad_report;
    }

    public void setCad_report(Integer cad_report) {
        this.cad_report = cad_report;
    }

    public Integer getItem_type() {
        return item_type;
    }

    public void setItem_type(Integer item_type) {
        this.item_type = item_type;
    }

    public Integer getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(Integer pubStatus) {
        this.pubStatus = pubStatus;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Integer getReported() {
        return reported;
    }

    public void setReported(Integer reported) {
        this.reported = reported;
    }

    public Integer getAllowSubordinateViewStatistics() {
        return allowSubordinateViewStatistics;
    }

    public void setAllowSubordinateViewStatistics(Integer allowSubordinateViewStatistics) {
        this.allowSubordinateViewStatistics = allowSubordinateViewStatistics;
    }

    public Integer getFeeLevel() {
        return feeLevel;
    }

    public void setFeeLevel(Integer feeLevel) {
        this.feeLevel = feeLevel;
    }

    public String getOpenOrgSEQ() {
        return openOrgSEQ;
    }

    public void setOpenOrgSEQ(String openOrgSEQ) {
        this.openOrgSEQ = openOrgSEQ == null ? null : openOrgSEQ.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getAuxiliary() {
        return auxiliary;
    }

    public void setAuxiliary(String auxiliary) {
        this.auxiliary = auxiliary == null ? null : auxiliary.trim();
    }

    public Double getExpenseFee() {
        return expenseFee;
    }

    public void setExpenseFee(Double expenseFee) {
        this.expenseFee = expenseFee;
    }



}