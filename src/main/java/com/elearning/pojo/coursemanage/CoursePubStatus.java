package com.elearning.pojo.coursemanage;

public class CoursePubStatus {
    private Long course_id;

    private Integer pubStatus;

    public CoursePubStatus(Long course_id, Integer pubStatus) {
        this.course_id = course_id;
        this.pubStatus = pubStatus;
    }

    public CoursePubStatus() {
        super();
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Integer getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(Integer pubStatus) {
        this.pubStatus = pubStatus;
    }
}