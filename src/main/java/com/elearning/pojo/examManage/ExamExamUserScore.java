package com.elearning.pojo.examManage;

public class ExamExamUserScore {
    private Integer ID;

    private Integer examID;

    private Integer employeeID;

    private Double paperScore;

    private Double usualScore;

    private Double totalScore;

    private Double objItemScore;

    private Double subItemScore;

    private Integer paperId;

    private Integer replyId;

    public ExamExamUserScore(Integer ID, Integer examID, Integer employeeID, Double paperScore, Double usualScore, Double totalScore, Double objItemScore, Double subItemScore, Integer paperId, Integer replyId) {
        this.ID = ID;
        this.examID = examID;
        this.employeeID = employeeID;
        this.paperScore = paperScore;
        this.usualScore = usualScore;
        this.totalScore = totalScore;
        this.objItemScore = objItemScore;
        this.subItemScore = subItemScore;
        this.paperId = paperId;
        this.replyId = replyId;
    }

    public ExamExamUserScore() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getExamID() {
        return examID;
    }

    public void setExamID(Integer examID) {
        this.examID = examID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Double getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(Double paperScore) {
        this.paperScore = paperScore;
    }

    public Double getUsualScore() {
        return usualScore;
    }

    public void setUsualScore(Double usualScore) {
        this.usualScore = usualScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getObjItemScore() {
        return objItemScore;
    }

    public void setObjItemScore(Double objItemScore) {
        this.objItemScore = objItemScore;
    }

    public Double getSubItemScore() {
        return subItemScore;
    }

    public void setSubItemScore(Double subItemScore) {
        this.subItemScore = subItemScore;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
}