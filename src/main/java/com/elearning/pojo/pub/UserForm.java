package com.elearning.pojo.pub;

import com.elearning.common.Tools;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author guoshen
 * @create 2019/7/22
 */
public class UserForm {
    private Integer operatorId;

    private String empCode;

    private Integer orgId;

    private Integer virOrgId;

    private String positionId;

    private String otel1;

    private String otel2;

    private String oemail;

    private String htel;

    private String haddress;

    private String zipCode;

    private String ptel1;

    private String ptel2;

    private String pemail;

    private String pid;

    private String birthDate;

    private String genderName;

    private Integer gender;

    private String party;

    private String degree;

    private String major;

    private String hobby;

    private String specialty;

    private String workExp;

    private String training;

    private String regDate;

    private String faxNo;

    private String mobileNo;

    private String bpno;

    private String createTime;

    private String lastModifyTime;

    private String empstatus;

    private String custcolumn;

    private Integer sortid;

    private String custnotice;

    private String custnoticename;

    private String orggrade;

    private String address;

    private Integer netdiskSize;

    private Integer defaultdiskSize;

    private String ismobileenc;

    private String emptype;

    private String sendDatachgNum;

    private String recDatachgNum;

    private String userId;

    private Integer isCtnEduRequire;

    //	对应于数据库中base64加密后的密码
    private String password;

    private String operatorName;

    private String isLocal;

    private Integer status;

    private String userIdcode;

    private String orgName;

    private String orgCode;     //组织机构编码

    private String orgSeq;

    private String outsiderOrgName;
    private String addTag;
    private Integer trainPointId;
    private String registerType;//区分云平台和普通研究所平台人员注册类型

    private String type;
    //	职称
    private String title;
    //  职务
    private String job;
    //	岗位
    private String position;

    private String suggestion;
    private Integer[] regUserFormList;
    private String headPic;

    private String emailPwd;
    private String emailHost;
    private Integer emailSetEnable;
    private Integer isTip;
    private Integer isKeyFigure;

    private Integer newEmployeeYear;

    /**
     * 存储用户的个性化logo(目前存储该学员所属租户的logo)
     */
    private String logo;

    // Constructors

    //  最后修改人
    private Integer lastModifyUserId;

    //密码修改时间
    private Date pwdModifyDate;

    public Date getPwdModifyDate() {
        return pwdModifyDate;
    }

    public void setPwdModifyDate(Date pwdModifyDate) {
        this.pwdModifyDate = pwdModifyDate;
    }

    public Integer getIsTip() {
        return isTip;
    }

