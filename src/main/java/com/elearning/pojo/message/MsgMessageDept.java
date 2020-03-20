package com.elearning.pojo.message;

public class MsgMessageDept {
    private Integer ID;

    private Integer msgID;

    private Integer deptID;

    public MsgMessageDept(Integer ID, Integer msgID, Integer deptID) {
        this.ID = ID;
        this.msgID = msgID;
        this.deptID = deptID;
    }

    public MsgMessageDept() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getMsgID() {
        return msgID;
    }

    public void setMsgID(Integer msgID) {
        this.msgID = msgID;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }
}