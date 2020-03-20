package com.elearning.pojo.mixtraining;

import java.util.Date;

public class MtMixTrainIngTip {
    private Integer tipId;

    private String tipName;

    private Date createDate;

    private Integer operatorId;

    private Integer useCount;

    private String isDefaultTemplate;

    private String remark;

    public MtMixTrainIngTip(Integer tipId, String tipName, Date createDate, Integer operatorId, Integer useCount, String isDefaultTemplate, String remark) {
        this.tipId = tipId;
        this.tipName = tipName;
        this.createDate = createDate;
        this.operatorId = operatorId;
        this.useCount = useCount;
        this.isDefaultTemplate = isDefaultTemplate;
        this.remark = remark;
    }

    public MtMixTrainIngTip() {
        super();
    }

    public Integer getTipId() {
        return tipId;
    }

    public void setTipId(Integer tipId) {
        this.tipId = tipId;
    }

    public String getTipName() {
        return tipName;
    }

    public void setTipName(String tipName) {
        this.tipName = tipName == null ? null : tipName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public String getIsDefaultTemplate() {
        return isDefaultTemplate;
    }

    public void setIsDefaultTemplate(String isDefaultTemplate) {
        this.isDefaultTemplate = isDefaultTemplate == null ? null : isDefaultTemplate.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}