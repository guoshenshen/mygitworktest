package com.elearning.pojo.recommend;

import java.util.Date;

public class ReceiverForRecommend {
    private Long id;

    private Long recommendMailId;

    private Integer operatorId;

    private Integer hasSend;

    private Integer hasOpened;

    private Integer linkOpenNum;

    private Date creatTime;

    private Date sendTime;

    public ReceiverForRecommend(Long id, Long recommendMailId, Integer operatorId, Integer hasSend, Integer hasOpened, Integer linkOpenNum, Date creatTime, Date sendTime) {
        this.id = id;
        this.recommendMailId = recommendMailId;
        this.operatorId = operatorId;
        this.hasSend = hasSend;
        this.hasOpened = hasOpened;
        this.linkOpenNum = linkOpenNum;
        this.creatTime = creatTime;
        this.sendTime = sendTime;
    }

    public ReceiverForRecommend() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecommendMailId() {
        return recommendMailId;
    }

    public void setRecommendMailId(Long recommendMailId) {
        this.recommendMailId = recommendMailId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getHasSend() {
        return hasSend;
    }

    public void setHasSend(Integer hasSend) {
        this.hasSend = hasSend;
    }

    public Integer getHasOpened() {
        return hasOpened;
    }

    public void setHasOpened(Integer hasOpened) {
        this.hasOpened = hasOpened;
    }

    public Integer getLinkOpenNum() {
        return linkOpenNum;
    }

    public void setLinkOpenNum(Integer linkOpenNum) {
        this.linkOpenNum = linkOpenNum;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}