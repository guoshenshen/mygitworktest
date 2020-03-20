package com.elearning.pojo.systemManage;

public class SysTenantResourceKey {
    private Integer resourceId;

    private Integer tenantId;

    private Integer roleId;

    public SysTenantResourceKey(Integer resourceId, Integer tenantId, Integer roleId) {
        this.resourceId = resourceId;
        this.tenantId = tenantId;
        this.roleId = roleId;
    }

    public SysTenantResourceKey() {
        super();
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}