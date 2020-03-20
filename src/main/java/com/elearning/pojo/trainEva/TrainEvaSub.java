package com.elearning.pojo.trainEva;

public class TrainEvaSub {
    private Integer ID;

    private Integer subItemId;

    private Integer tenantId;

    private Double originalMark;

    private Double reviewMark;

    private String comment;

    private Integer parentItemId;

    private Integer itemId;

    public TrainEvaSub(Integer ID, Integer subItemId, Integer tenantId, Double originalMark, Double reviewMark, String comment, Integer parentItemId, Integer itemId) {
        this.ID = ID;
        this.subItemId = subItemId;
        this.tenantId = tenantId;
        this.originalMark = originalMark;
        this.reviewMark = reviewMark;
        this.comment = comment;
        this.parentItemId = parentItemId;
        this.itemId = itemId;
    }

    public TrainEvaSub() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getSubItemId() {
        return subItemId;
    }

    public void setSubItemId(Integer subItemId) {
        this.subItemId = subItemId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Double getOriginalMark() {
        return originalMark;
    }

    public void setOriginalMark(Double originalMark) {
        this.originalMark = originalMark;
    }

    public Double getReviewMark() {
        return reviewMark;
    }

    public void setReviewMark(Double reviewMark) {
        this.reviewMark = reviewMark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(Integer parentItemId) {
        this.parentItemId = parentItemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}