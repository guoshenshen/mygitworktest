package com.elearning.pojo.plan;

import java.util.Date;

public class TpTemplate {
    private Integer id;

    private Integer level;

    private String templateName;

    private Integer period;

    private Integer orgID;

    private String orgName;

    private Integer organizerID;

    private String organizerName;

    private String organizerEmail;

    private String telephone;

    private Integer planType;

    private Integer trainTypeID;

    private Integer tenantId;

    private Double fee;

    private Double classHour;

    private Integer ifBJ;

    private String location;

    private Integer attendantCount;

    private String attendants;

    private Integer sponsorType;

    private String stationId;

    private String comment;

    private Integer sharedCourseNum;

    private Double days;

    private Integer isNoted;

    private String feeChannel;

    private Integer casSupport;

    private Date lastOperateTime;

    private Integer lastOperateId;

    private String supportDelegate;

    private Integer feeLevel;

    private Integer sponsorId;

    private Integer lastOperatorId;

    private Date lastModifyTime;

    public TpTemplate(Integer id, Integer level, String templateName, Integer period, Integer orgID, String orgName, Integer organizerID, String organizerName, String organizerEmail, String telephone, Integer planType, Integer trainTypeID, Integer tenantId, Double fee, Double classHour, Integer ifBJ, String location, Integer attendantCount, String attendants, Integer sponsorType, String stationId, String comment, Integer sharedCourseNum, Double days, Integer isNoted, String feeChannel, Integer casSupport, Date lastOperateTime, Integer lastOperateId, String supportDelegate, Integer feeLevel, Integer sponsorId, Integer lastOperatorId, Date lastModifyTime) {
        this.id = id;
        this.level = level;
        this.templateName = templateName;
        this.period = period;
        this.orgID = orgID;
        this.orgName = orgName;
        this.organizerID = organizerID;
        this.organizerName = organizerName;
        this.organizerEmail = organizerEmail;
        this.telephone = telephone;
        this.planType = planType;
        this.trainTypeID = trainTypeID;
        this.tenantId = tenantId;
        this.fee = fee;
        this.classHour = classHour;
        this.ifBJ = ifBJ;
        this.location = location;
        this.attendantCount = attendantCount;
        this.attendants = attendants;
        this.sponsorType = sponsorType;
        this.stationId = stationId;
        this.comment = comment;
        this.sharedCourseNum = sharedCourseNum;
        this.days = days;
        this.isNoted = isNoted;
        this.feeChannel = feeChannel;
        this.casSupport = casSupport;
        this.lastOperateTime = lastOperateTime;
        this.lastOperateId = lastOperateId;
        this.supportDelegate = supportDelegate;
        this.feeLevel = feeLevel;
        this.sponsorId = sponsorId;
        this.lastOperatorId = lastOperatorId;
        this.lastModifyTime = lastModifyTime;
    }

    public TpTemplate() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
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

    public Integer getOrganizerID() {
        return organizerID;
    }

    public void setOrganizerID(Integer organizerID) {
        this.organizerID = organizerID;
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

    public Integer getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Integer sponsorId) {
        this.sponsorId = sponsorId;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}