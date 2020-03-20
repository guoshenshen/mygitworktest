package com.elearning.pojo.courseStudy;

public class UserNeedLearnCourseKey {
    private Integer operatorId;

    private Integer trainId;

    private String year;

    private Integer sectionId;

    private Long courseId;

    public UserNeedLearnCourseKey(Integer operatorId, Integer trainId, String year, Integer sectionId, Long courseId) {
        this.operatorId = operatorId;
        this.trainId = trainId;
        this.year = year;
        this.sectionId = sectionId;
        this.courseId = courseId;
    }

    public UserNeedLearnCourseKey() {
        super();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}