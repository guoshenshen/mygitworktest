package com.elearning.pojo.systemManage;

import java.util.Date;

public class TimeSetting {
    private Integer id;

    private String year;

    private String month;

    private String day;

    private Integer status;

    private Date plstartline;

    private Date pldeadline;

    private Date bprstartline;

    private Date bprdeadline;

    private Integer pwdValidTime;

    public TimeSetting(Integer id, String year, String month, String day, Integer status, Date plstartline, Date pldeadline, Date bprstartline, Date bprdeadline, Integer pwdValidTime) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.status = status;
        this.plstartline = plstartline;
        this.pldeadline = pldeadline;
        this.bprstartline = bprstartline;
        this.bprdeadline = bprdeadline;
        this.pwdValidTime = pwdValidTime;
    }

    public TimeSetting() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPlstartline() {
        return plstartline;
    }

    public void setPlstartline(Date plstartline) {
        this.plstartline = plstartline;
    }

    public Date getPldeadline() {
        return pldeadline;
    }

    public void setPldeadline(Date pldeadline) {
        this.pldeadline = pldeadline;
    }

    public Date getBprstartline() {
        return bprstartline;
    }

    public void setBprstartline(Date bprstartline) {
        this.bprstartline = bprstartline;
    }

    public Date getBprdeadline() {
        return bprdeadline;
    }

    public void setBprdeadline(Date bprdeadline) {
        this.bprdeadline = bprdeadline;
    }

    public Integer getPwdValidTime() {
        return pwdValidTime;
    }

    public void setPwdValidTime(Integer pwdValidTime) {
        this.pwdValidTime = pwdValidTime;
    }
}