package com.elearning.pojo.coursemanage;

public class CourseRecommend extends CourseRecommendKey {
    private Integer tenantId;

    private String course_name;

    private Integer hottest;

    private Integer newest;

    private Integer highquality;

    public CourseRecommend(Long course_id, Integer rec_tenantId, Integer tenantId, String course_name, Integer hottest, Integer newest, Integer highquality) {
        super(course_id, rec_tenantId);
        this.tenantId = tenantId;
        this.course_name = course_name;
        this.hottest = hottest;
        this.newest = newest;
        this.highquality = highquality;
    }

    public CourseRecommend() {
        super();
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name == null ? null : course_name.trim();
    }

    public Integer getHottest() {
        return hottest;
    }

    public void setHottest(Integer hottest) {
        this.hottest = hottest;
    }

    public Integer getNewest() {
        return newest;
    }

    public void setNewest(Integer newest) {
        this.newest = newest;
    }

    public Integer getHighquality() {
        return highquality;
    }

    public void setHighquality(Integer highquality) {
        this.highquality = highquality;
    }
}