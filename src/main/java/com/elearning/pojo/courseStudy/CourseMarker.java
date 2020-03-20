package com.elearning.pojo.courseStudy;

import java.util.Date;

public class CourseMarker {
    private Long id;

    private Long courseId;

    private Integer time;

    private String text;

    private Date modifyTime;

    public CourseMarker(Long id, Long courseId, Integer time, String text, Date modifyTime) {
        this.id = id;
        this.courseId = courseId;
        this.time = time;
        this.text = text;
        this.modifyTime = modifyTime;
    }

    public CourseMarker() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}