    public void setIsTip(Integer isTip) {
        this.isTip = isTip;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer[] getRegUserFormList() {
        return regUserFormList;
    }

    public void setRegUserFormList(Integer[] regUserFormList) {
        this.regUserFormList = regUserFormList;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /** default constructor */
    public UserForm() {
    }

    public UserForm(EosorgTEmployee _eosorgTEmployee,EosOperator _tempOperator){
        if(_eosorgTEmployee!=null && _tempOperator != null) {
            
            this.setOperatorId(_eosorgTEmployee.getOperatorID());
            if (_eosorgTEmployee.getADDRESS() != null) this.setAddress(_eosorgTEmployee.getADDRESS());
            if (_eosorgTEmployee.getBirthDate() != null) try {
                this.setBirthDate(Tools.DateToString(_eosorgTEmployee.getBirthDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (_eosorgTEmployee.getBPNO() != null) this.setBpno(_eosorgTEmployee.getBPNO());
            if (_eosorgTEmployee.getCreateTime() != null) try {
                this.setCreateTime(Tools.DateToString(_eosorgTEmployee.getCreateTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (_eosorgTEmployee.getCUSTCOLUMN() != null) this.setCustcolumn(_eosorgTEmployee.getCUSTCOLUMN());
            if (_eosorgTEmployee.getDegree() != null) this.setDegree(_eosorgTEmployee.getDegree());
            if (_eosorgTEmployee.getEmpCode() != null) this.setEmpCode(_eosorgTEmployee.getEmpCode());
            if (_eosorgTEmployee.getFaxNO() != null) this.setFaxNo(_eosorgTEmployee.getFaxNO());
            if (_eosorgTEmployee.getGender() != null) this.setGender(_eosorgTEmployee.getGender());
            if (_eosorgTEmployee.getHAddress() != null) this.setHaddress(_eosorgTEmployee.getHAddress());
            if (_eosorgTEmployee.getHobby() != null) this.setHobby(_eosorgTEmployee.getHobby());
            if (_eosorgTEmployee.getOEmail() != null) this.setOemail(_eosorgTEmployee.getOEmail());
            if (_eosorgTEmployee.getHTel() != null) this.setHtel(_eosorgTEmployee.getHTel());
            if (_eosorgTEmployee.getOTel1() != null) this.setOtel1(_eosorgTEmployee.getOTel1());
            if (_eosorgTEmployee.getMobileNO() != null) this.setMobileNo(_eosorgTEmployee.getMobileNO());
            if(_eosorgTEmployee.getIsCtnEduRequire()!=null) this.setIsCtnEduRequire(_eosorgTEmployee.getIsCtnEduRequire());
            if (_eosorgTEmployee.getRegDate() != null) {
                try {
                    this.setRegDate(Tools.DateToString(_eosorgTEmployee.getRegDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (_eosorgTEmployee.getNewEmployeeYear() != null)
                this.setNewEmployeeYear(_eosorgTEmployee.getNewEmployeeYear());
            if (_eosorgTEmployee.getLastModifyTime() != null) {
                try {
                    this.setLastModifyTime(Tools.DateToString(_eosorgTEmployee.getLastModifyTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (_eosorgTEmployee.getTitle() != null) this.setTitle(_eosorgTEmployee.getTitle());
            if (_eosorgTEmployee.getJob() != null) this.setJob(_eosorgTEmployee.getJob());
            if (_eosorgTEmployee.getPosition() != null) this.setPosition(_eosorgTEmployee.getPosition());
            if (_eosorgTEmployee.getType() != null) this.setType(_eosorgTEmployee.getType());
            if (_eosorgTEmployee.getEmailHost() != null) this.setEmailHost(_eosorgTEmployee.getEmailHost());
            else this.setEmailHost("smtp.cnic.ac.cn");
            if (_eosorgTEmployee.getEmailPwd() != null) try {
                this.setEmailPwd(new String(new BASE64Decoder().decodeBuffer(_eosorgTEmployee.getEmailPwd())));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (_eosorgTEmployee.getEmailSetEnable() != null)
                this.setEmailSetEnable(_eosorgTEmployee.getEmailSetEnable());
            else this.setEmailSetEnable(0);//默认不启用个人邮箱发邮件
            if (_eosorgTEmployee.getIsKeyFigure() != null)
                this.setIsKeyFigure(_eosorgTEmployee.getIsKeyFigure());
            else this.setIsKeyFigure(0);//默认不是骨干人员
            if (_eosorgTEmployee.getOrgID() != null) this.setOrgId(_eosorgTEmployee.getOrgID());
            if (_eosorgTEmployee.getIsTip() != null) this.setIsTip(_eosorgTEmployee.getIsTip());

            EosorgTOrganization belongOrg = _eosorgTEmployee.getEosorgTOrganization();
            this.setOrgName(belongOrg.getOrgName());

            if (_tempOperator.getOperatorName() != null) this.setOperatorName(_tempOperator.getOperatorName());
            if (_tempOperator.getUserId() != null) this.setUserId(_tempOperator.getUserId());
            if (_tempOperator.getStatus() != null) {
                this.setStatus(_tempOperator.getStatus());
            }
            this.setPassword(_tempOperator.getPassword());
            this.setPwdModifyDate(_tempOperator.getPwdModifyDate());
        }
     }



    // Property accessors

    public Integer getOperatorId() {
        return this.operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getEmpCode() {
        return this.empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public Integer getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getPositionId() {
        return this.positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getOtel1() {
        return this.otel1;
    }

    public void setOtel1(String otel1) {
        this.otel1 = otel1;
    }

    public String getOtel2() {
        return this.otel2;
    }

    public void setOtel2(String otel2) {
        this.otel2 = otel2;
    }

    public String getOemail() {
        return this.oemail;
    }

    public void setOemail(String oemail) {
        this.oemail = oemail;
    }

    public String getHtel() {
        return this.htel;
    }

    public void setHtel(String htel) {
        this.htel = htel;
    }

    public String getHaddress() {
        return this.haddress;
    }

    public void setHaddress(String haddress) {
        this.haddress = haddress;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPtel1() {
        return this.ptel1;
    }

    public void setPtel1(String ptel1) {
        this.ptel1 = ptel1;
    }

    public String getPtel2() {
        return this.ptel2;
    }

    public void setPtel2(String ptel2) {
        this.ptel2 = ptel2;
    }

    public String getPemail() {
        return this.pemail;
    }

    public void setPemail(String pemail) {
        this.pemail = pemail;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getParty() {
        return this.party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getHobby() {
        return this.hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSpecialty() {
        return this.specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getWorkExp() {
        return this.workExp;
    }

    public void setWorkExp(String workExp) {
        this.workExp = workExp;
    }

    public String getTraining() {
        return this.training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getRegDate() {
        return this.regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getFaxNo() {
        return this.faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getBpno() {
        return this.bpno;
    }

    public void setBpno(String bpno) {
        this.bpno = bpno;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getEmpstatus() {
        return this.empstatus;
    }

    public void setEmpstatus(String empstatus) {
        this.empstatus = empstatus;
    }

    public String getCustcolumn() {
        return this.custcolumn;
    }

    public void setCustcolumn(String custcolumn) {
        this.custcolumn = custcolumn;
    }

    public Integer getSortid() {
        return this.sortid;
    }

    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    public String getCustnotice() {
        return this.custnotice;
    }

    public void setCustnotice(String custnotice) {
        this.custnotice = custnotice;
    }

    public String getCustnoticename() {
        return this.custnoticename;
    }

    public void setCustnoticename(String custnoticename) {
        this.custnoticename = custnoticename;
    }

    public String getOrggrade() {
        return this.orggrade;
    }

    public void setOrggrade(String orggrade) {
        this.orggrade = orggrade;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNetdiskSize() {
        return this.netdiskSize;
    }

    public void setNetdiskSize(Integer netdiskSize) {
        this.netdiskSize = netdiskSize;
    }

    public Integer getDefaultdiskSize() {
        return this.defaultdiskSize;
    }

    public void setDefaultdiskSize(Integer defaultdiskSize) {
        this.defaultdiskSize = defaultdiskSize;
    }

    public String getIsmobileenc() {
        return this.ismobileenc;
    }

    public void setIsmobileenc(String ismobileenc) {
        this.ismobileenc = ismobileenc;
    }

    public String getEmptype() {
        return this.emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }

    public String getSendDatachgNum() {
        return this.sendDatachgNum;
    }

    public void setSendDatachgNum(String sendDatachgNum) {
        this.sendDatachgNum = sendDatachgNum;
    }

    public String getRecDatachgNum() {
        return this.recDatachgNum;
    }

    public void setRecDatachgNum(String recDatachgNum) {
        this.recDatachgNum = recDatachgNum;
    }

    public String getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(String isLocal) {
        this.isLocal = isLocal;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdcode() {
        return userIdcode;
    }

    public void setUserIdcode(String userIdcode) {
        this.userIdcode = userIdcode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddTag() {
        return addTag;
    }

    public void setAddTag(String addTag) {
        this.addTag = addTag;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getOutsiderOrgName() {
        return outsiderOrgName;
    }

    public void setOutsiderOrgName(String outsiderOrgName) {
        this.outsiderOrgName = outsiderOrgName;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public Integer getTrainPointId() {
        return trainPointId;
    }

    public void setTrainPointId(Integer trainPointId) {
        this.trainPointId = trainPointId;
    }

    public Integer getVirOrgId() {
        return virOrgId;
    }

    public void setVirOrgId(Integer virOrgId) {
        this.virOrgId = virOrgId;
    }

    public String getEmailPwd() {
        return emailPwd;
    }

    public void setEmailPwd(String emailPwd) {
        this.emailPwd = emailPwd;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public Integer getEmailSetEnable() {
        return emailSetEnable;
    }

    public void setEmailSetEnable(Integer emailSetEnable) {
        this.emailSetEnable = emailSetEnable;
    }

    public Integer getIsKeyFigure() {
        return isKeyFigure;
    }

    public void setIsKeyFigure(Integer isKeyFigure) {
        this.isKeyFigure = isKeyFigure;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getNewEmployeeYear() {
        return newEmployeeYear;
    }

    public void setNewEmployeeYear(Integer newEmployeeYear) {
        this.newEmployeeYear = newEmployeeYear;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(Integer lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public String getOrgSeq() {
        return orgSeq;
    }

    public void setOrgSeq(String orgSeq) {
        this.orgSeq = orgSeq;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public Integer getIsCtnEduRequire() {
        return isCtnEduRequire;
    }

    public void setIsCtnEduRequire(Integer isCtnEduRequire) {
        this.isCtnEduRequire = isCtnEduRequire;
    }
}
