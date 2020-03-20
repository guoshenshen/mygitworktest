package com.elearning.pojo.coursemanage;

import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/16 17:22
 */
public class CmCourse implements java.io.Serializable {

    // Fields
    private static final long serialVersionUID = -4975966952797133210L;
    private CmCourseId id;
    private String courseNo;
    private String courseName;
    //	private Integer trainType;
    private Integer classfication;
    private Integer category;

    private Double fee;
    private Integer studyDay;
    private Double score;
    private Double classHour;
    private String suitableObject;
    private Integer pubstatus;
    private String maker;
    private String creator;
    private String remark;
    private String sliceType;
    private String isOpenCourse;
    private String serverFilePath;
    private Long serverId;
    private Integer courseSize;
    private String enterUrl;
    private String courseType;
    private Integer sharedCourseTypeId;
    private String isSharedCourse;
    private String orgName;
    private String isCreamCourse;
    private String content;
    private String stationId;
    private String pictureUrl;
    private Long totalvaman;
    private Long totalpoint;
    private Date createDate;

    //	制作单位
    private String produceOrgName;
    //	资助单位
    private String fundingOrgName;

    // Constructors

    /** default constructor */
    public CmCourse() {
    }

    /** minimal constructor */
    public CmCourse(CmCourseId id) {
        this.id = id;
    }

    /** full constructor */
    public CmCourse(CmCourseId id, String courseNo, String courseName,Integer category,
                    Integer classfication, Double fee, Integer studyDay, Double score,
                    Double classHour, String suitableObject, Integer pubstatus,
                    String maker, String creator, String remark, String sliceType,
                    String isOpenCourse, String serverFilePath, Long serverId,
                    Integer courseSize, String enterUrl, String courseType,
                    Integer sharedCourseTypeId, String isSharedCourse, String orgName,
                    String isCreamCourse, String content, String stationId,
                    String pictureUrl, Long totalvaman, Long totalpoint, Date createDate,String produceOrgName,String fundingOrgName) {
        this.id = id;
        this.courseNo = courseNo;
        this.courseName = courseName;
        this.classfication = classfication;
        this.category = category;
        this.fee = fee;
        this.studyDay = studyDay;
        this.score = score;
        this.classHour = classHour;
        this.suitableObject = suitableObject;
        this.pubstatus = pubstatus;
        this.maker = maker;
        this.creator = creator;
        this.remark = remark;
        this.sliceType = sliceType;
        this.isOpenCourse = isOpenCourse;
        this.serverFilePath = serverFilePath;
        this.serverId = serverId;
        this.courseSize = courseSize;
        this.enterUrl = enterUrl;
        this.courseType = courseType;
        this.sharedCourseTypeId = sharedCourseTypeId;
        this.isSharedCourse = isSharedCourse;
        this.orgName = orgName;
        this.isCreamCourse = isCreamCourse;
        this.content = content;
        this.stationId = stationId;
        this.pictureUrl = pictureUrl;
        this.totalvaman = totalvaman;
        this.totalpoint = totalpoint;
        this.createDate = createDate;
        this.produceOrgName = produceOrgName;
        this.fundingOrgName = fundingOrgName;
    }

    // Property accessors


    public CmCourseId getId() {
        return this.id;
    }

    public void setId(CmCourseId id) {
        this.id = id;
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


    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
    public String getCourseNo() {
        return this.courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

//	public Integer getTrainType() {
//		return this.trainType;
//	}
//
//	public void setTrainType(Integer trainType) {
//		this.trainType = trainType;
//	}



    public Double getFee() {
        return this.fee;
    }

    public Integer getClassfication() {
        return classfication;
    }

    public void setClassfication(Integer classfication) {
        this.classfication = classfication;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getStudyDay() {
        return this.studyDay;
    }

    public void setStudyDay(Integer studyDay) {
        this.studyDay = studyDay;
    }

    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getClassHour() {
        return this.classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
    }

    public String getSuitableObject() {
        return this.suitableObject;
    }

    public void setSuitableObject(String suitableObject) {
        this.suitableObject = suitableObject;
    }

    public Integer getPubstatus() {
        return this.pubstatus;
    }

    public void setPubstatus(Integer pubstatus) {
        this.pubstatus = pubstatus;
    }

    public String getMaker() {
        return this.maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSliceType() {
        return this.sliceType;
    }

    public void setSliceType(String sliceType) {
        this.sliceType = sliceType;
    }

    public String getIsOpenCourse() {
        return this.isOpenCourse;
    }

    public void setIsOpenCourse(String isOpenCourse) {
        this.isOpenCourse = isOpenCourse;
    }

    public String getServerFilePath() {
        return this.serverFilePath;
    }

    public void setServerFilePath(String serverFilePath) {
        this.serverFilePath = serverFilePath;
    }

    public Long getServerId() {
        return this.serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public Integer getCourseSize() {
        return this.courseSize;
    }

    public void setCourseSize(Integer courseSize) {
        this.courseSize = courseSize;
    }

    public String getEnterUrl() {
        return this.enterUrl;
    }

    public void setEnterUrl(String enterUrl) {
        this.enterUrl = enterUrl;
    }

    public String getCourseType() {
        return this.courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getSharedCourseTypeId() {
        return this.sharedCourseTypeId;
    }

    public void setSharedCourseTypeId(Integer sharedCourseTypeId) {
        this.sharedCourseTypeId = sharedCourseTypeId;
    }

    public String getIsSharedCourse() {
        return this.isSharedCourse;
    }

    public void setIsSharedCourse(String isSharedCourse) {
        this.isSharedCourse = isSharedCourse;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getIsCreamCourse() {
        return this.isCreamCourse;
    }

    public void setIsCreamCourse(String isCreamCourse) {
        this.isCreamCourse = isCreamCourse;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStationId() {
        return this.stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Long getTotalvaman() {
        return this.totalvaman;
    }

    public void setTotalvaman(Long totalvaman) {
        this.totalvaman = totalvaman;
    }

    public Long getTotalpoint() {
        return this.totalpoint;
    }

    public void setTotalpoint(Long totalpoint) {
        this.totalpoint = totalpoint;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
