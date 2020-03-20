package com.elearning.pojo.integralTask;

import java.util.Date;

public class IntegralRule {
    private Long id;

    private String itemCode;

    private String itemName;

    private String itemCategoryCode;

    private Integer score;

    private String creator;

    private Date createTime;

    private String updator;

    private Date updateTime;

    private String limitCount;

    private String limitUnit;

    private String hasLimit;

    private String detail;

    private String status;

    private Long creatorId;

    private Long updatorId;

    public IntegralRule(Long id, String itemCode, String itemName, String itemCategoryCode, Integer score, String creator, Date createTime, String updator, Date updateTime, String limitCount, String limitUnit, String hasLimit, String detail, String status, Long creatorId, Long updatorId) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemCategoryCode = itemCategoryCode;
        this.score = score;
        this.creator = creator;
        this.createTime = createTime;
        this.updator = updator;
        this.updateTime = updateTime;
        this.limitCount = limitCount;
        this.limitUnit = limitUnit;
        this.hasLimit = hasLimit;
        this.detail = detail;
        this.status = status;
        this.creatorId = creatorId;
        this.updatorId = updatorId;
    }

    public IntegralRule() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemCategoryCode() {
        return itemCategoryCode;
    }

    public void setItemCategoryCode(String itemCategoryCode) {
        this.itemCategoryCode = itemCategoryCode == null ? null : itemCategoryCode.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(String limitCount) {
        this.limitCount = limitCount == null ? null : limitCount.trim();
    }

    public String getLimitUnit() {
        return limitUnit;
    }

    public void setLimitUnit(String limitUnit) {
        this.limitUnit = limitUnit == null ? null : limitUnit.trim();
    }

    public String getHasLimit() {
        return hasLimit;
    }

    public void setHasLimit(String hasLimit) {
        this.hasLimit = hasLimit == null ? null : hasLimit.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }
}