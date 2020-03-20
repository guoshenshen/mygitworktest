package com.elearning.pojo.pub;

import java.util.Date;

public class OrgCourseType {
    private Integer id;

    private Integer orgId;

    private Integer courseTypeId;

    private Date modifytime;

    public OrgCourseType(Integer id, Integer orgId, Integer courseTypeId, Date modifytime) {
        this.id = id;
        this.orgId = orgId;
        this.courseTypeId = courseTypeId;
        this.modifytime = modifytime;
    }

    public OrgCourseType() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}