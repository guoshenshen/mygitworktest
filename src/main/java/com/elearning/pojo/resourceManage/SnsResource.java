package com.elearning.pojo.resourceManage;

import java.util.Date;

public class SnsResource {
    private Long resourceId;

    private String resourceName;

    private Integer resourceType;

    private Integer ownerId;

    private String ownerName;

    private Integer rightScope;

    private Date ownerReleaseTime;

    private Integer password;

    private Integer status;

    private String path;

    private Long viewTimes;

    private Long shareTimes;

    private Integer resourceCategoryId;

    private Integer resourceScore;

    private Integer loveCount;

    public SnsResource(Long resourceId, String resourceName, Integer resourceType, Integer ownerId, String ownerName, Integer rightScope, Date ownerReleaseTime, Integer password, Integer status, String path, Long viewTimes, Long shareTimes, Integer resourceCategoryId, Integer resourceScore, Integer loveCount) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.rightScope = rightScope;
        this.ownerReleaseTime = ownerReleaseTime;
        this.password = password;
        this.status = status;
        this.path = path;
        this.viewTimes = viewTimes;
        this.shareTimes = shareTimes;
        this.resourceCategoryId = resourceCategoryId;
        this.resourceScore = resourceScore;
        this.loveCount = loveCount;
    }

    public SnsResource() {
        super();
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public Integer getRightScope() {
        return rightScope;
    }

    public void setRightScope(Integer rightScope) {
        this.rightScope = rightScope;
    }

    public Date getOwnerReleaseTime() {
        return ownerReleaseTime;
    }

    public void setOwnerReleaseTime(Date ownerReleaseTime) {
        this.ownerReleaseTime = ownerReleaseTime;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Long getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Long viewTimes) {
        this.viewTimes = viewTimes;
    }

    public Long getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(Long shareTimes) {
        this.shareTimes = shareTimes;
    }

    public Integer getResourceCategoryId() {
        return resourceCategoryId;
    }

    public void setResourceCategoryId(Integer resourceCategoryId) {
        this.resourceCategoryId = resourceCategoryId;
    }

    public Integer getResourceScore() {
        return resourceScore;
    }

    public void setResourceScore(Integer resourceScore) {
        this.resourceScore = resourceScore;
    }

    public Integer getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(Integer loveCount) {
        this.loveCount = loveCount;
    }
}