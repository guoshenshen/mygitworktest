package com.elearning.pojo.pub;

import java.util.Date;

public class ForgetPwd {
    private Integer id;

    private String userid;

    private String vcode;

    private Date ctime;

    private Integer status;

    public ForgetPwd(Integer id, String userid, String vcode, Date ctime, Integer status) {
        this.id = id;
        this.userid = userid;
        this.vcode = vcode;
        this.ctime = ctime;
        this.status = status;
    }

    public ForgetPwd() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode == null ? null : vcode.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}