package com.elearning.pojo.pub;

import java.util.Date;

public class LiveTeacher {
    private Integer id;

    private Integer operatorId;

    private String teacherLoginName;

    private String teacherPassword;

    private Date createDate;

    public LiveTeacher(Integer id, Integer operatorId, String teacherLoginName, String teacherPassword, Date createDate) {
        this.id = id;
        this.operatorId = operatorId;
        this.teacherLoginName = teacherLoginName;
        this.teacherPassword = teacherPassword;
        this.createDate = createDate;
    }

    public LiveTeacher() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getTeacherLoginName() {
        return teacherLoginName;
    }

    public void setTeacherLoginName(String teacherLoginName) {
        this.teacherLoginName = teacherLoginName == null ? null : teacherLoginName.trim();
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword == null ? null : teacherPassword.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}