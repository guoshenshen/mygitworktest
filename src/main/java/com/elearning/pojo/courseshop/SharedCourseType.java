package com.elearning.pojo.courseshop;

public class SharedCourseType {
    private Integer sharedCourseTypeId;

    private String sharedCourseTypeName;

    private Integer parentId;

    private String fullPath;

    private Integer typeIndex;

    private String remark;

    public SharedCourseType(Integer sharedCourseTypeId, String sharedCourseTypeName, Integer parentId, String fullPath, Integer typeIndex, String remark) {
        this.sharedCourseTypeId = sharedCourseTypeId;
        this.sharedCourseTypeName = sharedCourseTypeName;
        this.parentId = parentId;
        this.fullPath = fullPath;
        this.typeIndex = typeIndex;
        this.remark = remark;
    }

    public SharedCourseType() {
        super();
    }

    public Integer getSharedCourseTypeId() {
        return sharedCourseTypeId;
    }

    public void setSharedCourseTypeId(Integer sharedCourseTypeId) {
        this.sharedCourseTypeId = sharedCourseTypeId;
    }

    public String getSharedCourseTypeName() {
        return sharedCourseTypeName;
    }

    public void setSharedCourseTypeName(String sharedCourseTypeName) {
        this.sharedCourseTypeName = sharedCourseTypeName == null ? null : sharedCourseTypeName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath == null ? null : fullPath.trim();
    }

    public Integer getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(Integer typeIndex) {
        this.typeIndex = typeIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}