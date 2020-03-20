package com.elearning.pojo.onlinetraining;

import java.util.Date;

public class TrainSignRecord {
    private Integer id;

    private Integer sign_id;

    private Integer user_id;

    private Date time;

    private Integer state;

    private String openId;

    //----------外部字段，数据库中不存在------------

    private String timeS;

    private String operatorName;

    private String orgName;

    private String email;

    private String userName;

    public TrainSignRecord(Integer id, Integer sign_id, Integer user_id, Date time, Integer state, String openId) {
        this.id = id;
        this.sign_id = sign_id;
        this.user_id = user_id;
        this.time = time;
        this.state = state;
        this.openId = openId;
    }

    public TrainSignRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSign_id() {
        return sign_id;
    }

    public void setSign_id(Integer sign_id) {
        this.sign_id = sign_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimeS() {
        return timeS;
    }

    public void setTimeS(String timeS) {
        this.timeS = timeS;
    }
}