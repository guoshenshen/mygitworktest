package com.elearning.pojo.forum;

public class FrmBoardAdmin extends FrmBoardAdminKey {
    private String operatorName;

    public FrmBoardAdmin(Integer boardId, Integer operatorId, String operatorName) {
        super(boardId, operatorId);
        this.operatorName = operatorName;
    }

    public FrmBoardAdmin() {
        super();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }
}