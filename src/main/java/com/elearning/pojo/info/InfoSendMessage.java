package com.elearning.pojo.info;

import java.util.Date;

public class InfoSendMessage {
    private Integer ID;

    private Integer senderID;

    private String title;

    private Date sendTime;

    private String viewStat;

    private String content;

    public InfoSendMessage(Integer ID, Integer senderID, String title, Date sendTime, String viewStat, String content) {
        this.ID = ID;
        this.senderID = senderID;
        this.title = title;
        this.sendTime = sendTime;
        this.viewStat = viewStat;
        this.content = content;
    }

    public InfoSendMessage() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public void setSenderID(Integer senderID) {
        this.senderID = senderID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getViewStat() {
        return viewStat;
    }

    public void setViewStat(String viewStat) {
        this.viewStat = viewStat == null ? null : viewStat.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}