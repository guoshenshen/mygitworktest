package com.elearning.vo.examManage;

public class ExamOnlinePaperReadingForm {

    private static final long serialVersionUID = 4830286196580591037L;

    private Integer examId;
    private Integer employeeID;
    private String employeeName;
    private String orgName;
    private String examPartStatus;
    private String readingStatus;
    private String empCode;
    private Integer replyId;
    private String submitDate;


    public Integer getExamId() {
        return examId;
    }
    public void setExamId(Integer examId) {
        this.examId = examId;
    }
    public Integer getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getReadingStatus() {
        return readingStatus;
    }
    public void setReadingStatus(String readingStatus) {
        this.readingStatus = readingStatus;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getExamPartStatus() {
        return examPartStatus;
    }
    public void setExamPartStatus(String examPartStatus) {
        this.examPartStatus = examPartStatus;
    }
    public String getEmpCode() {
        return empCode;
    }
    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
    public Integer getReplyId() {
        return replyId;
    }
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
    public String getSubmitDate() {
        return submitDate;
    }
    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }
}
