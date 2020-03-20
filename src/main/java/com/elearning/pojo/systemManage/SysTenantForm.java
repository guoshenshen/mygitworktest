package com.elearning.pojo.systemManage;

import java.util.Date;

public class SysTenantForm extends SysTenantFormKey {
    private String formName;

    private Integer operatorId;

    private String operatorName;

    private Date updateDate;

    public SysTenantForm(Integer formId, Integer tenantId, String formName, Integer operatorId, String operatorName, Date updateDate) {
        super(formId, tenantId);
        this.formName = formName;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.updateDate = updateDate;
    }

    public SysTenantForm() {
        super();
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName == null ? null : formName.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}