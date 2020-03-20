package com.elearning.pojo.onlinetraining;

import java.util.Date;

public class TrainSign {

    private Integer id;

    private Integer train_id;

    private Date start;

    private String startS;

    private Date end;

    private String endS;

    private Integer hour;

    private Integer state;

    private String name;

    private String info;

    private Integer ewmType;

    private Date startClass;

    private String startClassS;

    private Date endClass;

    private String endClassS;

    private String code;

    private Integer signCount;

    public TrainSign(Integer id, Integer train_id, Date start, Date end, Integer hour, Integer state, String name, String info, Integer ewmType, Date startClass, Date endClass, String code) {
        this.id = id;
        this.train_id = train_id;
        this.start = start;
        this.end = end;
        this.hour = hour;
        this.state = state;
        this.name = name;
        this.info = info;
        this.ewmType = ewmType;
        this.startClass = startClass;
        this.endClass = endClass;
        this.code = code;
    }

    public TrainSign() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrain_id() {
        return train_id;
    }

    public void setTrain_id(Integer train_id) {
        this.train_id = train_id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getEwmType() {
        return ewmType;
    }

    public void setEwmType(Integer ewmType) {
        this.ewmType = ewmType;
    }

    public Date getStartClass() {
        return startClass;
    }

    public void setStartClass(Date startClass) {
        this.startClass = startClass;
    }

    public Date getEndClass() {
        return endClass;
    }

    public void setEndClass(Date endClass) {
        this.endClass = endClass;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getStartS() {
        return startS;
    }

    public void setStartS(String startS) {
        this.startS = startS;
    }

    public String getEndS() {
        return endS;
    }

    public void setEndS(String endS) {
        this.endS = endS;
    }

    public String getStartClassS() {
        return startClassS;
    }

    public void setStartClassS(String startClassS) {
        this.startClassS = startClassS;
    }

    public String getEndClassS() {
        return endClassS;
    }

    public void setEndClassS(String endClassS) {
        this.endClassS = endClassS;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }
}