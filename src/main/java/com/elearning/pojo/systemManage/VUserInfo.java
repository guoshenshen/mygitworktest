package com.elearning.pojo.systemManage;

import java.util.Date;

public class VUserInfo {
    private Integer operatorID;

    private String userID;

    private Integer status;

    private String operatorName;

    private String empCode;

    private Integer orgID;

    private String EMPTYPE;

    private String EMPSTATUS;

    private String orgName;

    private String orgSEQ;

    private String statusName;

    private Date regDate;

    private Integer isKeyFigure;

    private Integer isCtnEduRequire;

    private Integer newEmployeeYear;

    private Date statusModifyDate;

    public VUserInfo(Integer operatorID, String userID, Integer status, String operatorName, String empCode, Integer orgID, String EMPTYPE, String EMPSTATUS, String orgName, String orgSEQ, String statusName, Date regDate, Integer isKeyFigure, Integer isCtnEduRequire, Integer newEmployeeYear, Date statusModifyDate) {
        this.operatorID = operatorID;
        this.userID = userID;
        this.status = status;
        this.operatorName = operatorName;
        this.empCode = empCode;
        this.orgID = orgID;
        this.EMPTYPE = EMPTYPE;
        this.EMPSTATUS = EMPSTATUS;
        this.orgName = orgName;
        this.orgSEQ = orgSEQ;
        this.statusName = statusName;
        this.regDate = regDate;
        this.isKeyFigure = isKeyFigure;
        this.isCtnEduRequire = isCtnEduRequire;
        this.newEmployeeYear = newEmployeeYear;
        this.statusModifyDate = statusModifyDate;
    }

    public VUserInfo() {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getEMPTYPE() {
        return EMPTYPE;
    }

    public void setEMPTYPE(String EMPTYPE) {
        this.EMPTYPE = EMPTYPE == null ? null : EMPTYPE.trim();
    }

    public String getEMPSTATUS() {
        return EMPSTATUS;
    }

    public void setEMPSTATUS(String EMPSTATUS) {
        this.EMPSTATUS = EMPSTATUS == null ? null : EMPSTATUS.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgSEQ() {
        return orgSEQ;
    }

    public void setOrgSEQ(String orgSEQ) {
        this.orgSEQ = orgSEQ == null ? null : orgSEQ.trim();
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName == null ? null : statusName.trim();
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getIsKeyFigure() {
        return isKeyFigure;
    }

    public void setIsKeyFigure(Integer isKeyFigure) {
        this.isKeyFigure = isKeyFigure;
    }

    public Integer getIsCtnEduRequire() {
        return isCtnEduRequire;
    }

    public void setIsCtnEduRequire(Integer isCtnEduRequire) {
        this.isCtnEduRequire = isCtnEduRequire;
    }

    public Integer getNewEmployeeYear() {
        return newEmployeeYear;
    }

    public void setNewEmployeeYear(Integer newEmployeeYear) {
        this.newEmployeeYear = newEmployeeYear;
    }

    public Date getStatusModifyDate() {
        return statusModifyDate;
    }

    public void setStatusModifyDate(Date statusModifyDate) {
        this.statusModifyDate = statusModifyDate;
    }
}