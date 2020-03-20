package com.elearning.vo.mixtraining;


public class MtMixTrainScheduleItemForm {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String scheduleItemName;
    private String scheduleDate;
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
    private Integer isTrainTheme;
    private String hoster;
    private String location;

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
        this.scheduleItemName = scheduleItemName;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public String getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
        this.teacherName = teacherName;
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
        this.remark = remark;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
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
        this.hoster = hoster;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
