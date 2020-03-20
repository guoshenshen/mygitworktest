package com.elearning.pojo.pub;

public class OrgXinxi {
    private Long id;

    private String orgName;

    private String orgcode;

    private Long orgId;

    private Integer tenantId;

    public OrgXinxi(Long id, String orgName, String orgcode, Long orgId, Integer tenantId) {
        this.id = id;
        this.orgName = orgName;
        this.orgcode = orgcode;
        this.orgId = orgId;
        this.tenantId = tenantId;
    }

    public OrgXinxi() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode == null ? null : orgcode.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}