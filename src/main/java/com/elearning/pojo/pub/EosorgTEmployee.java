package com.elearning.pojo.pub;

import com.elearning.common.Tools;
import sun.misc.BASE64Encoder;

import java.text.ParseException;
import java.util.Date;

public class EosorgTEmployee {

    private Integer operatorID;

    private String empCode;

    private Integer orgID;

    private String positionID;

    private String OTel1;

    private String OTel2;

    private String OEmail;

    private String HTel;

    private String HAddress;

    private String zipCode;

    private String PTel1;

    private String PTel2;

    private String PEmail;

    private String PID;

    private Date birthDate;

    private Integer gender;

    private String party;

    private String degree;

    private String major;

    private String hobby;

    private String specialty;

    private String workExp;

    private String training;

    private Date regDate;

    private String faxNO;

    private String mobileNO;

    private String BPNO;

    private Date createTime;

    private Date lastModifyTime;

    private String EMPSTATUS;

    private String CUSTCOLUMN;

    private Integer SORTID;

    private String CUSTNOTICE;

    private String CUSTNOTICENAME;

    private String ORGGRADE;

    private String ADDRESS;

    private Integer NETDISK_SIZE;

    private Integer DEFAULTDISK_SIZE;

    private String ISMOBILEENC;

    private String EMPTYPE;

    private String SEND_DATACHG_NUM;

    private String REC_DATACHG_NUM;

    private String title;

    private String job;

    private String position;

    private String type;

    private String suggestion;

    private String emailPwd;

    private String emailHost;

    private Integer emailSetEnable;

    private Integer isTip;

    private Integer isKeyFigure;

    private String orgName;

    private Integer isCtnEduRequire;

    private Integer newEmployeeYear;

    private Integer lastModifyUserId;

    private EosOperator eosOperator;

    private EosorgTOrganization eosorgTOrganization;

    public EosorgTOrganization getEosorgTOrganization() {
        return eosorgTOrganization;
    }

    public void setEosorgTOrganization(EosorgTOrganization eosorgTOrganization) {
        this.eosorgTOrganization = eosorgTOrganization;
    }


    // Constructors

    public EosOperator getEosoperator() {
        return eosOperator;
    }

    public void setEosoperator(EosOperator eosOperator) {
        this.eosOperator = eosOperator;
    }

    public EosorgTEmployee(Integer operatorID, String empCode, Integer orgID, String positionID, String OTel1, String OTel2, String OEmail, String HTel, String HAddress, String zipCode, String PTel1, String PTel2, String PEmail, String PID, Date birthDate, Integer gender, String party, String degree, String major, String hobby, String specialty, String workExp, String training, Date regDate, String faxNO, String mobileNO, String BPNO, Date createTime, Date lastModifyTime, String EMPSTATUS, String CUSTCOLUMN, Integer SORTID, String CUSTNOTICE, String CUSTNOTICENAME, String ORGGRADE, String ADDRESS, Integer NETDISK_SIZE, Integer DEFAULTDISK_SIZE, String ISMOBILEENC, String EMPTYPE, String SEND_DATACHG_NUM, String REC_DATACHG_NUM, String title, String job, String position, String type, String suggestion, String emailPwd, String emailHost, Integer emailSetEnable, Integer isTip, Integer isKeyFigure, String orgName, Integer isCtnEduRequire, Integer newEmployeeYear, Integer lastModifyUserId) {
        this.operatorID = operatorID;
        this.empCode = empCode;
        this.orgID = orgID;
        this.positionID = positionID;
        this.OTel1 = OTel1;
        this.OTel2 = OTel2;
        this.OEmail = OEmail;
        this.HTel = HTel;
        this.HAddress = HAddress;
        this.zipCode = zipCode;
        this.PTel1 = PTel1;
        this.PTel2 = PTel2;
        this.PEmail = PEmail;
        this.PID = PID;
        this.birthDate = birthDate;
        this.gender = gender;
        this.party = party;
        this.degree = degree;
        this.major = major;
        this.hobby = hobby;
        this.specialty = specialty;
        this.workExp = workExp;
        this.training = training;
        this.regDate = regDate;
        this.faxNO = faxNO;
        this.mobileNO = mobileNO;
        this.BPNO = BPNO;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
        this.EMPSTATUS = EMPSTATUS;
        this.CUSTCOLUMN = CUSTCOLUMN;
        this.SORTID = SORTID;
        this.CUSTNOTICE = CUSTNOTICE;
        this.CUSTNOTICENAME = CUSTNOTICENAME;
        this.ORGGRADE = ORGGRADE;
        this.ADDRESS = ADDRESS;
        this.NETDISK_SIZE = NETDISK_SIZE;
        this.DEFAULTDISK_SIZE = DEFAULTDISK_SIZE;
        this.ISMOBILEENC = ISMOBILEENC;
        this.EMPTYPE = EMPTYPE;
        this.SEND_DATACHG_NUM = SEND_DATACHG_NUM;
        this.REC_DATACHG_NUM = REC_DATACHG_NUM;
        this.title = title;
        this.job = job;
        this.position = position;
        this.type = type;
        this.suggestion = suggestion;
        this.emailPwd = emailPwd;
        this.emailHost = emailHost;
        this.emailSetEnable = emailSetEnable;
        this.isTip = isTip;
        this.isKeyFigure = isKeyFigure;
        this.orgName = orgName;
        this.isCtnEduRequire = isCtnEduRequire;
        this.newEmployeeYear = newEmployeeYear;
        this.lastModifyUserId = lastModifyUserId;
    }

