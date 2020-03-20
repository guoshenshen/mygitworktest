package com.elearning.pojo.pub;

import java.util.Date;

public class ClassLive {
    private Long id;

    private String subject;

    private Date startDate;

    private Date invalidDate;

    private String teacherToken;

    private String assistantToken;

    private String studentToken;

    private String studentClientToken;

    private String speakerInfo;

    private String scheduleInfo;

    private Integer webJoin;

    private Integer clientJoin;

    private String description;

    private Integer duration;

    private Integer uiMode;

    private String uiColor;

    private Integer scene;

    private Boolean uiWindow;

    private Boolean uiVideo;

    private Boolean upgrade;

    private Integer sec;

    private Boolean realtime;

    private Integer maxAttendees;

    private String loginName;

    private String password;

    private Integer creatorOperatorId;

    private Integer orgId;

    private Integer tenantId;

    private Integer openScope;

    private String openOrgSEQ;

    private String sponsorName;

    private String liveclassid;

    private String liveclassnumber;

    private String teacherJoinUrl;

    private String studentJoinUrl;

    private String picUrl;

    private Boolean isOnlyLoginWatch;

    private Boolean isOnlyLoginTeach;

    private Integer status;

    private Integer isAppShow;

    public ClassLive(Long id, String subject, Date startDate, Date invalidDate, String teacherToken, String assistantToken, String studentToken, String studentClientToken, String speakerInfo, String scheduleInfo, Integer webJoin, Integer clientJoin, String description, Integer duration, Integer uiMode, String uiColor, Integer scene, Boolean uiWindow, Boolean uiVideo, Boolean upgrade, Integer sec, Boolean realtime, Integer maxAttendees, String loginName, String password, Integer creatorOperatorId, Integer orgId, Integer tenantId, Integer openScope, String openOrgSEQ, String sponsorName, String liveclassid, String liveclassnumber, String teacherJoinUrl, String studentJoinUrl, String picUrl, Boolean isOnlyLoginWatch, Boolean isOnlyLoginTeach, Integer status, Integer isAppShow) {
        this.id = id;
        this.subject = subject;
        this.startDate = startDate;
        this.invalidDate = invalidDate;
        this.teacherToken = teacherToken;
        this.assistantToken = assistantToken;
        this.studentToken = studentToken;
        this.studentClientToken = studentClientToken;
        this.speakerInfo = speakerInfo;
        this.scheduleInfo = scheduleInfo;
        this.webJoin = webJoin;
        this.clientJoin = clientJoin;
        this.description = description;
        this.duration = duration;
        this.uiMode = uiMode;
        this.uiColor = uiColor;
        this.scene = scene;
        this.uiWindow = uiWindow;
        this.uiVideo = uiVideo;
        this.upgrade = upgrade;
        this.sec = sec;
        this.realtime = realtime;
        this.maxAttendees = maxAttendees;
        this.loginName = loginName;
        this.password = password;
        this.creatorOperatorId = creatorOperatorId;
        this.orgId = orgId;
        this.tenantId = tenantId;
        this.openScope = openScope;
        this.openOrgSEQ = openOrgSEQ;
        this.sponsorName = sponsorName;
        this.liveclassid = liveclassid;
        this.liveclassnumber = liveclassnumber;
        this.teacherJoinUrl = teacherJoinUrl;
        this.studentJoinUrl = studentJoinUrl;
        this.picUrl = picUrl;
        this.isOnlyLoginWatch = isOnlyLoginWatch;
        this.isOnlyLoginTeach = isOnlyLoginTeach;
        this.status = status;
        this.isAppShow = isAppShow;
    }

    public ClassLive() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getTeacherToken() {
        return teacherToken;
    }

    public void setTeacherToken(String teacherToken) {
        this.teacherToken = teacherToken == null ? null : teacherToken.trim();
    }

    public String getAssistantToken() {
        return assistantToken;
    }

    public void setAssistantToken(String assistantToken) {
        this.assistantToken = assistantToken == null ? null : assistantToken.trim();
    }

    public String getStudentToken() {
        return studentToken;
    }

    public void setStudentToken(String studentToken) {
        this.studentToken = studentToken == null ? null : studentToken.trim();
    }

