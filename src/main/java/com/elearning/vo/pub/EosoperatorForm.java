package com.elearning.vo.pub;

import com.elearning.pojo.pub.EosOperator;

public class EosoperatorForm {

    private Integer operatorId;

    private String userId;

    private String password;

    private String operatorName;

    private String isLocal;

    private Integer status;

    private String userIdcode;

    public EosoperatorForm(){}

    public EosoperatorForm(EosOperator operator){
        this.setIsLocal(operator.getIsLocal());
        this.setOperatorId(operator.getOperatorId());
        this.setOperatorName(operator.getOperatorName());
        this.setPassword(operator.getPassword());
        this.setStatus(operator.getStatus());
        this.setUserId(operator.getUserId());
        this.setUserIdcode(operator.getUser_idcode());
    }


    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(String isLocal) {
        this.isLocal = isLocal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserIdcode() {
        return userIdcode;
    }

    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode;
    }
}
