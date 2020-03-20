package com.elearning.pojo.pub;

public class CigitUsers {
    private String operatorName;

    private String userID;

    private String empCode;

    private String orgName;

    public CigitUsers(String operatorName, String userID, String empCode, String orgName) {
        this.operatorName = operatorName;
        this.userID = userID;
        this.empCode = empCode;
        this.orgName = orgName;
    }

    public CigitUsers() {
        super();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }
}