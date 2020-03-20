package com.elearning.pojo.mixtraining;

public class MtMixTrainScheduleTrain {
    private Integer id;

    private Integer scheduleId;

    private Integer trainId;

    private String status;

    public MtMixTrainScheduleTrain(Integer id, Integer scheduleId, Integer trainId, String status) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.trainId = trainId;
        this.status = status;
    }

    public MtMixTrainScheduleTrain() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}