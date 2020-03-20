package com.elearning.pojo.message;

import java.util.Date;

public class Message {
    private Integer id;

    private Integer type;

    private String msg;

    private Integer user_id;

    private Date time;

    public Message(Integer id, Integer type, String msg, Integer user_id, Date time) {
        this.id = id;
        this.type = type;
        this.msg = msg;
        this.user_id = user_id;
        this.time = time;
    }

    public Message() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
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
}