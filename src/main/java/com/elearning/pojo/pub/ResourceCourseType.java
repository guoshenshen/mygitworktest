package com.elearning.pojo.pub;

public class ResourceCourseType {
    private Long id;

    private Long resourceId;

    private Integer courseTypeId;

    private Integer resourceType;

    public ResourceCourseType(Long id, Long resourceId, Integer courseTypeId, Integer resourceType) {
        this.id = id;
        this.resourceId = resourceId;
        this.courseTypeId = courseTypeId;
        this.resourceType = resourceType;
    }

    public ResourceCourseType() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }
}