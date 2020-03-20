package com.elearning.pojo.info;

import java.util.Date;

public class InfoReceiveMessage {
    private Integer ID;

    private Integer sentMessageID;

    private Integer receiverRecordID;

    private Integer senderID;

    private Integer receiverID;

    private String title;

    private Date sendTime;

    private String viewStat;

    private String content;

    public InfoReceiveMessage(Integer ID, Integer sentMessageID, Integer receiverRecordID, Integer senderID, Integer receiverID, String title, Date sendTime, String viewStat, String content) {
        this.ID = ID;
        this.sentMessageID = sentMessageID;
        this.receiverRecordID = receiverRecordID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.title = title;
        this.sendTime = sendTime;
        this.viewStat = viewStat;
        this.content = content;
    }

    public InfoReceiveMessage() {
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

    public Integer getReceiverRecordID() {
        return receiverRecordID;
    }

    public void setReceiverRecordID(Integer receiverRecordID) {
        this.receiverRecordID = receiverRecordID;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public void setSenderID(Integer senderID) {
        this.senderID = senderID;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Integer receiverID) {
        this.receiverID = receiverID;
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