package com.elearning.pojo.courseStudy;

import java.util.Date;

public class UscUsertliddayStudyInfo {
    private Integer UTDID;

    private Integer userID;

    private Long courseID;

    private Date STUDYDAY;

    private Integer STUDYAMOUNT;

    private Integer STUDYTIME;

    private Float STUDYPROGRESS;

    private Integer trainId;

    private Integer sectionId;

    private String year;

    private Integer tenantId;

    private Integer type;

    public UscUsertliddayStudyInfo(Integer UTDID, Integer userID, Long courseID, Date STUDYDAY, Integer STUDYAMOUNT, Integer STUDYTIME, Float STUDYPROGRESS, Integer trainId, Integer sectionId, String year, Integer tenantId, Integer type) {
        this.UTDID = UTDID;
        this.userID = userID;
        this.courseID = courseID;
        this.STUDYDAY = STUDYDAY;
        this.STUDYAMOUNT = STUDYAMOUNT;
        this.STUDYTIME = STUDYTIME;
        this.STUDYPROGRESS = STUDYPROGRESS;
        this.trainId = trainId;
        this.sectionId = sectionId;
        this.year = year;
        this.tenantId = tenantId;
        this.type = type;
    }

    public UscUsertliddayStudyInfo() {
        super();
    }

    public Integer getUTDID() {
        return UTDID;
    }

    public void setUTDID(Integer UTDID) {
        this.UTDID = UTDID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public Date getSTUDYDAY() {
        return STUDYDAY;
    }

    public void setSTUDYDAY(Date STUDYDAY) {
        this.STUDYDAY = STUDYDAY;
    }

    public Integer getSTUDYAMOUNT() {
        return STUDYAMOUNT;
    }

    public void setSTUDYAMOUNT(Integer STUDYAMOUNT) {
        this.STUDYAMOUNT = STUDYAMOUNT;
    }

    public Integer getSTUDYTIME() {
        return STUDYTIME;
    }

    public void setSTUDYTIME(Integer STUDYTIME) {
        this.STUDYTIME = STUDYTIME;
    }

    public Float getSTUDYPROGRESS() {
        return STUDYPROGRESS;
    }

    public void setSTUDYPROGRESS(Float STUDYPROGRESS) {
        this.STUDYPROGRESS = STUDYPROGRESS;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}