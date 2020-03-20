package com.elearning.pojo.recommend;

public class ReceiverItem {
    private Integer id;

    private Integer operatorId;

    private Integer recommendMailId;

    private Long itemId;

    public ReceiverItem(Integer id, Integer operatorId, Integer recommendMailId, Long itemId) {
        this.id = id;
        this.operatorId = operatorId;
        this.recommendMailId = recommendMailId;
        this.itemId = itemId;
    }

    public ReceiverItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getRecommendMailId() {
        return recommendMailId;
    }

    public void setRecommendMailId(Integer recommendMailId) {
        this.recommendMailId = recommendMailId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}