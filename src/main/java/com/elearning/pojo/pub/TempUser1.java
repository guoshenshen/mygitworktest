package com.elearning.pojo.pub;

public class TempUser1 {
    private Integer id;

    private String orgName;

    private String operatorName;

    private String title;

    private String orgSEQ;

    private Integer operatorID;

    private String userID;

    private String empCode;

    private Integer eorgID;

    private String eorgName;

    private String eorgSEQ;

    public TempUser1(Integer id, String orgName, String operatorName, String title, String orgSEQ, Integer operatorID, String userID, String empCode, Integer eorgID, String eorgName, String eorgSEQ) {
        this.id = id;
        this.orgName = orgName;
        this.operatorName = operatorName;
        this.title = title;
        this.orgSEQ = orgSEQ;
        this.operatorID = operatorID;
        this.userID = userID;
        this.empCode = empCode;
        this.eorgID = eorgID;
        this.eorgName = eorgName;
        this.eorgSEQ = eorgSEQ;
    }

    public TempUser1() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getOrgSEQ() {
        return orgSEQ;
    }

    public void setOrgSEQ(String orgSEQ) {
        this.orgSEQ = orgSEQ == null ? null : orgSEQ.trim();
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID == null ? null : userID.trim();
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode == null ? null : empCode.trim();
    }

    public Integer getEorgID() {
        return eorgID;
    }

    public void setEorgID(Integer eorgID) {
        this.eorgID = eorgID;
    }

    public String getEorgName() {
        return eorgName;
    }

    public void setEorgName(String eorgName) {
        this.eorgName = eorgName == null ? null : eorgName.trim();
    }

    public String getEorgSEQ() {
        return eorgSEQ;
    }

    public void setEorgSEQ(String eorgSEQ) {
        this.eorgSEQ = eorgSEQ == null ? null : eorgSEQ.trim();
    }
}