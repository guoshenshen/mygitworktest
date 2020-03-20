package com.elearning.pojo.mixtraining;

public class MtMixTrainingTipItem {
    private Integer id;

    private String itemName;

    private Integer tipDays;

    private Integer isBeforeOrAfter;

    private Integer tipId;

    private String isDefalut;

    private String remarks;

    private Integer seqNum;

    private String status;

    private Integer operatorId;

    public MtMixTrainingTipItem(Integer id, String itemName, Integer tipDays, Integer isBeforeOrAfter, Integer tipId, String isDefalut, String remarks, Integer seqNum, String status, Integer operatorId) {
        this.id = id;
        this.itemName = itemName;
        this.tipDays = tipDays;
        this.isBeforeOrAfter = isBeforeOrAfter;
        this.tipId = tipId;
        this.isDefalut = isDefalut;
        this.remarks = remarks;
        this.seqNum = seqNum;
        this.status = status;
        this.operatorId = operatorId;
    }

    public MtMixTrainingTipItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getTipDays() {
        return tipDays;
    }

    public void setTipDays(Integer tipDays) {
        this.tipDays = tipDays;
    }

    public Integer getIsBeforeOrAfter() {
        return isBeforeOrAfter;
    }

    public void setIsBeforeOrAfter(Integer isBeforeOrAfter) {
        this.isBeforeOrAfter = isBeforeOrAfter;
    }

    public Integer getTipId() {
        return tipId;
    }

    public void setTipId(Integer tipId) {
        this.tipId = tipId;
    }

    public String getIsDefalut() {
        return isDefalut;
    }

    public void setIsDefalut(String isDefalut) {
        this.isDefalut = isDefalut == null ? null : isDefalut.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}