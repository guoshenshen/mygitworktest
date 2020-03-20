package com.elearning.pojo.info;

import java.util.Date;

public class InfoReceiveRecord {
    private Integer ID;

    private Integer sentMessageID;

    private Integer receiverID;

    private Date viewTime;

    private String viewStat;

    public InfoReceiveRecord(Integer ID, Integer sentMessageID, Integer receiverID, Date viewTime, String viewStat) {
        this.ID = ID;
        this.sentMessageID = sentMessageID;
        this.receiverID = receiverID;
        this.viewTime = viewTime;
        this.viewStat = viewStat;
    }

    public InfoReceiveRecord() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getSentMessageID() {
        return sentMessageID;
    }

    public void setSentMessageID(Integer sentMessageID) {
        this.sentMessageID = sentMessageID;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Integer receiverID) {
        this.receiverID = receiverID;
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }

    public String getViewStat() {
        return viewStat;
    }

    public void setViewStat(String viewStat) {
        this.viewStat = viewStat == null ? null : viewStat.trim();
    }
}