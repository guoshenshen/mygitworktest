package com.elearning.pojo.systemManage;

public class VUserRole {
    private Integer operatorID;

    private String userID;

    private String operatorName;

    private String empCode;

    private Integer orgID;

    private String orgName;

    private Integer ID;

    private Integer roleID;

    private String roleName;

    private Integer roleOrgID;

    private Integer creatorID;

    private Integer status;

    public VUserRole(Integer operatorID, String userID, String operatorName, String empCode, Integer orgID, String orgName, Integer ID, Integer roleID, String roleName, Integer roleOrgID, Integer creatorID, Integer status) {
        this.operatorID = operatorID;
        this.userID = userID;
        this.operatorName = operatorName;
        this.empCode = empCode;
        this.orgID = orgID;
        this.orgName = orgName;
        this.ID = ID;
        this.roleID = roleID;
        this.roleName = roleName;
        this.roleOrgID = roleOrgID;
        this.creatorID = creatorID;
        this.status = status;
    }

    public VUserRole() {
        super();
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode == null ? null : empCode.trim();
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getRoleOrgID() {
        return roleOrgID;
    }

    public void setRoleOrgID(Integer roleOrgID) {
        this.roleOrgID = roleOrgID;
    }

    public Integer getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}