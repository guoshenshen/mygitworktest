package com.elearning.vo;

import java.io.Serializable;


public class CourseFormAll implements Serializable {

    private Long course_id;
    private String courseName;
    private String courseNo;
    private int studyDay;
    private String courseTypeName;
    private String checked;
    private double classHour;
    private String courseStations;
    private int courseTypeId;
    private int studyNumber;
    private Long orgId;
    private String orgName;
    private String pubStatus;
    private Integer pubSts;
    private String sliceType;
    private String createDate;
    private String maker;
    private String creator;
    private String suitableObject;
    private String enterUrl;
    private String serverFilePath;
    private long serverId;
    private String isOpenCourse;
    private String mainContent;
    private String remark;
    private int watchable;  //是否可预览
    private int updatable;  //是否可修改基本信息和发布状态
    private String isSharedCourse;  //是否企业间共享
    private long uploadOrgId;       //上传企业id
    private int approveOpenCourseRight;  //是否具有审核公开课的权限
    private String approveStatus;
    private String hasStatus;
    private int classfication;
    private String classficationName;
    private int category;
    private String categoryName;
    private double fee;
    private String pictureUrl;
    private String postId;
    private String keyWords;
    private String expertAreaId;
    private String subExpertAreaId;
    private Integer isUnderScope;
    private String expertAreas;
    private int typeId;
    private String suitpost;
    private Integer totalLearnedUser;
    private Integer discussUser;
    private String score;
    private int kindId;
    private String videoConvertFlag;   //视频转化状态
    private Integer tenantId;
    private Long sourceCourseId;
    private int shareCourseObtainStatus;
    private int openScope;
    private String openScopeStr;
    private int usedByOrgFlag;     //共享课程是否被本机构使用过   0-未使用;1-使用过
    private String shareDate;
    private String shareCourseToOrgAllOperatorNames;
    private String shareCourseToOrgAllPosts;
    private int shareCourseToOrgLearnerNum;
    private String shareCourseToAllTenantNames;
    private int shareCourseToTenantNum;
    private int shareCourseCollectStatus;
    private String breed;
    private Integer upTenantId;
    private Integer requiredCourseTypeId;
    private Integer isFormal;
    private Long operatorId;
    private Integer isNoted;
    private Integer isApproved;
    //	制作单位
    private String produceOrgName;
    //	资助单位
    private String fundingOrgName;
    //  手机端播放地址
    private String mobilePlayAddress;
    //是否为课程包,是/1否/0
    private Integer isCoursePackage;
    //是否为一级课程,是/1否/0
    private Integer isFirstLevelDirectory;
    private Long secondCourseId;

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public int getStudyDay() {
        return studyDay;
    }

    public void setStudyDay(int studyDay) {
        this.studyDay = studyDay;
    }

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public double getClassHour() {
        return classHour;
    }

    public void setClassHour(double classHour) {
        this.classHour = classHour;
    }

    public String getCourseStations() {
        return courseStations;
    }

    public void setCourseStations(String courseStations) {
        this.courseStations = courseStations;
    }

