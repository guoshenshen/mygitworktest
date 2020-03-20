package com.elearning.pojo.onlinetraining;

import java.util.Date;

public class TrainSignWithBLOBs extends TrainSign {
    private String ewm;

    private String url;

    public TrainSignWithBLOBs(Integer id, Integer train_id, Date start, Date end, Integer hour, Integer state, String name, String info, Integer ewmType, Date startClass, Date endClass, String code, String ewm, String url) {
        super(id, train_id, start, end, hour, state, name, info, ewmType, startClass, endClass, code);
        this.ewm = ewm;
        this.url = url;
    }

    public TrainSignWithBLOBs() {
        super();
    }

    public String getEwm() {
        return ewm;
    }

    public void setEwm(String ewm) {
        this.ewm = ewm == null ? null : ewm.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}