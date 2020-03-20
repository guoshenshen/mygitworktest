package com.elearning.pojo.forum;

import java.util.Date;

public class FrmBoard {
    private Integer boardId;

    private Date createTime;

    private Integer editable;

    private String description;

    private String boardName;

    private Integer replyCount;

    private Integer threadCount;

    private Integer parentBoardId;

    private Integer lastReplyId;

    private Integer lastThreadId;

    private Integer tenantId;

    private Integer boardSeq;

    public FrmBoard(Integer boardId, Date createTime, Integer editable, String description, String boardName, Integer replyCount, Integer threadCount, Integer parentBoardId, Integer lastReplyId, Integer lastThreadId, Integer tenantId, Integer boardSeq) {
        this.boardId = boardId;
        this.createTime = createTime;
        this.editable = editable;
        this.description = description;
        this.boardName = boardName;
        this.replyCount = replyCount;
        this.threadCount = threadCount;
        this.parentBoardId = parentBoardId;
        this.lastReplyId = lastReplyId;
        this.lastThreadId = lastThreadId;
        this.tenantId = tenantId;
        this.boardSeq = boardSeq;
    }

    public FrmBoard() {
        super();
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEditable() {
        return editable;
    }

    public void setEditable(Integer editable) {
        this.editable = editable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName == null ? null : boardName.trim();
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public Integer getParentBoardId() {
        return parentBoardId;
    }

    public void setParentBoardId(Integer parentBoardId) {
        this.parentBoardId = parentBoardId;
    }

    public Integer getLastReplyId() {
        return lastReplyId;
    }

    public void setLastReplyId(Integer lastReplyId) {
        this.lastReplyId = lastReplyId;
    }

    public Integer getLastThreadId() {
        return lastThreadId;
    }

    public void setLastThreadId(Integer lastThreadId) {
        this.lastThreadId = lastThreadId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(Integer boardSeq) {
        this.boardSeq = boardSeq;
    }
}