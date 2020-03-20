package com.elearning.pojo.coursemanage;

public class SheetCourseKeywords {
    private Long courseId;

    private Integer tenantId;

    private String keywords1;

    private String tenantName;

    private String keywords2;

    public SheetCourseKeywords(Long courseId, Integer tenantId, String keywords1, String tenantName, String keywords2) {
        this.courseId = courseId;
        this.tenantId = tenantId;
        this.keywords1 = keywords1;
        this.tenantName = tenantName;
        this.keywords2 = keywords2;
    }

    public SheetCourseKeywords() {
        super();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getKeywords1() {
        return keywords1;
    }

    public void setKeywords1(String keywords1) {
        this.keywords1 = keywords1 == null ? null : keywords1.trim();
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName == null ? null : tenantName.trim();
    }

    public String getKeywords2() {
        return keywords2;
    }

    public void setKeywords2(String keywords2) {
        this.keywords2 = keywords2 == null ? null : keywords2.trim();
    }
}