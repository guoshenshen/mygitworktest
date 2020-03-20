package com.elearning.pojo.courseshop;

import java.util.Date;

public class CourseCollect {
    private Integer id;

    private Long courseId;

    private Integer orgId;

    private Integer operatorId;

    private Date createDate;

    private Integer tenantId;

    public CourseCollect(Integer id, Long courseId, Integer orgId, Integer operatorId, Date createDate, Integer tenantId) {
        this.id = id;
        this.courseId = courseId;
        this.orgId = orgId;
        this.operatorId = operatorId;
        this.createDate = createDate;
        this.tenantId = tenantId;
    }

    public CourseCollect() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}