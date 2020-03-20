package com.elearning.vo;

import java.util.Date;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/15 15:26
 */
public class CourseVo {
    private Long courseId;

    private String courseNo;

    private Integer orgId;

    private String orgName;

    private String courseName;

    //科目分类：公需科目、专业科目
    private Integer category;

    private String categoryStr;

    //微课程分类
    private Integer classfication;

    private String classificationStr;

    private Double score;

    private Double classHour;

    private String maker;

    private String creator;

    private Date createDate;

    private String createDateStr;

    private String pictureUrl;

    //制作单位
    private String produceOrgName;

    //资助单位
    private String fundingOrgName;

    private Integer tenantId;

    private String keyWords;

    private String tenantName;

    private Long itemId;

    private Long orderWeight;

    private String expertAreaId;

    private String expertAreaStr;

    private Integer isCoursePackage;    //是否为课程包,是/1否/0

    private Integer isFirstLevelDirectory; //是否为一级课程,是/1否/0

    private String sliceType;

    private Integer selectedTimes;

    private String content;

    private String enterUrl;

    private Date shareDate;

    private Float studyProgress;

    private Map<Integer, Integer> scoreMap ;

    private int	studyDay;

    private String courseTypeName;

    private String checked;

    private String courseStations;

    private int courseTypeId;

    private int studyNumber;

    private String pubStatus;

    private Integer pubSts;

    private String suitableObject;

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

    //	private int trainType;

    private String classficationName;

    private String categoryName;

    private double fee;

    private String postId;

    //	private String keyWordsTag;
    private String subExpertAreaId;

    private Integer isUnderScope;

    private String expertAreas;

    private int typeId;

    private String suitpost;

    private Integer totalLearnedUser;

    private Integer discussUser;

    private int kindId;

    private String videoConvertFlag;   //视频转化状态

    private Long sourceCourseId;

    private int shareCourseObtainStatus;

    private int openScope;

    private String openScopeStr;

    private int usedByOrgFlag;     //共享课程是否被本机构使用过   0-未使用;1-使用过

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

    //  手机端播放地址
    private String mobilePlayAddress;

    private Long  secondCourseId;

    private String shareDateStr;

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
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getCategoryStr() {
        return categoryStr;
    }

    public void setCategoryStr(String categoryStr) {
        this.categoryStr = categoryStr;
    }

    public Integer getClassfication() {
        return classfication;
    }

    public void setClassfication(Integer classfication) {
        this.classfication = classfication;
    }

    public String getClassificationStr() {
        return classificationStr;
    }

    public void setClassificationStr(String classificationStr) {
        this.classificationStr = classificationStr;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(Long orderWeight) {
        this.orderWeight = orderWeight;
    }

    public String getExpertAreaId() {
        return expertAreaId;
    }

    public void setExpertAreaId(String expertAreaId) {
        this.expertAreaId = expertAreaId;
    }

    public String getExpertAreaStr() {
        return expertAreaStr;
    }

    public void setExpertAreaStr(String expertAreaStr) {
        this.expertAreaStr = expertAreaStr;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getSliceType() {
        return sliceType;
    }

    public void setSliceType(String sliceType) {
        this.sliceType = sliceType;
    }

    public Integer getSelectedTimes() {
        return selectedTimes;
    }

    public void setSelectedTimes(Integer selectedTimes) {
        this.selectedTimes = selectedTimes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEnterUrl() {
        return enterUrl;
    }

    public void setEnterUrl(String enterUrl) {
        this.enterUrl = enterUrl;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }


    public Float getStudyProgress() {
        return studyProgress;
    }

    public void setStudyProgress(Float studyProgress) {
        this.studyProgress = studyProgress;
    }

    public Map<Integer, Integer> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Map<Integer, Integer> scoreMap) {
        this.scoreMap = scoreMap;
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

    public String getSuitableObject() {
        return suitableObject;
    }

    public void setSuitableObject(String suitableObject) {
        this.suitableObject = suitableObject;
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

    public String getClassficationName() {
        return classficationName;
    }

    public void setClassficationName(String classficationName) {
        this.classficationName = classficationName;
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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public String getMobilePlayAddress() {
        return mobilePlayAddress;
    }

    public void setMobilePlayAddress(String mobilePlayAddress) {
        this.mobilePlayAddress = mobilePlayAddress;
    }

    public Long getSecondCourseId() {
        return secondCourseId;
    }

    public void setSecondCourseId(Long secondCourseId) {
        this.secondCourseId = secondCourseId;
    }

    public String getShareDateStr() {
        return shareDateStr;
    }

    public void setShareDateStr(String shareDateStr) {
        this.shareDateStr = shareDateStr;
    }
}
