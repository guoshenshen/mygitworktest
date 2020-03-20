package com.elearning.pojo.coursemanage;

public class CourseRecommendKey {
    private Long course_id;

    private Integer rec_tenantId;

    public CourseRecommendKey(Long course_id, Integer rec_tenantId) {
        this.course_id = course_id;
        this.rec_tenantId = rec_tenantId;
    }

    public CourseRecommendKey() {
        super();
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Integer getRec_tenantId() {
        return rec_tenantId;
    }

    public void setRec_tenantId(Integer rec_tenantId) {
        this.rec_tenantId = rec_tenantId;
    }
}