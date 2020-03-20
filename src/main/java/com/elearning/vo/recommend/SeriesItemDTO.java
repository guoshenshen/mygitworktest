package com.elearning.vo.recommend;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/12/4 15:22
 */
public class SeriesItemDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8308289785266992337L;
    //主键id
    private String id;
    //专题id
    private Integer seriesId;
    //关联资源id
    private String itemId;
    //关联资源类型
    private Integer itemType;
    //权重
    private String orderWeight;
    /*******************course*************************/
    //课程id
    private String courseId;
    //课程编号
    private String courseNo;
    //课程名字
    private String courseName;
    //科目分类：公需科目、专业科目
    private Integer category;
    //课程时长
    private Double classHour;
    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createDate;
    //微课程分类
    private Integer classfication;
    private Double score;
    private String maker;
    private String creator;
    //	制作单位
    private String produceOrgName;
    //	资助单位
    private String fundingOrgName;
    private Integer tenantId;
    private String pictureUrl;
    private String keyWords;

    private String categoryStr;
    private String classificationStr;
    private String createDateStr;


    public String getCategoryStr() {
        return categoryStr;
    }

    public void setCategoryStr(String categoryStr) {
        this.categoryStr = categoryStr;
    }

    public String getClassificationStr() {
        return classificationStr;
    }

    public void setClassificationStr(String classificationStr) {
        this.classificationStr = classificationStr;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

	/*private Double fee;
	private Integer studyDay;
	private String suitableObject;
	private Integer pubStatus;
	private String remark;
	private String sliceType;
	private String isOpenCourse;
	private String enterUrl;
	private String content;
	private String stationId;
	private String isSharedCourse;
	private Long uploadOrgId;
	private String uploadOrgName;
	private Long totalvaman;
	private Long totalpoint;
	private Long orgId;
	private String approveStatus;
	private String serverFilePath;
	private Long serverId;
	private String postId;
	private String expertAreaId;
	private Integer isUnderScope;
	private Long sourceCourseId;
	private Integer openScope;
	private String subExpertAreaId;
	private Date shareDate;
	private String indexUrl;
	private String customFilePath;
	private String breed;
	private Integer upTenantId;
	private Double qualificationMark;
	private Integer requiredCourseTypeId;
	private Integer isFormal;
	private Long operatorId;
	private Integer isNoted;
	private Integer isApproved;
	private Integer hasTeacher;
	private Integer teacherId;

	private Integer selectedTimes;



//  手机端播放地址
	private String mobilePlayAddress;

//	兼容之前的课件，代表课件是否被转化为多种形式；0:否；1:是
	private Integer isMultiSize;


//	Date:2018-03-06
//	课件上传平台，当课程发布之后，tenantId修改为正确的研究所，sourceTenantId不变
	private Integer sourceTenantId;


	private String openOrgSEQ;

	private String studyProgress;

	//是否为课程包
	private Integer isCoursePackage;
	//是否为一级课程
	private Integer  isFirstLevelDirectory;
	private Long LastWatchCourse;*/
    /***********************************************/


    /***********************train************************/
    private String trainid;
    private BigInteger trainId;
    private String trainName;
    private Integer trainTypeId;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;
    private String sponsorName;
    private String orgName;
    private String organizerName;
    private String telephone;
    private String organizerEmail;
    private Double trainClassHour;
    private Integer attendantCount;
    private Integer ifBJ;
    private String location;
    private Short isEnrolled;
    private Date programStartTime;
    private Date programEndTime;
    private Integer trainItemType;
    private Integer trainTenantId;
    private Integer implStatusId;
    private Integer reported;
    private Integer approveStatus;//培训项目的审批状态
    private Long trainPlanID;

    private String tenantName;
    private String trainTypeName;
    private String startTimeStr;
    private String endTimeStr;
    private String programStartTimeStr;
    private String programEndTimeStr;


    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getProgramStartTimeStr() {
        return programStartTimeStr;
    }

    public void setProgramStartTimeStr(String programStartTimeStr) {
        this.programStartTimeStr = programStartTimeStr;
    }

    public String getProgramEndTimeStr() {
        return programEndTimeStr;
    }

    public void setProgramEndTimeStr(String programEndTimeStr) {
        this.programEndTimeStr = programEndTimeStr;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }

    public void setTrainTypeName(String trainTypeName) {
        this.trainTypeName = trainTypeName;
    }

	/*private Integer trainWay;
	private Integer programNo;
	private String sponsorId;
	private String organizerId;
	private Long orgId;
	private String trainingContent;
	private String attendants;
	private String trainGoal;
	private Short isIssued;
	private Short isNeedCheck;
	private Integer isStationTrain;
	private String year;
	private Integer subTrainTypeID;
	private Integer isPlaned;
	private Integer invlCount;
	private Long operatorId;
	private String keyWordsTag;
	private Integer openScope;
	private String trainPlanName;
	private Integer iconId;
	private Integer topbandId;
	private Integer creamProject;
	private Integer trainMode;
	private Integer upTenantId;
	private Integer attendedCount;
	private Integer monitorId;
	private String monitorName;
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
	private Double days;
	private String stationId;
	private String implStatusName;
	private Integer recommendFlag;
	  //用来标记人事处反馈回来结果是：精品项目、普通资助项目、普通项目
	private Integer pubstatus;
	private Set<MsgMessageInfo> messages;
	private Integer cad_report;
	//	培训班是否允许下级查看（网上专题班：十八届六中全会）
	private Integer allowSubordinateViewStatistics;

	*//**
     * 培训计划培训人员层次：0：处级及以下;1：司局级;2：省部级
     *//*
	private Integer feeLevel;

	private String openOrgSEQ;*/

    public String getTrainid() {
        return trainid;
    }

    public void setTrainid(String trainid) {
        this.trainid = trainid;
    }

    public BigInteger getTrainId() {
        return trainId;
    }

    public void setTrainId(BigInteger trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Integer getTrainTypeId() {
        return trainTypeId;
    }

    public void setTrainTypeId(Integer trainTypeId) {
        this.trainTypeId = trainTypeId;
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
        this.sponsorName = sponsorName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
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

    public Double getTrainClassHour() {
        return trainClassHour;
    }

    public void setTrainClassHour(Double trainClassHour) {
        this.trainClassHour = trainClassHour;
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
        this.location = location;
    }

    public Short getIsEnrolled() {
        return isEnrolled;
    }

    public void setIsEnrolled(Short isEnrolled) {
        this.isEnrolled = isEnrolled;
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

    public Integer getTrainItemType() {
        return trainItemType;
    }

    public void setTrainItemType(Integer trainItemType) {
        this.trainItemType = trainItemType;
    }

    public Integer getTrainTenantId() {
        return trainTenantId;
    }

    public void setTrainTenantId(Integer trainTenantId) {
        this.trainTenantId = trainTenantId;
    }

    public Integer getImplStatusId() {
        return implStatusId;
    }

    public void setImplStatusId(Integer implStatusId) {
        this.implStatusId = implStatusId;
    }

    public Integer getReported() {
        return reported;
    }

    public void setReported(Integer reported) {
        this.reported = reported;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Long getTrainPlanID() {
        return trainPlanID;
    }

    public void setTrainPlanID(Long trainPlanID) {
        this.trainPlanID = trainPlanID;
    }

    /***********************************************/
    /**********************series*******************/
    private Integer seriesMainId;
    private String title;
    private String sponsorInfo;
    private String detail;
    private String url;
    private String picUrl;
    private String mainPicUrl;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;



    public Integer getSeriesMainId() {
        return seriesMainId;
    }

    public void setSeriesMainId(Integer seriesMainId) {
        this.seriesMainId = seriesMainId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSponsorInfo() {
        return sponsorInfo;
    }

    public void setSponsorInfo(String sponsorInfo) {
        this.sponsorInfo = sponsorInfo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMainPicUrl() {
        return mainPicUrl;
    }

    public void setMainPicUrl(String mainPicUrl) {
        this.mainPicUrl = mainPicUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId(){
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id.toString();
    }

    /***********************************************/



    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        if(courseId != null){
            this.courseId = courseId.toString();
        }

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

    public Integer getClassfication() {
        return classfication;
    }

    public void setClassfication(Integer classfication) {
        this.classfication = classfication;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
    public String getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(BigInteger orderWeight) {
        if(orderWeight != null){
            this.orderWeight = orderWeight.toString();
        }

    }

    /*public String getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id.toString();
    }
*/
    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId.toString();
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }



}
