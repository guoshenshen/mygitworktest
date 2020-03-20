package com.elearning.pojo.pub;

import java.util.Date;

public class LoginAuth {
    private Integer id;

    private Long operatorId;

    private String authenId;

    private Date modifytime;

    private Boolean type;

    public LoginAuth(Integer id, Long operatorId, String authenId, Date modifytime, Boolean type) {
        this.id = id;
        this.operatorId = operatorId;
        this.authenId = authenId;
        this.modifytime = modifytime;
        this.type = type;
    }

    public LoginAuth() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getAuthenId() {
        return authenId;
    }

    public void setAuthenId(String authenId) {
        this.authenId = authenId == null ? null : authenId.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}