    public int getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(int courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public int getStudyNumber() {
        return studyNumber;
    }

    public void setStudyNumber(int studyNumber) {
        this.studyNumber = studyNumber;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(String pubStatus) {
        this.pubStatus = pubStatus;
    }

    public Integer getPubSts() {
        return pubSts;
    }

    public void setPubSts(Integer pubSts) {
        this.pubSts = pubSts;
    }

    public String getSliceType() {
        return sliceType;
    }

    public void setSliceType(String sliceType) {
        this.sliceType = sliceType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSuitableObject() {
        return suitableObject;
    }

    public void setSuitableObject(String suitableObject) {
        this.suitableObject = suitableObject;
    }

    public String getEnterUrl() {
        return enterUrl;
    }

    public void setEnterUrl(String enterUrl) {
        this.enterUrl = enterUrl;
    }

    public String getServerFilePath() {
        return serverFilePath;
    }

    public void setServerFilePath(String serverFilePath) {
        this.serverFilePath = serverFilePath;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public String getIsOpenCourse() {
        return isOpenCourse;
    }

    public void setIsOpenCourse(String isOpenCourse) {
        this.isOpenCourse = isOpenCourse;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getWatchable() {
        return watchable;
    }

    public void setWatchable(int watchable) {
        this.watchable = watchable;
    }

    public int getUpdatable() {
        return updatable;
    }

    public void setUpdatable(int updatable) {
        this.updatable = updatable;
    }

    public String getIsSharedCourse() {
        return isSharedCourse;
    }

    public void setIsSharedCourse(String isSharedCourse) {
        this.isSharedCourse = isSharedCourse;
    }

    public long getUploadOrgId() {
        return uploadOrgId;
    }

    public void setUploadOrgId(long uploadOrgId) {
        this.uploadOrgId = uploadOrgId;
    }

    public int getApproveOpenCourseRight() {
        return approveOpenCourseRight;
    }

    public void setApproveOpenCourseRight(int approveOpenCourseRight) {
        this.approveOpenCourseRight = approveOpenCourseRight;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getHasStatus() {
        return hasStatus;
    }

    public void setHasStatus(String hasStatus) {
        this.hasStatus = hasStatus;
    }

    public int getClassfication() {
        return classfication;
    }

    public void setClassfication(int classfication) {
        this.classfication = classfication;
    }

    public String getClassficationName() {
        return classficationName;
    }

    public void setClassficationName(String classficationName) {
        this.classficationName = classficationName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getExpertAreaId() {
        return expertAreaId;
    }

    public void setExpertAreaId(String expertAreaId) {
        this.expertAreaId = expertAreaId;
    }

    public String getSubExpertAreaId() {
        return subExpertAreaId;
    }

    public void setSubExpertAreaId(String subExpertAreaId) {
        this.subExpertAreaId = subExpertAreaId;
    }

    public Integer getIsUnderScope() {
        return isUnderScope;
    }

    public void setIsUnderScope(Integer isUnderScope) {
        this.isUnderScope = isUnderScope;
    }

    public String getExpertAreas() {
        return expertAreas;
    }

    public void setExpertAreas(String expertAreas) {
        this.expertAreas = expertAreas;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getSuitpost() {
        return suitpost;
    }

    public void setSuitpost(String suitpost) {
        this.suitpost = suitpost;
    }

    public Integer getTotalLearnedUser() {
        return totalLearnedUser;
    }

    public void setTotalLearnedUser(Integer totalLearnedUser) {
        this.totalLearnedUser = totalLearnedUser;
    }

    public Integer getDiscussUser() {
        return discussUser;
    }

    public void setDiscussUser(Integer discussUser) {
        this.discussUser = discussUser;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public String getVideoConvertFlag() {
        return videoConvertFlag;
    }

    public void setVideoConvertFlag(String videoConvertFlag) {
        this.videoConvertFlag = videoConvertFlag;
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

    public int getShareCourseObtainStatus() {
        return shareCourseObtainStatus;
    }

    public void setShareCourseObtainStatus(int shareCourseObtainStatus) {
        this.shareCourseObtainStatus = shareCourseObtainStatus;
    }

    public int getOpenScope() {
        return openScope;
    }

    public void setOpenScope(int openScope) {
        this.openScope = openScope;
    }

    public String getOpenScopeStr() {
        return openScopeStr;
    }

    public void setOpenScopeStr(String openScopeStr) {
        this.openScopeStr = openScopeStr;
    }

    public int getUsedByOrgFlag() {
        return usedByOrgFlag;
    }

    public void setUsedByOrgFlag(int usedByOrgFlag) {
        this.usedByOrgFlag = usedByOrgFlag;
    }

    public String getShareDate() {
        return shareDate;
    }

    public void setShareDate(String shareDate) {
        this.shareDate = shareDate;
    }

    public String getShareCourseToOrgAllOperatorNames() {
        return shareCourseToOrgAllOperatorNames;
    }

    public void setShareCourseToOrgAllOperatorNames(String shareCourseToOrgAllOperatorNames) {
        this.shareCourseToOrgAllOperatorNames = shareCourseToOrgAllOperatorNames;
    }

    public String getShareCourseToOrgAllPosts() {
        return shareCourseToOrgAllPosts;
    }

    public void setShareCourseToOrgAllPosts(String shareCourseToOrgAllPosts) {
        this.shareCourseToOrgAllPosts = shareCourseToOrgAllPosts;
    }

    public int getShareCourseToOrgLearnerNum() {
        return shareCourseToOrgLearnerNum;
    }

    public void setShareCourseToOrgLearnerNum(int shareCourseToOrgLearnerNum) {
        this.shareCourseToOrgLearnerNum = shareCourseToOrgLearnerNum;
    }

    public String getShareCourseToAllTenantNames() {
        return shareCourseToAllTenantNames;
    }

    public void setShareCourseToAllTenantNames(String shareCourseToAllTenantNames) {
        this.shareCourseToAllTenantNames = shareCourseToAllTenantNames;
    }

    public int getShareCourseToTenantNum() {
        return shareCourseToTenantNum;
    }

    public void setShareCourseToTenantNum(int shareCourseToTenantNum) {
        this.shareCourseToTenantNum = shareCourseToTenantNum;
    }

    public int getShareCourseCollectStatus() {
        return shareCourseCollectStatus;
    }

    public void setShareCourseCollectStatus(int shareCourseCollectStatus) {
        this.shareCourseCollectStatus = shareCourseCollectStatus;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getUpTenantId() {
        return upTenantId;
    }

    public void setUpTenantId(Integer upTenantId) {
        this.upTenantId = upTenantId;
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

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
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

    public String getProduceOrgName() {
        return produceOrgName;
    }

    public void setProduceOrgName(String produceOrgName) {
        this.produceOrgName = produceOrgName;
    }

    public String getFundingOrgName() {
        return fundingOrgName;
    }

    public void setFundingOrgName(String fundingOrgName) {
        this.fundingOrgName = fundingOrgName;
    }

    public String getMobilePlayAddress() {
        return mobilePlayAddress;
    }

    public void setMobilePlayAddress(String mobilePlayAddress) {
        this.mobilePlayAddress = mobilePlayAddress;
    }

    public Integer getIsCoursePackage() {
        return isCoursePackage;
    }

    public void setIsCoursePackage(Integer isCoursePackage) {
        this.isCoursePackage = isCoursePackage;
    }

    public Integer getIsFirstLevelDirectory() {
        return isFirstLevelDirectory;
    }

    public void setIsFirstLevelDirectory(Integer isFirstLevelDirectory) {
        this.isFirstLevelDirectory = isFirstLevelDirectory;
    }

    public Long getSecondCourseId() {
        return secondCourseId;
    }

    public void setSecondCourseId(Long secondCourseId) {
        this.secondCourseId = secondCourseId;
    }
}
