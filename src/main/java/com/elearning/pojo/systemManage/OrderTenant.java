package com.elearning.pojo.systemManage;

public class OrderTenant {
    private Integer id;

    private Integer tenantId;

    private String tenantName;

    private Integer orgId;

    private Integer order;

    private String openOrgSEQ;

    public OrderTenant(Integer id, Integer tenantId, String tenantName, Integer orgId, Integer order, String openOrgSEQ) {
        this.id = id;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.orgId = orgId;
        this.order = order;
        this.openOrgSEQ = openOrgSEQ;
    }

    public OrderTenant() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName == null ? null : tenantName.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getOpenOrgSEQ() {
        return openOrgSEQ;
    }

    public void setOpenOrgSEQ(String openOrgSEQ) {
        this.openOrgSEQ = openOrgSEQ == null ? null : openOrgSEQ.trim();
    }
}