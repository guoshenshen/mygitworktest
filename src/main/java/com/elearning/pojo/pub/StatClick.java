package com.elearning.pojo.pub;

import java.util.Date;

public class StatClick {
    private Integer id;

    private Date date;

    private Integer num;

    private String type;

    public StatClick(Integer id, Date date, Integer num, String type) {
        this.id = id;
        this.date = date;
        this.num = num;
        this.type = type;
    }

    public StatClick() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}