    public String getStudentClientToken() {
        return studentClientToken;
    }

    public void setStudentClientToken(String studentClientToken) {
        this.studentClientToken = studentClientToken == null ? null : studentClientToken.trim();
    }

    public String getSpeakerInfo() {
        return speakerInfo;
    }

    public void setSpeakerInfo(String speakerInfo) {
        this.speakerInfo = speakerInfo == null ? null : speakerInfo.trim();
    }

    public String getScheduleInfo() {
        return scheduleInfo;
    }

    public void setScheduleInfo(String scheduleInfo) {
        this.scheduleInfo = scheduleInfo == null ? null : scheduleInfo.trim();
    }

    public Integer getWebJoin() {
        return webJoin;
    }

    public void setWebJoin(Integer webJoin) {
        this.webJoin = webJoin;
    }

    public Integer getClientJoin() {
        return clientJoin;
    }

    public void setClientJoin(Integer clientJoin) {
        this.clientJoin = clientJoin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getUiMode() {
        return uiMode;
    }

    public void setUiMode(Integer uiMode) {
        this.uiMode = uiMode;
    }

    public String getUiColor() {
        return uiColor;
    }

    public void setUiColor(String uiColor) {
        this.uiColor = uiColor == null ? null : uiColor.trim();
    }

    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
    }

    public Boolean getUiWindow() {
        return uiWindow;
    }

    public void setUiWindow(Boolean uiWindow) {
        this.uiWindow = uiWindow;
    }

    public Boolean getUiVideo() {
        return uiVideo;
    }

    public void setUiVideo(Boolean uiVideo) {
        this.uiVideo = uiVideo;
    }

    public Boolean getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Boolean upgrade) {
        this.upgrade = upgrade;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }

    public Boolean getRealtime() {
        return realtime;
    }

    public void setRealtime(Boolean realtime) {
        this.realtime = realtime;
    }

    public Integer getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(Integer maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getCreatorOperatorId() {
        return creatorOperatorId;
    }

    public void setCreatorOperatorId(Integer creatorOperatorId) {
        this.creatorOperatorId = creatorOperatorId;
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

    public Integer getOpenScope() {
        return openScope;
    }

    public void setOpenScope(Integer openScope) {
        this.openScope = openScope;
    }

    public String getOpenOrgSEQ() {
        return openOrgSEQ;
    }

    public void setOpenOrgSEQ(String openOrgSEQ) {
        this.openOrgSEQ = openOrgSEQ == null ? null : openOrgSEQ.trim();
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName == null ? null : sponsorName.trim();
    }

    public String getLiveclassid() {
        return liveclassid;
    }

    public void setLiveclassid(String liveclassid) {
        this.liveclassid = liveclassid == null ? null : liveclassid.trim();
    }

    public String getLiveclassnumber() {
        return liveclassnumber;
    }

    public void setLiveclassnumber(String liveclassnumber) {
        this.liveclassnumber = liveclassnumber == null ? null : liveclassnumber.trim();
    }

    public String getTeacherJoinUrl() {
        return teacherJoinUrl;
    }

    public void setTeacherJoinUrl(String teacherJoinUrl) {
        this.teacherJoinUrl = teacherJoinUrl == null ? null : teacherJoinUrl.trim();
    }

    public String getStudentJoinUrl() {
        return studentJoinUrl;
    }

    public void setStudentJoinUrl(String studentJoinUrl) {
        this.studentJoinUrl = studentJoinUrl == null ? null : studentJoinUrl.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Boolean getIsOnlyLoginWatch() {
        return isOnlyLoginWatch;
    }

    public void setIsOnlyLoginWatch(Boolean isOnlyLoginWatch) {
        this.isOnlyLoginWatch = isOnlyLoginWatch;
    }

    public Boolean getIsOnlyLoginTeach() {
        return isOnlyLoginTeach;
    }

    public void setIsOnlyLoginTeach(Boolean isOnlyLoginTeach) {
        this.isOnlyLoginTeach = isOnlyLoginTeach;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsAppShow() {
        return isAppShow;
    }

    public void setIsAppShow(Integer isAppShow) {
        this.isAppShow = isAppShow;
    }
}