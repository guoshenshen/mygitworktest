package com.elearning.pojo.plan;

import java.util.Date;

public class TpTrainPlanDetail {
    private Long ID;

    private String year;

    private Integer level;

    private String trainName;

    private String planTime;

    private Date startTime;

    private Date endTime;

    private Integer orgID;

    private String orgName;

    private String organizerID;

    private String organizerName;

    private String organizerEmail;

    private String sponsorID;

    private String sponsorName;

    private String telephone;

    private Integer planType;

    private Integer trainTypeID;

    private Integer subTrainTypeID;

    private Integer pubStatus;

    private Integer approveStatusID;

    private Integer implStatusID;

    private Integer openScope;

    private Integer creamProject;

    private Integer trainMode;

    private Integer tenantId;

    private Double fee;

    private Double classHour;

    private Integer ifBJ;

    private String location;

    private Integer attendantCount;

    private String attendants;

    private Integer operatorId;

    private String operatorName;

    private Integer sponsorType;

    private String stationId;

    private String comment;

    private Double fee1;

    private Double fee2;

    private Double fee3;

    private Double fee4;

    private Integer isShared;

    private Integer sharedCourseNum;

    private Double days;

    private Integer isNoted;

    private String feeChannel;

    private String approveStatusName;

    private String trainCode;

    private Integer cad_report;

    private Integer casSupport;

    private Date lastOperateTime;

    private Integer lastOperateId;

    private Date casUpload;

    private Date arpUpload;

    private Integer temp;

    private Integer reported;

    private String supportDelegate;

    private Integer feeLevel;

    private Boolean deletable;

    public TpTrainPlanDetail(Long ID, String year, Integer level, String trainName, String planTime, Date startTime, Date endTime, Integer orgID, String orgName, String organizerID, String organizerName, String organizerEmail, String sponsorID, String sponsorName, String telephone, Integer planType, Integer trainTypeID, Integer subTrainTypeID, Integer pubStatus, Integer approveStatusID, Integer implStatusID, Integer openScope, Integer creamProject, Integer trainMode, Integer tenantId, Double fee, Double classHour, Integer ifBJ, String location, Integer attendantCount, String attendants, Integer operatorId, String operatorName, Integer sponsorType, String stationId, String comment, Double fee1, Double fee2, Double fee3, Double fee4, Integer isShared, Integer sharedCourseNum, Double days, Integer isNoted, String feeChannel, String approveStatusName, String trainCode, Integer cad_report, Integer casSupport, Date lastOperateTime, Integer lastOperateId, Date casUpload, Date arpUpload, Integer temp, Integer reported, String supportDelegate, Integer feeLevel, Boolean deletable) {
        this.ID = ID;
        this.year = year;
        this.level = level;
        this.trainName = trainName;
        this.planTime = planTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orgID = orgID;
        this.orgName = orgName;
        this.organizerID = organizerID;
        this.organizerName = organizerName;
        this.organizerEmail = organizerEmail;
        this.sponsorID = sponsorID;
        this.sponsorName = sponsorName;
        this.telephone = telephone;
        this.planType = planType;
        this.trainTypeID = trainTypeID;
        this.subTrainTypeID = subTrainTypeID;
        this.pubStatus = pubStatus;
        this.approveStatusID = approveStatusID;
        this.implStatusID = implStatusID;
        this.openScope = openScope;
        this.creamProject = creamProject;
        this.trainMode = trainMode;
        this.tenantId = tenantId;
        this.fee = fee;
        this.classHour = classHour;
        this.ifBJ = ifBJ;
        this.location = location;
        this.attendantCount = attendantCount;
        this.attendants = attendants;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.sponsorType = sponsorType;
        this.stationId = stationId;
        this.comment = comment;
        this.fee1 = fee1;
        this.fee2 = fee2;
        this.fee3 = fee3;
        this.fee4 = fee4;
        this.isShared = isShared;
        this.sharedCourseNum = sharedCourseNum;
        this.days = days;
        this.isNoted = isNoted;
        this.feeChannel = feeChannel;
        this.approveStatusName = approveStatusName;
        this.trainCode = trainCode;
        this.cad_report = cad_report;
        this.casSupport = casSupport;
        this.lastOperateTime = lastOperateTime;
        this.lastOperateId = lastOperateId;
        this.casUpload = casUpload;
        this.arpUpload = arpUpload;
        this.temp = temp;
        this.reported = reported;
        this.supportDelegate = supportDelegate;
        this.feeLevel = feeLevel;
        this.deletable = deletable;
    }

