package com.elearning.pojo.pub;

public class UserVOrganizationKey {
    private Integer operatorID;

    private Integer orgID;

    public UserVOrganizationKey(Integer operatorID, Integer orgID) {
        this.operatorID = operatorID;
        this.orgID = orgID;
    }

    public UserVOrganizationKey() {
        super();
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }
}