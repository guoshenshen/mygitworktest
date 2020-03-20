package com.elearning.pojo.forum;

public class FrmBoardAdminKey {
    private Integer boardId;

    private Integer operatorId;

    public FrmBoardAdminKey(Integer boardId, Integer operatorId) {
        this.boardId = boardId;
        this.operatorId = operatorId;
    }

    public FrmBoardAdminKey() {
        super();
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}