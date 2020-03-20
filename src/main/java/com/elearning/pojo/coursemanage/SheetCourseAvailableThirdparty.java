package com.elearning.pojo.coursemanage;

public class SheetCourseAvailableThirdparty {
    private String course_id;

    private String courseName;

    private String sourcePubStatus;

    private String sourceOpenScope;

    private String creator;

    private String teacherName;

    private String orgId;

    private String tenantId;

    private String tenantName;

    private String keywords;

    private String isAvailable;

    private String isFromThirdParty;

    public SheetCourseAvailableThirdparty(String course_id, String courseName, String sourcePubStatus, String sourceOpenScope, String creator, String teacherName, String orgId, String tenantId, String tenantName, String keywords, String isAvailable, String isFromThirdParty) {
        this.course_id = course_id;
        this.courseName = courseName;
        this.sourcePubStatus = sourcePubStatus;
        this.sourceOpenScope = sourceOpenScope;
        this.creator = creator;
        this.teacherName = teacherName;
        this.orgId = orgId;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.keywords = keywords;
        this.isAvailable = isAvailable;
        this.isFromThirdParty = isFromThirdParty;
    }

    public SheetCourseAvailableThirdparty() {
        super();
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id == null ? null : course_id.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getSourcePubStatus() {
        return sourcePubStatus;
    }

    public void setSourcePubStatus(String sourcePubStatus) {
        this.sourcePubStatus = sourcePubStatus == null ? null : sourcePubStatus.trim();
    }

    public String getSourceOpenScope() {
        return sourceOpenScope;
    }

    public void setSourceOpenScope(String sourceOpenScope) {
        this.sourceOpenScope = sourceOpenScope == null ? null : sourceOpenScope.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName == null ? null : tenantName.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable == null ? null : isAvailable.trim();
    }

    public String getIsFromThirdParty() {
        return isFromThirdParty;
    }

    public void setIsFromThirdParty(String isFromThirdParty) {
        this.isFromThirdParty = isFromThirdParty == null ? null : isFromThirdParty.trim();
    }
}