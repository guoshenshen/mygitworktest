package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtMixTrainSchedule {
    private Integer scheduleId;

    private String scheduleName;

    private Date createDate;

    private Integer creatorId;

    private Integer orgId;

    private Integer isToWebSite;

    public MtMixTrainSchedule(Integer scheduleId, String scheduleName, Date createDate, Integer creatorId, Integer orgId, Integer isToWebSite) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.createDate = createDate;
        this.creatorId = creatorId;
        this.orgId = orgId;
        this.isToWebSite = isToWebSite;
    }

    public MtMixTrainSchedule() {
        super();
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName == null ? null : scheduleName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getIsToWebSite() {
        return isToWebSite;
    }

    public void setIsToWebSite(Integer isToWebSite) {
        this.isToWebSite = isToWebSite;
    }
}