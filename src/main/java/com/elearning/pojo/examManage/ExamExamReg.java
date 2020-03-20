package com.elearning.pojo.examManage;

public class ExamExamReg {
    private Integer ID;

    private Integer examID;

    private Integer employeeID;

    private Integer examStatus;

    private String examApplyReason;

    public ExamExamReg(Integer ID, Integer examID, Integer employeeID, Integer examStatus, String examApplyReason) {
        this.ID = ID;
        this.examID = examID;
        this.employeeID = employeeID;
        this.examStatus = examStatus;
        this.examApplyReason = examApplyReason;
    }

    public ExamExamReg() {
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

    public Integer getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(Integer examStatus) {
        this.examStatus = examStatus;
    }

    public String getExamApplyReason() {
        return examApplyReason;
    }

    public void setExamApplyReason(String examApplyReason) {
        this.examApplyReason = examApplyReason == null ? null : examApplyReason.trim();
    }
}