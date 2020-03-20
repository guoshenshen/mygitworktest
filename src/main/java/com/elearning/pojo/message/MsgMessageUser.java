package com.elearning.pojo.message;

import java.util.Date;

public class MsgMessageUser {
    private Integer ID;

    private Integer msgID;

    private Integer userID;

    private Integer role;

    private Integer attendable;

    private String comment;

    private Integer trainID;

    private Date sendbackTime;

    private Integer state;

    private String openid;

    private Integer type;

    public MsgMessageUser(Integer ID, Integer msgID, Integer userID, Integer role, Integer attendable, String comment, Integer trainID, Date sendbackTime, Integer state, String openid, Integer type) {
        this.ID = ID;
        this.msgID = msgID;
        this.userID = userID;
        this.role = role;
        this.attendable = attendable;
        this.comment = comment;
        this.trainID = trainID;
        this.sendbackTime = sendbackTime;
        this.state = state;
        this.openid = openid;
        this.type = type;
    }

    public MsgMessageUser() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getMsgID() {
        return msgID;
    }

    public void setMsgID(Integer msgID) {
        this.msgID = msgID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getAttendable() {
        return attendable;
    }

    public void setAttendable(Integer attendable) {
        this.attendable = attendable;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getTrainID() {
        return trainID;
    }

    public void setTrainID(Integer trainID) {
        this.trainID = trainID;
    }

    public Date getSendbackTime() {
        return sendbackTime;
    }

    public void setSendbackTime(Date sendbackTime) {
        this.sendbackTime = sendbackTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}