package com.elearning.pojo.recommend;

public class SeriesItem {
    private Long id;

    private Integer seriesId;

    private Long itemId;

    private Integer itemType;

    private Long orderWeight;

    public SeriesItem(Long id, Integer seriesId, Long itemId, Integer itemType, Long orderWeight) {
        this.id = id;
        this.seriesId = seriesId;
        this.itemId = itemId;
        this.itemType = itemType;
        this.orderWeight = orderWeight;
    }

    public SeriesItem() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Long getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(Long orderWeight) {
        this.orderWeight = orderWeight;
    }
}