package com.elearning.pojo.pub;

public class EosorgTOrgEmpGeneral {
    private Integer id;

    private Integer orgId;

    private String orgCode;

    private String orgName;

    private Integer year;

    private Integer onJobStaffTotalNum;

    public EosorgTOrgEmpGeneral(Integer id, Integer orgId, String orgCode, String orgName, Integer year, Integer onJobStaffTotalNum) {
        this.id = id;
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.year = year;
        this.onJobStaffTotalNum = onJobStaffTotalNum;
    }

    public EosorgTOrgEmpGeneral() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getOnJobStaffTotalNum() {
        return onJobStaffTotalNum;
    }

    public void setOnJobStaffTotalNum(Integer onJobStaffTotalNum) {
        this.onJobStaffTotalNum = onJobStaffTotalNum;
    }
}