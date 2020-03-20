package com.elearning.pojo.examManage;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExamExamInfo {
    private Integer ID;

    private String examTitle;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    private Double totalTime;

    private Integer examType;

    private Integer examStyle;

    private Double proportion;

    private Integer isApply;

    private Integer isNeedApprove;

    private Integer teacher_id;

    private Integer joinCount;

    private Integer finishCount;

    private Integer regCount;

    private Integer isPublish;

    private Integer orgId;

    private Integer trainId;

    private Integer ifDisplayAnswer;

    private Integer ifRepeatExam;

    private String examDescription;

    //------------------------------------------------------------------------------------------------------------------
    private String trainName;
    private String userExamStatus;
    private Integer enterflag;
    private Double myPaperScore;
    private Integer paperId;

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getUserExamStatus() {
        return userExamStatus;
    }

    public void setUserExamStatus(String userExamStatus) {
        this.userExamStatus = userExamStatus;
    }

    public Integer getEnterflag() {
        return enterflag;
    }

    public void setEnterflag(Integer enterflag) {
        this.enterflag = enterflag;
    }

    public Double getMyPaperScore() {
        return myPaperScore;
    }

    public void setMyPaperScore(Double myPaperScore) {
        this.myPaperScore = myPaperScore;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    //------------------------------------------------------------------------------------------------------------------

    public ExamExamInfo(Integer ID, String examTitle, Date startTime, Date endTime, Double totalTime, Integer examType, Integer examStyle, Double proportion, Integer isApply, Integer isNeedApprove, Integer teacher_id, Integer joinCount, Integer finishCount, Integer regCount, Integer isPublish, Integer orgId, Integer trainId, Integer ifDisplayAnswer, Integer ifRepeatExam, String examDescription) {
        this.ID = ID;
        this.examTitle = examTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalTime = totalTime;
        this.examType = examType;
        this.examStyle = examStyle;
        this.proportion = proportion;
        this.isApply = isApply;
        this.isNeedApprove = isNeedApprove;
        this.teacher_id = teacher_id;
        this.joinCount = joinCount;
        this.finishCount = finishCount;
        this.regCount = regCount;
        this.isPublish = isPublish;
        this.orgId = orgId;
        this.trainId = trainId;
        this.ifDisplayAnswer = ifDisplayAnswer;
        this.ifRepeatExam = ifRepeatExam;
        this.examDescription = examDescription;
    }

    public ExamExamInfo() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle == null ? null : examTitle.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getExamType() {
        return examType;
    }

    public void setExamType(Integer examType) {
        this.examType = examType;
    }

    public Integer getExamStyle() {
        return examStyle;
    }

    public void setExamStyle(Integer examStyle) {
        this.examStyle = examStyle;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Integer getIsApply() {
        return isApply;
    }

    public void setIsApply(Integer isApply) {
        this.isApply = isApply;
    }

    public Integer getIsNeedApprove() {
        return isNeedApprove;
    }

    public void setIsNeedApprove(Integer isNeedApprove) {
        this.isNeedApprove = isNeedApprove;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Integer getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(Integer joinCount) {
        this.joinCount = joinCount;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getRegCount() {
        return regCount;
    }

    public void setRegCount(Integer regCount) {
        this.regCount = regCount;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getIfDisplayAnswer() {
        return ifDisplayAnswer;
    }

    public void setIfDisplayAnswer(Integer ifDisplayAnswer) {
        this.ifDisplayAnswer = ifDisplayAnswer;
    }

    public Integer getIfRepeatExam() {
        return ifRepeatExam;
    }

    public void setIfRepeatExam(Integer ifRepeatExam) {
        this.ifRepeatExam = ifRepeatExam;
    }

    public String getExamDescription() {
        return examDescription;
    }

    public void setExamDescription(String examDescription) {
        this.examDescription = examDescription == null ? null : examDescription.trim();
    }
}