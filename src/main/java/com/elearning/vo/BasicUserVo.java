package com.elearning.vo;

/**
 * 〈<br>
 * 〈〉
 *  封装用户的基本信息
 * @author lxx
 * @create 2019/7/17 9:51
 */
public class BasicUserVo {

    private String operatorName;
    private String otel1;
    private String oemail;
    private Integer operatorId;
    private String orgName;
    private String mobileNo;
    private String userId;
    private String address;
    private Integer gender;
    private Integer tenantId;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOtel1() {
        return otel1;
    }

    public void setOtel1(String otel1) {
        this.otel1 = otel1;
    }

    public String getOemail() {
        return oemail;
    }

    public void setOemail(String oemail) {
        this.oemail = oemail;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}
