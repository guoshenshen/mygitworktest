package com.elearning.pojo.trainEva;

import java.util.Date;

public class TrainQrCode {
    private Integer id;

    private Integer trainId;

    private Integer operatorId;

    private Date starttime;

    private Date endtime;

    private Integer period;

    private Double classhour;

    private Date ctime;

    private Double lat;

    private Double lng;

    public TrainQrCode(Integer id, Integer trainId, Integer operatorId, Date starttime, Date endtime, Integer period, Double classhour, Date ctime, Double lat, Double lng) {
        this.id = id;
        this.trainId = trainId;
        this.operatorId = operatorId;
        this.starttime = starttime;
        this.endtime = endtime;
        this.period = period;
        this.classhour = classhour;
        this.ctime = ctime;
        this.lat = lat;
        this.lng = lng;
    }

    public TrainQrCode() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getClasshour() {
        return classhour;
    }

    public void setClasshour(Double classhour) {
        this.classhour = classhour;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}