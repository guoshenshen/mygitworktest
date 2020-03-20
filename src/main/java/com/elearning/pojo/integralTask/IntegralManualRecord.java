package com.elearning.pojo.integralTask;

import java.util.Date;

public class IntegralManualRecord {
    private Long id;

    private Long operatorId;

    private String operatorName;

    private Long studentId;

    private Long integralId;

    private String operateItemNames;

    private String operateItemMaps;

    private Date operateTime;

    public IntegralManualRecord(Long id, Long operatorId, String operatorName, Long studentId, Long integralId, String operateItemNames, String operateItemMaps, Date operateTime) {
        this.id = id;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.studentId = studentId;
        this.integralId = integralId;
        this.operateItemNames = operateItemNames;
        this.operateItemMaps = operateItemMaps;
        this.operateTime = operateTime;
    }

    public IntegralManualRecord() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getIntegralId() {
        return integralId;
    }

    public void setIntegralId(Long integralId) {
        this.integralId = integralId;
    }

    public String getOperateItemNames() {
        return operateItemNames;
    }

    public void setOperateItemNames(String operateItemNames) {
        this.operateItemNames = operateItemNames == null ? null : operateItemNames.trim();
    }

    public String getOperateItemMaps() {
        return operateItemMaps;
    }

    public void setOperateItemMaps(String operateItemMaps) {
        this.operateItemMaps = operateItemMaps == null ? null : operateItemMaps.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}