    public EosorgTEmployee(UserForm userForm){
        if(userForm.getOperatorId()!=null){
            this.setOperatorID(userForm.getOperatorId());
        }
        this.setEmpCode(userForm.getEmpCode());
        if(userForm.getAddress()!=null&&!userForm.getAddress().equals(""))
            this.setADDRESS(userForm.getAddress());
        if(userForm.getBpno()!=null&&!userForm.getBpno().equals(""))
            this.setBPNO(userForm.getBpno());
        this.setOrgID(userForm.getOrgId());

        this.setIsCtnEduRequire(userForm.getIsCtnEduRequire());

        this.setLastModifyTime(new Date());

        if(userForm.getBirthDate()!=null&&!userForm.getBirthDate().equals(""))
            try {
                this.setBirthDate(Tools.stringToDate(userForm.getBirthDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        if(userForm.getRegDate()!=null&&!userForm.getRegDate().equals(""))
            try {
                this.setRegDate(Tools.stringToDate(userForm.getRegDate()));
                this.setNewEmployeeYear(Integer.valueOf(Tools.getNowYear(Tools.stringToDate(userForm.getRegDate()))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        if(userForm.getCustcolumn()!=null&&!userForm.getCustcolumn().equals(""))
            this.setCUSTCOLUMN(userForm.getCustcolumn());
        if(userForm.getCustnotice()!=null&&!userForm.getCustnotice().equals(""))
            this.setCUSTNOTICE(userForm.getCustnotice());
        if(userForm.getCustnoticename()!=null)
            this.setCUSTNOTICENAME(userForm.getCustnoticename());
        if(userForm.getDefaultdiskSize()!=null)
            this.setDEFAULTDISK_SIZE(userForm.getDefaultdiskSize());
        if(userForm.getOemail()!=null)
            this.setOEmail(userForm.getOemail());
        if(userForm.getDegree()!=null)
            this.setDegree(userForm.getDegree());
        if(userForm.getEmpCode()!=null&&!userForm.getEmpCode().equals("")){
            this.setEmpCode(userForm.getEmpCode());
        }

        if(userForm.getEmpstatus()!=null)
            this.setEMPSTATUS(userForm.getEmpstatus());
        if(userForm.getEmptype()!=null)
            this.setEMPTYPE(userForm.getEmptype());
        if(userForm.getFaxNo()!=null)
            this.setFaxNO(userForm.getFaxNo());
        if(userForm.getGender()!=null)
            this.setGender(userForm.getGender());
        if(userForm.getHaddress()!=null)
            this.setHAddress(userForm.getHaddress());
        if(userForm.getHobby()!=null)
            this.setHobby(userForm.getHobby());
        if(userForm.getOtel1()!=null)
            this.setOTel1(userForm.getOtel1());
        if(userForm.getHtel()!=null)
            this.setHTel(userForm.getHtel());
        if(userForm.getMobileNo()!=null)
            this.setMobileNO(userForm.getMobileNo());
        if(userForm.getWorkExp()!=null)
            this.setWorkExp(userForm.getWorkExp());
        if(userForm.getTitle()!=null)
            this.setTitle(userForm.getTitle());
        if(userForm.getJob()!=null)
            this.setJob(userForm.getJob());
        if(userForm.getPosition()!=null)
            this.setPosition(userForm.getPosition());
        if(userForm.getType()!=null)
            this.setType(userForm.getType());
        if(userForm.getEmailPwd()!=null)
            this.setEmailPwd(new BASE64Encoder().encodeBuffer(userForm.getEmailPwd().toString().getBytes()));
        if(userForm.getEmailHost()!=null)
            this.setEmailHost(userForm.getEmailHost());
        if(userForm.getEmailSetEnable()!=null)
            this.setEmailSetEnable(userForm.getEmailSetEnable());
        if(userForm.getIsTip()!=null)
            this.setIsTip(userForm.getIsTip());
        if(userForm.getIsKeyFigure()!=null)
            this.setIsKeyFigure(userForm.getIsKeyFigure());
        else
            this.setIsKeyFigure(0);
        if(userForm.getLastModifyUserId() != null)
            this.setLastModifyUserId(userForm.getLastModifyUserId());
    }

    public EosorgTEmployee() {
        super();
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode == null ? null : empCode.trim();
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public String getPositionID() {
        return positionID;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID == null ? null : positionID.trim();
    }

    public String getOTel1() {
        return OTel1;
    }

    public void setOTel1(String OTel1) {
        this.OTel1 = OTel1 == null ? null : OTel1.trim();
    }

    public String getOTel2() {
        return OTel2;
    }

    public void setOTel2(String OTel2) {
        this.OTel2 = OTel2 == null ? null : OTel2.trim();
    }

    public String getOEmail() {
        return OEmail;
    }

    public void setOEmail(String OEmail) {
        this.OEmail = OEmail == null ? null : OEmail.trim();
    }

    public String getHTel() {
        return HTel;
    }

    public void setHTel(String HTel) {
        this.HTel = HTel == null ? null : HTel.trim();
    }

    public String getHAddress() {
        return HAddress;
    }

    public void setHAddress(String HAddress) {
        this.HAddress = HAddress == null ? null : HAddress.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getPTel1() {
        return PTel1;
    }

    public void setPTel1(String PTel1) {
        this.PTel1 = PTel1 == null ? null : PTel1.trim();
    }

    public String getPTel2() {
        return PTel2;
    }

    public void setPTel2(String PTel2) {
        this.PTel2 = PTel2 == null ? null : PTel2.trim();
    }

    public String getPEmail() {
        return PEmail;
    }

    public void setPEmail(String PEmail) {
        this.PEmail = PEmail == null ? null : PEmail.trim();
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID == null ? null : PID.trim();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party == null ? null : party.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    public String getWorkExp() {
        return workExp;
    }

    public void setWorkExp(String workExp) {
        this.workExp = workExp == null ? null : workExp.trim();
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training == null ? null : training.trim();
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getFaxNO() {
        return faxNO;
    }

    public void setFaxNO(String faxNO) {
        this.faxNO = faxNO == null ? null : faxNO.trim();
    }

    public String getMobileNO() {
        return mobileNO;
    }

    public void setMobileNO(String mobileNO) {
        this.mobileNO = mobileNO == null ? null : mobileNO.trim();
    }

    public String getBPNO() {
        return BPNO;
    }

    public void setBPNO(String BPNO) {
        this.BPNO = BPNO == null ? null : BPNO.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getEMPSTATUS() {
        return EMPSTATUS;
    }

    public void setEMPSTATUS(String EMPSTATUS) {
        this.EMPSTATUS = EMPSTATUS == null ? null : EMPSTATUS.trim();
    }

    public String getCUSTCOLUMN() {
        return CUSTCOLUMN;
    }

    public void setCUSTCOLUMN(String CUSTCOLUMN) {
        this.CUSTCOLUMN = CUSTCOLUMN == null ? null : CUSTCOLUMN.trim();
    }

    public Integer getSORTID() {
        return SORTID;
    }

    public void setSORTID(Integer SORTID) {
        this.SORTID = SORTID;
    }

    public String getCUSTNOTICE() {
        return CUSTNOTICE;
    }

    public void setCUSTNOTICE(String CUSTNOTICE) {
        this.CUSTNOTICE = CUSTNOTICE == null ? null : CUSTNOTICE.trim();
    }

    public String getCUSTNOTICENAME() {
        return CUSTNOTICENAME;
    }

    public void setCUSTNOTICENAME(String CUSTNOTICENAME) {
        this.CUSTNOTICENAME = CUSTNOTICENAME == null ? null : CUSTNOTICENAME.trim();
    }

    public String getORGGRADE() {
        return ORGGRADE;
    }

    public void setORGGRADE(String ORGGRADE) {
        this.ORGGRADE = ORGGRADE == null ? null : ORGGRADE.trim();
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS == null ? null : ADDRESS.trim();
    }

    public Integer getNETDISK_SIZE() {
        return NETDISK_SIZE;
    }

    public void setNETDISK_SIZE(Integer NETDISK_SIZE) {
        this.NETDISK_SIZE = NETDISK_SIZE;
    }

    public Integer getDEFAULTDISK_SIZE() {
        return DEFAULTDISK_SIZE;
    }

    public void setDEFAULTDISK_SIZE(Integer DEFAULTDISK_SIZE) {
        this.DEFAULTDISK_SIZE = DEFAULTDISK_SIZE;
    }

    public String getISMOBILEENC() {
        return ISMOBILEENC;
    }

    public void setISMOBILEENC(String ISMOBILEENC) {
        this.ISMOBILEENC = ISMOBILEENC == null ? null : ISMOBILEENC.trim();
    }

    public String getEMPTYPE() {
        return EMPTYPE;
    }

    public void setEMPTYPE(String EMPTYPE) {
        this.EMPTYPE = EMPTYPE == null ? null : EMPTYPE.trim();
    }

    public String getSEND_DATACHG_NUM() {
        return SEND_DATACHG_NUM;
    }

    public void setSEND_DATACHG_NUM(String SEND_DATACHG_NUM) {
        this.SEND_DATACHG_NUM = SEND_DATACHG_NUM == null ? null : SEND_DATACHG_NUM.trim();
    }

    public String getREC_DATACHG_NUM() {
        return REC_DATACHG_NUM;
    }

    public void setREC_DATACHG_NUM(String REC_DATACHG_NUM) {
        this.REC_DATACHG_NUM = REC_DATACHG_NUM == null ? null : REC_DATACHG_NUM.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion == null ? null : suggestion.trim();
    }

    public String getEmailPwd() {
        return emailPwd;
    }

    public void setEmailPwd(String emailPwd) {
        this.emailPwd = emailPwd == null ? null : emailPwd.trim();
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost == null ? null : emailHost.trim();
    }

    public Integer getEmailSetEnable() {
        return emailSetEnable;
    }

    public void setEmailSetEnable(Integer emailSetEnable) {
        this.emailSetEnable = emailSetEnable;
    }

    public Integer getIsTip() {
        return isTip;
    }

    public void setIsTip(Integer isTip) {
        this.isTip = isTip;
    }

    public Integer getIsKeyFigure() {
        return isKeyFigure;
    }

    public void setIsKeyFigure(Integer isKeyFigure) {
        this.isKeyFigure = isKeyFigure;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getIsCtnEduRequire() {
        return isCtnEduRequire;
    }

    public void setIsCtnEduRequire(Integer isCtnEduRequire) {
        this.isCtnEduRequire = isCtnEduRequire;
    }

    public Integer getNewEmployeeYear() {
        return newEmployeeYear;
    }

    public void setNewEmployeeYear(Integer newEmployeeYear) {
        this.newEmployeeYear = newEmployeeYear;
    }

    public Integer getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(Integer lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }
}