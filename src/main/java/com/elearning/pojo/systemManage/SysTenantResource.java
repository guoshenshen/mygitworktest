package com.elearning.pojo.systemManage;

public class SysTenantResource extends SysTenantResourceKey {
    private String customName;

    private Integer visible;

    public SysTenantResource(Integer resourceId, Integer tenantId, Integer roleId, String customName, Integer visible) {
        super(resourceId, tenantId, roleId);
        this.customName = customName;
        this.visible = visible;
    }

    public SysTenantResource() {
        super();
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName == null ? null : customName.trim();
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}