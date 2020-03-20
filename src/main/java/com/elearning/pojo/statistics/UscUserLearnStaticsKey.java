package com.elearning.pojo.statistics;

public class UscUserLearnStaticsKey {
    private Integer operatorId;

    private Integer year;

    private Integer month;

    public UscUserLearnStaticsKey(Integer operatorId, Integer year, Integer month) {
        this.operatorId = operatorId;
        this.year = year;
        this.month = month;
    }

    public UscUserLearnStaticsKey() {
        super();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}