package com.elearning.pojo.coursemanage;

public class CourseType {
    private Integer courseTypeID;

    private Integer parentID;

    private String courseTypeName;

    private String fullPath;

    private String typeDesc;

    private Integer tindex;

    private Integer orgId;

    private Integer tenantId;

    public CourseType(Integer courseTypeID, Integer parentID, String courseTypeName, String fullPath, String typeDesc, Integer tindex, Integer orgId, Integer tenantId) {
        this.courseTypeID = courseTypeID;
        this.parentID = parentID;
        this.courseTypeName = courseTypeName;
        this.fullPath = fullPath;
        this.typeDesc = typeDesc;
        this.tindex = tindex;
        this.orgId = orgId;
        this.tenantId = tenantId;
    }

    public CourseType() {
        super();
    }

    public Integer getCourseTypeID() {
        return courseTypeID;
    }

    public void setCourseTypeID(Integer courseTypeID) {
        this.courseTypeID = courseTypeID;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName == null ? null : courseTypeName.trim();
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath == null ? null : fullPath.trim();
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc == null ? null : typeDesc.trim();
    }

    public Integer getTindex() {
        return tindex;
    }

    public void setTindex(Integer tindex) {
        this.tindex = tindex;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}