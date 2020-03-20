package com.elearning.pojo.systemManage;

public class PageSetting {
    private Integer operatorID;

    private Integer pageSize;

    public PageSetting(Integer operatorID, Integer pageSize) {
        this.operatorID = operatorID;
        this.pageSize = pageSize;
    }

    public PageSetting() {
        super();
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}