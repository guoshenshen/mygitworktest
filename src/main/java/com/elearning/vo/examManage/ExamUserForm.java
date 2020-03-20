package com.elearning.vo.examManage;

public class ExamUserForm {

    private static final long serialVersionUID = 4830286196580591037L;

    private Integer id;
    private Integer examId;
    private Integer userId;
    private String userName;

    public Integer getExamId() {
        return examId;
    }
    public void setExamId(Integer examId) {
        this.examId = examId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

}
