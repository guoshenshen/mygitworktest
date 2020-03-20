package com.elearning.pojo.selfStudy;

import java.util.Date;

public class SlStudy {
    private Long slStudyId;

    private Integer orgId;

    private Long courseId;

    private Integer operatorId;

    private Date beginTime;

    private Date endTime;

    private String extParam;

    public SlStudy(Long slStudyId, Integer orgId, Long courseId, Integer operatorId, Date beginTime, Date endTime, String extParam) {
        this.slStudyId = slStudyId;
        this.orgId = orgId;
        this.courseId = courseId;
        this.operatorId = operatorId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.extParam = extParam;
    }

    public SlStudy() {
        super();
    }

    public Long getSlStudyId() {
        return slStudyId;
    }

    public void setSlStudyId(Long slStudyId) {
        this.slStudyId = slStudyId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getExtParam() {
        return extParam;
    }

    public void setExtParam(String extParam) {
        this.extParam = extParam == null ? null : extParam.trim();
    }
}