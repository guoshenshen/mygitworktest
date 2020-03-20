package com.elearning.pojo.pub;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Course {
    private Long courseId;

    private String courseNo;

    private String courseName;

    private Integer category;

    private Integer classfication;

    private Double fee;

    private Integer studyDay;

    private Double score;

    private Double classHour;

    private String suitableObject;

    private Integer pubStatus;

    private String maker;

    private String creator;

    private String produceOrgName;

    private String fundingOrgName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createDate;

    private String remark;

    private String sliceType;

    private String isOpenCourse;

    private String enterUrl;

    private String content;

    private String stationId;

    private String pictureURL;

    private String isSharedCourse;

    private Long uploadOrgId;

    private String uploadOrgName;

    private Long totalvaman;

    private Long totalpoint;

    private Integer orgId;

    private String approveStatus;

    private String serverFilePath;

    private Long serverId;

    private String postId;

    private Integer courseTypeId;

    private String keyWordsTag;

    private String expertAreaId;

    private Integer isUnderScope;

    private Integer tenantId;

    private Long sourceCourseId;

    private Integer openScope;

    private String indexUrl;

    private String breed;

    private String customFilePath;

    private String subExpertAreaId;

    private Date shareDate;

    private Integer upTenantId;

    private Integer qualificationMark;

    private Integer requiredCourseTypeId;

    private Integer isFormal;

    private Integer operatorId;

    private Integer isNoted;

    private Integer isApproved;

    private Integer hasTeacher;

    private Integer teacherId;

    private String keyWords;

    private Integer selectedTimes;

    private String mobilePlayAddress;

    private Integer downloadable;

    private Integer isMultiSize;

    private String openOrgSEQ;

    private Integer sourceTenantId;

    private Integer isFirstLevelDirectory;

    private Integer isCoursePackage;

    private Long lastWatchCourse;

    private String studyProgress;

    public Course(Long courseId, String courseNo, String courseName, Integer category, Integer classfication, Double fee, Integer studyDay, Double score, Double classHour, String suitableObject, Integer pubStatus, String maker, String creator, String produceOrgName, String fundingOrgName, Date createDate, String remark, String sliceType, String isOpenCourse, String enterUrl, String content, String stationId, String pictureURL, String isSharedCourse, Long uploadOrgId, String uploadOrgName, Long totalvaman, Long totalpoint, Integer orgId, String approveStatus, String serverFilePath, Long serverId, String postId, Integer courseTypeId, String keyWordsTag, String expertAreaId, Integer isUnderScope, Integer tenantId, Long sourceCourseId, Integer openScope, String indexUrl, String breed, String customFilePath, String subExpertAreaId, Date shareDate, Integer upTenantId, Integer qualificationMark, Integer requiredCourseTypeId, Integer isFormal, Integer operatorId, Integer isNoted, Integer isApproved, Integer hasTeacher, Integer teacherId, String keyWords, Integer selectedTimes, String mobilePlayAddress, Integer downloadable, Integer isMultiSize, String openOrgSEQ, Integer sourceTenantId, Integer isFirstLevelDirectory, Integer isCoursePackage, Long lastWatchCourse) {
        this.courseId = courseId;
        this.courseNo = courseNo;
        this.courseName = courseName;
        this.category = category;
        this.classfication = classfication;
        this.fee = fee;
        this.studyDay = studyDay;
        this.score = score;
        this.classHour = classHour;
        this.suitableObject = suitableObject;
        this.pubStatus = pubStatus;
        this.maker = maker;
        this.creator = creator;
        this.produceOrgName = produceOrgName;
        this.fundingOrgName = fundingOrgName;
        this.createDate = createDate;
        this.remark = remark;
        this.sliceType = sliceType;
        this.isOpenCourse = isOpenCourse;
        this.enterUrl = enterUrl;
        this.content = content;
        this.stationId = stationId;
        this.pictureURL = pictureURL;
        this.isSharedCourse = isSharedCourse;
        this.uploadOrgId = uploadOrgId;
        this.uploadOrgName = uploadOrgName;
        this.totalvaman = totalvaman;
        this.totalpoint = totalpoint;
        this.orgId = orgId;
        this.approveStatus = approveStatus;
        this.serverFilePath = serverFilePath;
        this.serverId = serverId;
        this.postId = postId;
        this.courseTypeId = courseTypeId;
        this.keyWordsTag = keyWordsTag;
        this.expertAreaId = expertAreaId;
        this.isUnderScope = isUnderScope;
        this.tenantId = tenantId;
        this.sourceCourseId = sourceCourseId;
        this.openScope = openScope;
        this.indexUrl = indexUrl;
        this.breed = breed;
        this.customFilePath = customFilePath;
        this.subExpertAreaId = subExpertAreaId;
        this.shareDate = shareDate;
        this.upTenantId = upTenantId;
        this.qualificationMark = qualificationMark;
        this.requiredCourseTypeId = requiredCourseTypeId;
        this.isFormal = isFormal;
        this.operatorId = operatorId;
        this.isNoted = isNoted;
        this.isApproved = isApproved;
        this.hasTeacher = hasTeacher;
        this.teacherId = teacherId;
        this.keyWords = keyWords;
        this.selectedTimes = selectedTimes;
        this.mobilePlayAddress = mobilePlayAddress;
        this.downloadable = downloadable;
        this.isMultiSize = isMultiSize;
        this.openOrgSEQ = openOrgSEQ;
        this.sourceTenantId = sourceTenantId;
        this.isFirstLevelDirectory = isFirstLevelDirectory;
        this.isCoursePackage = isCoursePackage;
        this.lastWatchCourse = lastWatchCourse;
    }

    public Course() {
        super();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo == null ? null : courseNo.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getClassfication() {
        return classfication;
    }

    public void setClassfication(Integer classfication) {
        this.classfication = classfication;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getStudyDay() {
        return studyDay;
    }

    public void setStudyDay(Integer studyDay) {
        this.studyDay = studyDay;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getClassHour() {
        return classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
    }

    public String getSuitableObject() {
        return suitableObject;
    }

    public void setSuitableObject(String suitableObject) {
        this.suitableObject = suitableObject == null ? null : suitableObject.trim();
    }

    public Integer getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(Integer pubStatus) {
        this.pubStatus = pubStatus;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getProduceOrgName() {
        return produceOrgName;
    }

    public void setProduceOrgName(String produceOrgName) {
        this.produceOrgName = produceOrgName == null ? null : produceOrgName.trim();
    }

    public String getFundingOrgName() {
        return fundingOrgName;
    }

    public void setFundingOrgName(String fundingOrgName) {
        this.fundingOrgName = fundingOrgName == null ? null : fundingOrgName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSliceType() {
        return sliceType;
    }

    public void setSliceType(String sliceType) {
        this.sliceType = sliceType == null ? null : sliceType.trim();
    }

    public String getIsOpenCourse() {
        return isOpenCourse;
    }

    public void setIsOpenCourse(String isOpenCourse) {
        this.isOpenCourse = isOpenCourse == null ? null : isOpenCourse.trim();
    }

    public String getEnterUrl() {
        return enterUrl;
    }

    public void setEnterUrl(String enterUrl) {
        this.enterUrl = enterUrl == null ? null : enterUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId == null ? null : stationId.trim();
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL == null ? null : pictureURL.trim();
    }

    public String getIsSharedCourse() {
        return isSharedCourse;
    }

    public void setIsSharedCourse(String isSharedCourse) {
        this.isSharedCourse = isSharedCourse == null ? null : isSharedCourse.trim();
    }

    public Long getUploadOrgId() {
        return uploadOrgId;
    }

    public void setUploadOrgId(Long uploadOrgId) {
        this.uploadOrgId = uploadOrgId;
    }

    public String getUploadOrgName() {
        return uploadOrgName;
    }

    public void setUploadOrgName(String uploadOrgName) {
        this.uploadOrgName = uploadOrgName == null ? null : uploadOrgName.trim();
    }

    public Long getTotalvaman() {
        return totalvaman;
    }

    public void setTotalvaman(Long totalvaman) {
        this.totalvaman = totalvaman;
    }

    public Long getTotalpoint() {
        return totalpoint;
    }

    public void setTotalpoint(Long totalpoint) {
        this.totalpoint = totalpoint;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus == null ? null : approveStatus.trim();
    }

    public String getServerFilePath() {
        return serverFilePath;
    }

    public void setServerFilePath(String serverFilePath) {
        this.serverFilePath = serverFilePath == null ? null : serverFilePath.trim();
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId == null ? null : postId.trim();
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getKeyWordsTag() {
        return keyWordsTag;
    }

    public void setKeyWordsTag(String keyWordsTag) {
        this.keyWordsTag = keyWordsTag == null ? null : keyWordsTag.trim();
    }

    public String getExpertAreaId() {
        return expertAreaId;
    }

    public void setExpertAreaId(String expertAreaId) {
        this.expertAreaId = expertAreaId == null ? null : expertAreaId.trim();
    }

    public Integer getIsUnderScope() {
        return isUnderScope;
    }

    public void setIsUnderScope(Integer isUnderScope) {
        this.isUnderScope = isUnderScope;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Long getSourceCourseId() {
        return sourceCourseId;
    }

    public void setSourceCourseId(Long sourceCourseId) {
        this.sourceCourseId = sourceCourseId;
    }

    public Integer getOpenScope() {
        return openScope;
    }

    public void setOpenScope(Integer openScope) {
        this.openScope = openScope;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl == null ? null : indexUrl.trim();
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed == null ? null : breed.trim();
    }

    public String getCustomFilePath() {
        return customFilePath;
    }

    public void setCustomFilePath(String customFilePath) {
        this.customFilePath = customFilePath == null ? null : customFilePath.trim();
    }

    public String getSubExpertAreaId() {
        return subExpertAreaId;
    }

    public void setSubExpertAreaId(String subExpertAreaId) {
        this.subExpertAreaId = subExpertAreaId == null ? null : subExpertAreaId.trim();
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }

    public Integer getUpTenantId() {
        return upTenantId;
    }

    public void setUpTenantId(Integer upTenantId) {
        this.upTenantId = upTenantId;
    }

    public Integer getQualificationMark() {
        return qualificationMark;
    }

    public void setQualificationMark(Integer qualificationMark) {
        this.qualificationMark = qualificationMark;
    }

    public Integer getRequiredCourseTypeId() {
        return requiredCourseTypeId;
    }

    public void setRequiredCourseTypeId(Integer requiredCourseTypeId) {
        this.requiredCourseTypeId = requiredCourseTypeId;
    }

    public Integer getIsFormal() {
        return isFormal;
    }

    public void setIsFormal(Integer isFormal) {
        this.isFormal = isFormal;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getIsNoted() {
        return isNoted;
    }

    public void setIsNoted(Integer isNoted) {
        this.isNoted = isNoted;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public Integer getHasTeacher() {
        return hasTeacher;
    }

    public void setHasTeacher(Integer hasTeacher) {
        this.hasTeacher = hasTeacher;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords == null ? null : keyWords.trim();
    }

    public Integer getSelectedTimes() {
        return selectedTimes;
    }

    public void setSelectedTimes(Integer selectedTimes) {
        this.selectedTimes = selectedTimes;
    }

    public String getMobilePlayAddress() {
        return mobilePlayAddress;
    }

    public void setMobilePlayAddress(String mobilePlayAddress) {
        this.mobilePlayAddress = mobilePlayAddress == null ? null : mobilePlayAddress.trim();
    }

    public Integer getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(Integer downloadable) {
        this.downloadable = downloadable;
    }

    public Integer getIsMultiSize() {
        return isMultiSize;
    }

    public void setIsMultiSize(Integer isMultiSize) {
        this.isMultiSize = isMultiSize;
    }

    public String getOpenOrgSEQ() {
        return openOrgSEQ;
    }

    public void setOpenOrgSEQ(String openOrgSEQ) {
        this.openOrgSEQ = openOrgSEQ == null ? null : openOrgSEQ.trim();
    }

    public Integer getSourceTenantId() {
        return sourceTenantId;
    }

    public void setSourceTenantId(Integer sourceTenantId) {
        this.sourceTenantId = sourceTenantId;
    }

    public Integer getIsFirstLevelDirectory() {
        return isFirstLevelDirectory;
    }

    public void setIsFirstLevelDirectory(Integer isFirstLevelDirectory) {
        this.isFirstLevelDirectory = isFirstLevelDirectory;
    }

    public Integer getIsCoursePackage() {
        return isCoursePackage;
    }

    public void setIsCoursePackage(Integer isCoursePackage) {
        this.isCoursePackage = isCoursePackage;
    }

    public Long getLastWatchCourse() {
        return lastWatchCourse;
    }

    public void setLastWatchCourse(Long lastWatchCourse) {
        this.lastWatchCourse = lastWatchCourse;
    }

    public String getStudyProgress() {
        return studyProgress;
    }

    public void setStudyProgress(String studyProgress) {
        this.studyProgress = studyProgress;
    }
}