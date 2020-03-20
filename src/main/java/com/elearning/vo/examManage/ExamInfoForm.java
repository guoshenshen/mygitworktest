package com.elearning.vo.examManage;

public class ExamInfoForm {

    private static final long serialVersionUID = 4830286196580591037L;

    private String examTitle;
    private String startTime;
    private String endTime;
    private Double totalTime;
    private Integer examType;
    private Integer examStyle;
    private Double proportion;
    private Integer isApply;
    private Integer isNeedApprove;
    private String examDescription;
    private Integer teacherId;
    private Integer orgId;
    private Integer ifDisplayAnswer;
    private Integer ifRepeatExam;

    public String getExamTitle() {
        return examTitle;
    }
    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
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
    public String getExamDescription() {
        return examDescription;
    }
    public void setExamDescription(String examDescription) {
        this.examDescription = examDescription;
    }
    public Integer getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
    public Integer getOrgId() {
        return orgId;
    }
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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



}
