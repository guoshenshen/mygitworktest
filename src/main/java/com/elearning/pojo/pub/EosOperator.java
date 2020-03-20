package com.elearning.pojo.pub;

import com.elearning.common.Constants;
import sun.misc.BASE64Encoder;

import java.util.Date;
import java.util.List;

public class EosOperator {
    private Integer operatorId;

    private String userId;

    private String password;

    private String operatorName;

    private String isLocal;

    private Integer status;

    private String user_idcode;

    private String major;

    private String validataCode;

    private Date validDate;

    private Date statusModifyDate;

    private Integer isSuperAdmin;

    private Date pwdModifyDate;

    private EosorgTEmployee eosorgTEmployee;

    private Integer tenantId;

    private EosorgTOrganization eosOrganization;

    /**
     * 学员所具有角色标识，该字段不保存到数据库记录中，学员登录后查询userrole表获取该值后存储到operator内存对象中
     */
    private List<Integer> roleIdcode;

    public void setEosOrganization(EosorgTOrganization eosOrganization) {
        this.eosOrganization = eosOrganization;
    }

    public void setRoleIdcode(List<Integer> roleIdcode) {
        this.roleIdcode = roleIdcode;
    }

    public EosorgTOrganization getEosOrganization() {
        return eosOrganization;
    }

    public List<Integer> getRoleIdcode() {
        return roleIdcode;
    }

    public EosOperator(Integer operatorID, String userId, String password, String operatorName, String isLocal, Integer status, String user_idcode, String major, String validataCode, Date validDate, Date statusModifyDate, Integer isSuperAdmin, Date pwdModifyDate) {
        this.operatorId = operatorID;
        this.userId = userId;
        this.password = password;
        this.operatorName = operatorName;
        this.isLocal = isLocal;
        this.status = status;
        this.user_idcode = user_idcode;
        this.major = major;
        this.validataCode = validataCode;
        this.validDate = validDate;
        this.statusModifyDate = statusModifyDate;
        this.isSuperAdmin = isSuperAdmin;
        this.pwdModifyDate = pwdModifyDate;
    }

    public EosOperator(UserForm userForm){
        if(userForm.getStatus()!=null){
            this.setStatus(userForm.getStatus());
        }
        else{
            this.setStatus(1);
        }
        this.setOperatorId(userForm.getOperatorId());
        this.setUserId(userForm.getUserId());
        this.setOperatorName(userForm.getOperatorName());
        String password=userForm.getPassword()==null?this.getPassword():userForm.getPassword();
        Date pwdModifyDate = userForm.getPwdModifyDate()==null?this.getPwdModifyDate():userForm.getPwdModifyDate();
        if(password==null){
            String pwd = Constants.password;
            password=new BASE64Encoder().encodeBuffer(pwd.getBytes()).replace("\r", "").replace("\n", "");

        }
        this.setPassword(password);
        this.setPwdModifyDate(pwdModifyDate);
    }


    public EosOperator() {
        super();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorID) {
        this.operatorId = operatorID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(String isLocal) {
        this.isLocal = isLocal == null ? null : isLocal.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUser_idcode() {
        return user_idcode;
    }

    public void setUser_idcode(String user_idcode) {
        this.user_idcode = user_idcode == null ? null : user_idcode.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getValidataCode() {
        return validataCode;
    }

    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode == null ? null : validataCode.trim();
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getStatusModifyDate() {
        return statusModifyDate;
    }

    public void setStatusModifyDate(Date statusModifyDate) {
        this.statusModifyDate = statusModifyDate;
    }

    public Integer getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(Integer isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public Date getPwdModifyDate() {
        return pwdModifyDate;
    }

    public void setPwdModifyDate(Date pwdModifyDate) {
        this.pwdModifyDate = pwdModifyDate;
    }

    public EosorgTEmployee getEosorgTEmployee() {
        return eosorgTEmployee;
    }

    public void setEosorgTEmployee(EosorgTEmployee eosorgTEmployee) {
        this.eosorgTEmployee = eosorgTEmployee;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}