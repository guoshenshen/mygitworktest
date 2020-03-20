package com.elearning.pojo.systemManage;

public class SysFormField extends SysFormFieldKey {
    private String fieldName;

    private Integer fieldSeq;

    private Integer visible;

    private Integer colspan;

    private Integer detailVisible;

    private Integer studentVisible;

    private Integer queryVisible;

    private Integer queryCondition;

    private String defaultValue;

    private String valueFormat;

    private Integer dataResource;

    private String parentAttribute;

    private String formula;

    private String scope;

    public SysFormField(Integer formId, Integer tenantId, String fieldAttribute, String fieldName, Integer fieldSeq, Integer visible, Integer colspan, Integer detailVisible, Integer studentVisible, Integer queryVisible, Integer queryCondition, String defaultValue, String valueFormat, Integer dataResource, String parentAttribute, String formula, String scope) {
        super(formId, tenantId, fieldAttribute);
        this.fieldName = fieldName;
        this.fieldSeq = fieldSeq;
        this.visible = visible;
        this.colspan = colspan;
        this.detailVisible = detailVisible;
        this.studentVisible = studentVisible;
        this.queryVisible = queryVisible;
        this.queryCondition = queryCondition;
        this.defaultValue = defaultValue;
        this.valueFormat = valueFormat;
        this.dataResource = dataResource;
        this.parentAttribute = parentAttribute;
        this.formula = formula;
        this.scope = scope;
    }

    public SysFormField() {
        super();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public Integer getFieldSeq() {
        return fieldSeq;
    }

    public void setFieldSeq(Integer fieldSeq) {
        this.fieldSeq = fieldSeq;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getColspan() {
        return colspan;
    }

    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }

    public Integer getDetailVisible() {
        return detailVisible;
    }

    public void setDetailVisible(Integer detailVisible) {
        this.detailVisible = detailVisible;
    }

    public Integer getStudentVisible() {
        return studentVisible;
    }

    public void setStudentVisible(Integer studentVisible) {
        this.studentVisible = studentVisible;
    }

    public Integer getQueryVisible() {
        return queryVisible;
    }

    public void setQueryVisible(Integer queryVisible) {
        this.queryVisible = queryVisible;
    }

    public Integer getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(Integer queryCondition) {
        this.queryCondition = queryCondition;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public String getValueFormat() {
        return valueFormat;
    }

    public void setValueFormat(String valueFormat) {
        this.valueFormat = valueFormat == null ? null : valueFormat.trim();
    }

    public Integer getDataResource() {
        return dataResource;
    }

    public void setDataResource(Integer dataResource) {
        this.dataResource = dataResource;
    }

    public String getParentAttribute() {
        return parentAttribute;
    }

    public void setParentAttribute(String parentAttribute) {
        this.parentAttribute = parentAttribute == null ? null : parentAttribute.trim();
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }
}