package com.elearning.pojo.pub;

public class JgOperator {
    private Integer operatorId;

    private Integer orgId;

    private Integer gender;

    private String empcode;

    private String title;

    private Integer parentOrgId;

    private String orgName;

    private String parentOrgName;

    private String operatorName;

    private String userId;

    public JgOperator(Integer operatorId, Integer orgId, Integer gender, String empcode, String title, Integer parentOrgId, String orgName, String parentOrgName, String operatorName, String userId) {
        this.operatorId = operatorId;
        this.orgId = orgId;
        this.gender = gender;
        this.empcode = empcode;
        this.title = title;
        this.parentOrgId = parentOrgId;
        this.orgName = orgName;
        this.parentOrgName = parentOrgName;
        this.operatorName = operatorName;
        this.userId = userId;
    }

    public JgOperator() {
        super();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode == null ? null : empcode.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Integer parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName == null ? null : parentOrgName.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}