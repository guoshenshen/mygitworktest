package com.elearning.pojo.forum;

import java.util.Date;

public class FrmThread {
    private Integer threadId;

    private Date createTime;

    private Date dateLastReplied;

    private Integer hit;

    private String ipCreated;

    private Integer readonly;

    private Integer replyCount;

    private String title;

    private Integer topped;

    private Integer operatorId;

    private String operatorName;

    private Integer lastRepliedOperatorId;

    private Integer boardId;

    private Integer tenantId;

    private String lastRepliedOperatorName;

    private String content;

    public FrmThread(Integer threadId, Date createTime, Date dateLastReplied, Integer hit, String ipCreated, Integer readonly, Integer replyCount, String title, Integer topped, Integer operatorId, String operatorName, Integer lastRepliedOperatorId, Integer boardId, Integer tenantId, String lastRepliedOperatorName, String content) {
        this.threadId = threadId;
        this.createTime = createTime;
        this.dateLastReplied = dateLastReplied;
        this.hit = hit;
        this.ipCreated = ipCreated;
        this.readonly = readonly;
        this.replyCount = replyCount;
        this.title = title;
        this.topped = topped;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.lastRepliedOperatorId = lastRepliedOperatorId;
        this.boardId = boardId;
        this.tenantId = tenantId;
        this.lastRepliedOperatorName = lastRepliedOperatorName;
        this.content = content;
    }

    public FrmThread() {
        super();
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDateLastReplied() {
        return dateLastReplied;
    }

    public void setDateLastReplied(Date dateLastReplied) {
        this.dateLastReplied = dateLastReplied;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public String getIpCreated() {
        return ipCreated;
    }

    public void setIpCreated(String ipCreated) {
        this.ipCreated = ipCreated == null ? null : ipCreated.trim();
    }

    public Integer getReadonly() {
        return readonly;
    }

    public void setReadonly(Integer readonly) {
        this.readonly = readonly;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getTopped() {
        return topped;
    }

    public void setTopped(Integer topped) {
        this.topped = topped;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Integer getLastRepliedOperatorId() {
        return lastRepliedOperatorId;
    }

    public void setLastRepliedOperatorId(Integer lastRepliedOperatorId) {
        this.lastRepliedOperatorId = lastRepliedOperatorId;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getLastRepliedOperatorName() {
        return lastRepliedOperatorName;
    }

    public void setLastRepliedOperatorName(String lastRepliedOperatorName) {
        this.lastRepliedOperatorName = lastRepliedOperatorName == null ? null : lastRepliedOperatorName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}