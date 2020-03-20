package com.elearning.pojo.coursemanage;

public class UscLectureFile {
    private Integer lectureId;

    private String lectureName;

    private String lectureURL;

    private Long courseId;

    private Integer openScope;

    public UscLectureFile(Integer lectureId, String lectureName, String lectureURL, Long courseId, Integer openScope) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
        this.lectureURL = lectureURL;
        this.courseId = courseId;
        this.openScope = openScope;
    }

    public UscLectureFile() {
        super();
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName == null ? null : lectureName.trim();
    }

    public String getLectureURL() {
        return lectureURL;
    }

    public void setLectureURL(String lectureURL) {
        this.lectureURL = lectureURL == null ? null : lectureURL.trim();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getOpenScope() {
        return openScope;
    }

    public void setOpenScope(Integer openScope) {
        this.openScope = openScope;
    }
}