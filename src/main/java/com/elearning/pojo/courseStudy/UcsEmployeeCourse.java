package com.elearning.pojo.courseStudy;

import java.util.Date;

public class UcsEmployeeCourse {
    private Integer ID;

    private Integer operatorID;

    private Long courseId;

    private Date firstTime;

    private String lateTime;

    private Integer ISPASS;

    private Integer EXAMMARK;

    private Float studyProgress;

    private Integer year;

    private Integer tenantId;

    private Integer lastLearnTime;

    private Integer totalCourseTime;

    private Integer type;

    private Integer isCalculated;

    private Integer studyTime;

    private Long LastWatchCourse;

    public UcsEmployeeCourse(Integer ID, Integer operatorID, Long courseId, Date firstTime, String lateTime, Integer ISPASS, Integer EXAMMARK, Float studyProgress, Integer year, Integer tenantId, Integer lastLearnTime, Integer totalCourseTime, Integer type, Integer isCalculated, Integer studyTime) {
        this.ID = ID;
        this.operatorID = operatorID;
        this.courseId = courseId;
        this.firstTime = firstTime;
        this.lateTime = lateTime;
        this.ISPASS = ISPASS;
        this.EXAMMARK = EXAMMARK;
        this.studyProgress = studyProgress;
        this.year = year;
        this.tenantId = tenantId;
        this.lastLearnTime = lastLearnTime;
        this.totalCourseTime = totalCourseTime;
        this.type = type;
        this.isCalculated = isCalculated;
        this.studyTime = studyTime;
    }

    public UcsEmployeeCourse() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public String getLateTime() {
        return lateTime;
    }

    public void setLateTime(String lateTime) {
        this.lateTime = lateTime == null ? null : lateTime.trim();
    }

    public Integer getISPASS() {
        return ISPASS;
    }

    public void setISPASS(Integer ISPASS) {
        this.ISPASS = ISPASS;
    }

    public Integer getEXAMMARK() {
        return EXAMMARK;
    }

    public void setEXAMMARK(Integer EXAMMARK) {
        this.EXAMMARK = EXAMMARK;
    }

    public Float getStudyProgress() {
        return studyProgress;
    }

    public void setStudyProgress(Float studyProgress) {
        this.studyProgress = studyProgress;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getLastLearnTime() {
        return lastLearnTime;
    }

    public void setLastLearnTime(Integer lastLearnTime) {
        this.lastLearnTime = lastLearnTime;
    }

    public Integer getTotalCourseTime() {
        return totalCourseTime;
    }

    public void setTotalCourseTime(Integer totalCourseTime) {
        this.totalCourseTime = totalCourseTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsCalculated() {
        return isCalculated;
    }

    public void setIsCalculated(Integer isCalculated) {
        this.isCalculated = isCalculated;
    }

    public Integer getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Integer studyTime) {
        this.studyTime = studyTime;
    }

    public Long getLastWatchCourse() {
        return LastWatchCourse;
    }

    public void setLastWatchCourse(Long lastWatchCourse) {
        LastWatchCourse = lastWatchCourse;
    }
}