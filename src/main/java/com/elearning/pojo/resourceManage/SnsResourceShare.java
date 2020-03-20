package com.elearning.pojo.resourceManage;

import java.util.Date;

public class SnsResourceShare {
    private Long id;

    private Long resourceId;

    private Integer seq;

    private Date shareTime;

    private Integer userId;

    public SnsResourceShare(Long id, Long resourceId, Integer seq, Date shareTime, Integer userId) {
        this.id = id;
        this.resourceId = resourceId;
        this.seq = seq;
        this.shareTime = shareTime;
        this.userId = userId;
    }

    public SnsResourceShare() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}