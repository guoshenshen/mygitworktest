package com.elearning.pojo.forum;

import java.util.Date;

public class FrmReply {
    private Integer replyId;

    private Date createTime;

    private String content;

    private Integer reFloor;

    private String ipCreated;

    private String title;

    private Integer operatorId;

    private Integer threadId;

    private Integer tenantId;

    private String operatorName;

    private Integer boardId;

    public FrmReply(Integer replyId, Date createTime, String content, Integer reFloor, String ipCreated, String title, Integer operatorId, Integer threadId, Integer tenantId, String operatorName, Integer boardId) {
        this.replyId = replyId;
        this.createTime = createTime;
        this.content = content;
        this.reFloor = reFloor;
        this.ipCreated = ipCreated;
        this.title = title;
        this.operatorId = operatorId;
        this.threadId = threadId;
        this.tenantId = tenantId;
        this.operatorName = operatorName;
        this.boardId = boardId;
    }

    public FrmReply() {
        super();
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getReFloor() {
        return reFloor;
    }

    public void setReFloor(Integer reFloor) {
        this.reFloor = reFloor;
    }

    public String getIpCreated() {
        return ipCreated;
    }

    public void setIpCreated(String ipCreated) {
        this.ipCreated = ipCreated == null ? null : ipCreated.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }
}