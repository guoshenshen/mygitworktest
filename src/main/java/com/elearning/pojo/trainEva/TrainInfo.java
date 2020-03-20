package com.elearning.pojo.trainEva;

public class TrainInfo {
    private Double id;

    private Double item_Type;

    public TrainInfo(Double id, Double item_Type) {
        this.id = id;
        this.item_Type = item_Type;
    }

    public TrainInfo() {
        super();
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getItem_Type() {
        return item_Type;
    }

    public void setItem_Type(Double item_Type) {
        this.item_Type = item_Type;
    }
}