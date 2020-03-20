package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtMixTrainScheduleItemInfoElearning {
    private Integer id;

    private String scheduleItemName;

    private Date scheduleDate;

    private String scheduleStartTime;

    private String scheduleEndTime;

    private String courseName;

    private Long courseId;

    private Integer isCourseStore;

    private String teacherName;

    private Integer teacherId;

    private Integer isTeacherStore;

    private String remark;

    private Integer scheduleId;

    private Integer operatorId;

    private Integer onOrOffLineFlag;

    private Integer isCourseComplete;

    private String fileName;

    private String filePath;

    private Integer isTrainTheme;

    private String hoster;

    private Integer hasCourseUrl;

    private String location;

    public MtMixTrainScheduleItemInfoElearning(Integer id, String scheduleItemName, Date scheduleDate, String scheduleStartTime, String scheduleEndTime, String courseName, Long courseId, Integer isCourseStore, String teacherName, Integer teacherId, Integer isTeacherStore, String remark, Integer scheduleId, Integer operatorId, Integer onOrOffLineFlag, Integer isCourseComplete, String fileName, String filePath, Integer isTrainTheme, String hoster, Integer hasCourseUrl, String location) {
        this.id = id;
        this.scheduleItemName = scheduleItemName;
        this.scheduleDate = scheduleDate;
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleEndTime = scheduleEndTime;
        this.courseName = courseName;
        this.courseId = courseId;
        this.isCourseStore = isCourseStore;
        this.teacherName = teacherName;
        this.teacherId = teacherId;
        this.isTeacherStore = isTeacherStore;
        this.remark = remark;
        this.scheduleId = scheduleId;
        this.operatorId = operatorId;
        this.onOrOffLineFlag = onOrOffLineFlag;
        this.isCourseComplete = isCourseComplete;
        this.fileName = fileName;
        this.filePath = filePath;
        this.isTrainTheme = isTrainTheme;
        this.hoster = hoster;
        this.hasCourseUrl = hasCourseUrl;
        this.location = location;
    }

    public MtMixTrainScheduleItemInfoElearning() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScheduleItemName() {
        return scheduleItemName;
    }

    public void setScheduleItemName(String scheduleItemName) {
        this.scheduleItemName = scheduleItemName == null ? null : scheduleItemName.trim();
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime == null ? null : scheduleStartTime.trim();
    }

    public String getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime == null ? null : scheduleEndTime.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getIsCourseStore() {
        return isCourseStore;
    }

    public void setIsCourseStore(Integer isCourseStore) {
        this.isCourseStore = isCourseStore;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getIsTeacherStore() {
        return isTeacherStore;
    }

    public void setIsTeacherStore(Integer isTeacherStore) {
        this.isTeacherStore = isTeacherStore;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getOnOrOffLineFlag() {
        return onOrOffLineFlag;
    }

    public void setOnOrOffLineFlag(Integer onOrOffLineFlag) {
        this.onOrOffLineFlag = onOrOffLineFlag;
    }

    public Integer getIsCourseComplete() {
        return isCourseComplete;
    }

    public void setIsCourseComplete(Integer isCourseComplete) {
        this.isCourseComplete = isCourseComplete;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getIsTrainTheme() {
        return isTrainTheme;
    }

    public void setIsTrainTheme(Integer isTrainTheme) {
        this.isTrainTheme = isTrainTheme;
    }

    public String getHoster() {
        return hoster;
    }

    public void setHoster(String hoster) {
        this.hoster = hoster == null ? null : hoster.trim();
    }

    public Integer getHasCourseUrl() {
        return hasCourseUrl;
    }

    public void setHasCourseUrl(Integer hasCourseUrl) {
        this.hasCourseUrl = hasCourseUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
}