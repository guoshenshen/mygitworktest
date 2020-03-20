package com.elearning.pojo.pub;

public class QRLogin {
    private Integer id;

    private Long randNum;

    private Integer operatorId;

    private Boolean isChecked;

    public QRLogin(Integer id, Long randNum, Integer operatorId, Boolean isChecked) {
        this.id = id;
        this.randNum = randNum;
        this.operatorId = operatorId;
        this.isChecked = isChecked;
    }

    public QRLogin() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getRandNum() {
        return randNum;
    }

    public void setRandNum(Long randNum) {
        this.randNum = randNum;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
}