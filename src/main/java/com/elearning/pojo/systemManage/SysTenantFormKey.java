package com.elearning.pojo.systemManage;

public class SysTenantFormKey {
    private Integer formId;

    private Integer tenantId;

    public SysTenantFormKey(Integer formId, Integer tenantId) {
        this.formId = formId;
        this.tenantId = tenantId;
    }

    public SysTenantFormKey() {
        super();
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}