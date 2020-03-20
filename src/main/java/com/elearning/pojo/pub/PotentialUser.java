package com.elearning.pojo.pub;

public class PotentialUser {
    private Integer pid;

    private String operatorName;

    private String orgName;

    private String email;

    private String phone;

    private String idNum;

    public PotentialUser(Integer pid, String operatorName, String orgName, String email, String phone, String idNum) {
        this.pid = pid;
        this.operatorName = operatorName;
        this.orgName = orgName;
        this.email = email;
        this.phone = phone;
        this.idNum = idNum;
    }

    public PotentialUser() {
        super();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum == null ? null : idNum.trim();
    }
}