    public TpTrainPlanDetail() {
        super();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName == null ? null : trainName.trim();
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime == null ? null : planTime.trim();
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

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrganizerID() {
        return organizerID;
    }

    public void setOrganizerID(String organizerID) {
        this.organizerID = organizerID == null ? null : organizerID.trim();
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName == null ? null : organizerName.trim();
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail == null ? null : organizerEmail.trim();
    }

    public String getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID == null ? null : sponsorID.trim();
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName == null ? null : sponsorName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
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

    public Integer getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(Integer pubStatus) {
        this.pubStatus = pubStatus;
    }

    public Integer getApproveStatusID() {
        return approveStatusID;
    }

    public void setApproveStatusID(Integer approveStatusID) {
        this.approveStatusID = approveStatusID;
    }

    public Integer getImplStatusID() {
        return implStatusID;
    }

    public void setImplStatusID(Integer implStatusID) {
        this.implStatusID = implStatusID;
    }

    public Integer getOpenScope() {
        return openScope;
    }

    public void setOpenScope(Integer openScope) {
        this.openScope = openScope;
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

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getClassHour() {
        return classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
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

    public Integer getAttendantCount() {
        return attendantCount;
    }

    public void setAttendantCount(Integer attendantCount) {
        this.attendantCount = attendantCount;
    }

    public String getAttendants() {
        return attendants;
    }

    public void setAttendants(String attendants) {
        this.attendants = attendants == null ? null : attendants.trim();
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
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Integer getSponsorType() {
        return sponsorType;
    }

    public void setSponsorType(Integer sponsorType) {
        this.sponsorType = sponsorType;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId == null ? null : stationId.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Double getFee1() {
        return fee1;
    }

    public void setFee1(Double fee1) {
        this.fee1 = fee1;
    }

    public Double getFee2() {
        return fee2;
    }

    public void setFee2(Double fee2) {
        this.fee2 = fee2;
    }

    public Double getFee3() {
        return fee3;
    }

    public void setFee3(Double fee3) {
        this.fee3 = fee3;
    }

    public Double getFee4() {
        return fee4;
    }

    public void setFee4(Double fee4) {
        this.fee4 = fee4;
    }

    public Integer getIsShared() {
        return isShared;
    }

    public void setIsShared(Integer isShared) {
        this.isShared = isShared;
    }

    public Integer getSharedCourseNum() {
        return sharedCourseNum;
    }

    public void setSharedCourseNum(Integer sharedCourseNum) {
        this.sharedCourseNum = sharedCourseNum;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public Integer getIsNoted() {
        return isNoted;
    }

    public void setIsNoted(Integer isNoted) {
        this.isNoted = isNoted;
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

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode == null ? null : trainCode.trim();
    }

    public Integer getCad_report() {
        return cad_report;
    }

    public void setCad_report(Integer cad_report) {
        this.cad_report = cad_report;
    }

    public Integer getCasSupport() {
        return casSupport;
    }

    public void setCasSupport(Integer casSupport) {
        this.casSupport = casSupport;
    }

    public Date getLastOperateTime() {
        return lastOperateTime;
    }

    public void setLastOperateTime(Date lastOperateTime) {
        this.lastOperateTime = lastOperateTime;
    }

    public Integer getLastOperateId() {
        return lastOperateId;
    }

    public void setLastOperateId(Integer lastOperateId) {
        this.lastOperateId = lastOperateId;
    }

    public Date getCasUpload() {
        return casUpload;
    }

    public void setCasUpload(Date casUpload) {
        this.casUpload = casUpload;
    }

    public Date getArpUpload() {
        return arpUpload;
    }

    public void setArpUpload(Date arpUpload) {
        this.arpUpload = arpUpload;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getReported() {
        return reported;
    }

    public void setReported(Integer reported) {
        this.reported = reported;
    }

    public String getSupportDelegate() {
        return supportDelegate;
    }

    public void setSupportDelegate(String supportDelegate) {
        this.supportDelegate = supportDelegate == null ? null : supportDelegate.trim();
    }

    public Integer getFeeLevel() {
        return feeLevel;
    }

    public void setFeeLevel(Integer feeLevel) {
        this.feeLevel = feeLevel;
    }

    public Boolean getDeletable() {
        return deletable;
    }

    public void setDeletable(Boolean deletable) {
        this.deletable = deletable;
    }
}