package com.elearning.pojo.pub;

import java.util.Date;

public class DynamicLogin {
    private Integer id;

    private Long dynamicKey;

    private Integer operatorId;

    private Date createTime;

    private Integer validPeriod;

    private Short type;

    public DynamicLogin(Integer id, Long dynamicKey, Integer operatorId, Date createTime, Integer validPeriod, Short type) {
        this.id = id;
        this.dynamicKey = dynamicKey;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.validPeriod = validPeriod;
        this.type = type;
    }

    public DynamicLogin() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDynamicKey() {
        return dynamicKey;
    }

    public void setDynamicKey(Long dynamicKey) {
        this.dynamicKey = dynamicKey;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getValidPeriod() {
        return validPeriod;
    }

    public void setValidPeriod(Integer validPeriod) {
        this.validPeriod = validPeriod;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}