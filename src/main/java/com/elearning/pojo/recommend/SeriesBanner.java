package com.elearning.pojo.recommend;

public class SeriesBanner {
    private Integer id;

    private Integer seriesId;

    private Integer bannerId;

    private Byte type;

    private Byte orderweight;

    public SeriesBanner(Integer id, Integer seriesId, Integer bannerId, Byte type, Byte orderweight) {
        this.id = id;
        this.seriesId = seriesId;
        this.bannerId = bannerId;
        this.type = type;
        this.orderweight = orderweight;
    }

    public SeriesBanner() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getOrderweight() {
        return orderweight;
    }

    public void setOrderweight(Byte orderweight) {
        this.orderweight = orderweight;
    }
}