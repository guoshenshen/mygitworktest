package com.elearning.pojo.mixtraining;

public class MtMixTrainAndAddrBook {
    private Integer ID;

    private Integer trainId;

    private Integer addrbookId;

    public MtMixTrainAndAddrBook(Integer ID, Integer trainId, Integer addrbookId) {
        this.ID = ID;
        this.trainId = trainId;
        this.addrbookId = addrbookId;
    }

    public MtMixTrainAndAddrBook() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getAddrbookId() {
        return addrbookId;
    }

    public void setAddrbookId(Integer addrbookId) {
        this.addrbookId = addrbookId;
    }
}