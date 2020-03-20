package com.elearning.pojo.systemManage;

public class SysFormFieldKey {
    private Integer formId;

    private Integer tenantId;

    private String fieldAttribute;

    public SysFormFieldKey(Integer formId, Integer tenantId, String fieldAttribute) {
        this.formId = formId;
        this.tenantId = tenantId;
        this.fieldAttribute = fieldAttribute;
    }

    public SysFormFieldKey() {
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

    public String getFieldAttribute() {
        return fieldAttribute;
    }

    public void setFieldAttribute(String fieldAttribute) {
        this.fieldAttribute = fieldAttribute == null ? null : fieldAttribute.trim();
    }
}