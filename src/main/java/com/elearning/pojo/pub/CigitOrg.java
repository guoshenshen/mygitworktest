package com.elearning.pojo.pub;

public class CigitOrg {
    private String orgName;

    private String orgCode;

    private Integer orgId;

    public CigitOrg(String orgName, String orgCode, Integer orgId) {
        this.orgName = orgName;
        this.orgCode = orgCode;
        this.orgId = orgId;
    }

    public CigitOrg() {
        super();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}