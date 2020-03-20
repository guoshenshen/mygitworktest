package com.elearning.pojo.pub;

import com.elearning.vo.EosOrgTOrganizationVo;

public class EosorgTOrganization {
    private Integer orgID;

    private String orgCode;

    private String orgName;

    private Integer ORGLEVEL;

    private Integer parentOrgID;

    private String orgSEQ;

    private String orgTypeID;

    private String ORGSCOPE;

    private String orgAddress;

    private String zipCode;

    private Integer MANAGERID;

    private String LINKMAN;

    private String LINKMANTEL;

    private String EMAIL;

    private String webURL;

    private String memo;

    private Integer positionID;

    private String archiveNum;

    private Integer sortID;

    private String orgClass;

    private String userGroupID;

    private String datachg_num;

    private String send_datachg_num;

    private String rec_datachg_num;

    private Integer sortAll;

    private String extParam1;

    private String extParam2;

    private Integer skinId;

    private Integer isOrg;

    private Integer isVirOrg;

    private Integer attachedOrgId;

    private Integer status;

    private Integer tenantId;

    private String logo;

    private byte[] ADDRESSCODE;

    public EosorgTOrganization(Integer orgID, String orgCode, String orgName, Integer ORGLEVEL, Integer parentOrgID, String orgSEQ, String orgTypeID, String ORGSCOPE, String orgAddress, String zipCode, Integer MANAGERID, String LINKMAN, String LINKMANTEL, String EMAIL, String webURL, String memo, Integer positionID, String archiveNum, Integer sortID, String orgClass, String userGroupID, String datachg_num, String send_datachg_num, String rec_datachg_num, Integer sortAll, String extParam1, String extParam2, Integer skinId, Integer isOrg, Integer isVirOrg, Integer attachedOrgId, Integer status, Integer tenantId, String logo, byte[] ADDRESSCODE) {
        this.orgID = orgID;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.ORGLEVEL = ORGLEVEL;
        this.parentOrgID = parentOrgID;
        this.orgSEQ = orgSEQ;
        this.orgTypeID = orgTypeID;
        this.ORGSCOPE = ORGSCOPE;
        this.orgAddress = orgAddress;
        this.zipCode = zipCode;
        this.MANAGERID = MANAGERID;
        this.LINKMAN = LINKMAN;
        this.LINKMANTEL = LINKMANTEL;
        this.EMAIL = EMAIL;
        this.webURL = webURL;
        this.memo = memo;
        this.positionID = positionID;
        this.archiveNum = archiveNum;
        this.sortID = sortID;
        this.orgClass = orgClass;
        this.userGroupID = userGroupID;
        this.datachg_num = datachg_num;
        this.send_datachg_num = send_datachg_num;
        this.rec_datachg_num = rec_datachg_num;
        this.sortAll = sortAll;
        this.extParam1 = extParam1;
        this.extParam2 = extParam2;
        this.skinId = skinId;
        this.isOrg = isOrg;
        this.isVirOrg = isVirOrg;
        this.attachedOrgId = attachedOrgId;
        this.status = status;
        this.tenantId = tenantId;
        this.logo = logo;
        this.ADDRESSCODE = ADDRESSCODE;
    }

    public EosorgTOrganization(EosOrgTOrganizationVo eosorgTOrganizationForm){
        //if(eosorgTOrganizationForm.getAddresscode()!=null)
        //  this.setADDRESSCODE(eosorgTOrganizationForm.getAddresscode());
        if(eosorgTOrganizationForm.getArchiveNum()!=null)
            this.setArchiveNum(eosorgTOrganizationForm.getArchiveNum());
        if(eosorgTOrganizationForm.getDatachgNum()!=null)
            this.setdatachg_num(eosorgTOrganizationForm.getDatachgNum());
        if(eosorgTOrganizationForm.getEmail()!=null)
            this.setEMAIL(eosorgTOrganizationForm.getEmail());
        if(eosorgTOrganizationForm.getExtParam1()!=null)
            this.setExtParam1(eosorgTOrganizationForm.getExtParam1());
        if(eosorgTOrganizationForm.getExtParam2()!=null)
            this.setExtParam2(eosorgTOrganizationForm.getExtParam2());
        if(eosorgTOrganizationForm.getIsOrg()!=null)
            this.setIsOrg(eosorgTOrganizationForm.getIsOrg());
        if(eosorgTOrganizationForm.getIsVirOrg()!=null)
            this.setIsVirOrg(eosorgTOrganizationForm.getIsVirOrg());
        if(eosorgTOrganizationForm.getLinkman()!=null)
            this.setLINKMAN(eosorgTOrganizationForm.getLinkman());
        if(eosorgTOrganizationForm.getLinkmantel()!=null)
            this.setLINKMANTEL(eosorgTOrganizationForm.getLinkmantel());
        if(eosorgTOrganizationForm.getManagerid()!=null)
            this.setMANAGERID(eosorgTOrganizationForm.getManagerid());
        if(eosorgTOrganizationForm.getMemo()!=null)
            this.setMemo(eosorgTOrganizationForm.getMemo());
        if(eosorgTOrganizationForm.getOrgAddress()!=null)
            this.setOrgAddress(eosorgTOrganizationForm.getOrgAddress());
        if(eosorgTOrganizationForm.getOrgClass()!=null)
            this.setOrgClass(eosorgTOrganizationForm.getOrgClass());
        if(eosorgTOrganizationForm.getOrgCode()!=null)
            this.setOrgCode(eosorgTOrganizationForm.getOrgCode());
        if(eosorgTOrganizationForm.getOrgId()!=null)
            this.setOrgID(eosorgTOrganizationForm.getOrgId());
        if(eosorgTOrganizationForm.getOrglevel()!=null)
            this.setORGLEVEL(eosorgTOrganizationForm.getOrglevel());
        if(eosorgTOrganizationForm.getOrgName()!=null)
            this.setOrgName(eosorgTOrganizationForm.getOrgName());
        if(eosorgTOrganizationForm.getOrgscope()!=null)
            this.setORGSCOPE(eosorgTOrganizationForm.getOrgscope());
        if(eosorgTOrganizationForm.getOrgSeq()!=null)
            this.setOrgSEQ(eosorgTOrganizationForm.getOrgSeq());
        if(eosorgTOrganizationForm.getOrgTypeId()!=null)
            this.setOrgTypeID(eosorgTOrganizationForm.getOrgTypeId());
        if(eosorgTOrganizationForm.getPositionId()!=null)
            this.setPositionID(eosorgTOrganizationForm.getPositionId());
        if(eosorgTOrganizationForm.getRecDatachgNum()!=null)
            this.setrec_datachg_num(eosorgTOrganizationForm.getRecDatachgNum());
        if(eosorgTOrganizationForm.getSendDatachgNum()!=null)
            this.setsend_datachg_num(eosorgTOrganizationForm.getSendDatachgNum());
        if(eosorgTOrganizationForm.getSortAll()!=null)
            this.setSortAll(eosorgTOrganizationForm.getSortAll());
        if(eosorgTOrganizationForm.getSortId()!=null)
            this.setSortID(eosorgTOrganizationForm.getSortId());
        if(eosorgTOrganizationForm.getUserGroupId()!=null)
            this.setUserGroupID(eosorgTOrganizationForm.getUserGroupId());
        if(eosorgTOrganizationForm.getWebUrl()!=null)
            this.setWebURL(eosorgTOrganizationForm.getWebUrl());
        if(eosorgTOrganizationForm.getLogo()!=null)
            this.setLogo(eosorgTOrganizationForm.getLogo());
        if(eosorgTOrganizationForm.getStatus()!=null)
            this.setStatus(eosorgTOrganizationForm.getStatus());
        if(eosorgTOrganizationForm.getParentOrgId()!=null){
            int parentOrgId = eosorgTOrganizationForm.getParentOrgId();
            this.setParentOrgID(parentOrgId);
        }else{

        }
        if(eosorgTOrganizationForm.getAttachedOrgId()!=null){
            int attachedOrgId = eosorgTOrganizationForm.getAttachedOrgId();
            this.setAttachedOrgId(attachedOrgId);
        }else{

        }
    }

    public EosorgTOrganization() {
        super();
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getORGLEVEL() {
        return ORGLEVEL;
    }

    public void setORGLEVEL(Integer ORGLEVEL) {
        this.ORGLEVEL = ORGLEVEL;
    }

    public Integer getParentOrgID() {
        return parentOrgID;
    }

    public void setParentOrgID(Integer parentOrgID) {
        this.parentOrgID = parentOrgID;
    }

    public String getOrgSEQ() {
        return orgSEQ;
    }

    public void setOrgSEQ(String orgSEQ) {
        this.orgSEQ = orgSEQ == null ? null : orgSEQ.trim();
    }

    public String getOrgTypeID() {
        return orgTypeID;
    }

    public void setOrgTypeID(String orgTypeID) {
        this.orgTypeID = orgTypeID == null ? null : orgTypeID.trim();
    }

    public String getORGSCOPE() {
        return ORGSCOPE;
    }

    public void setORGSCOPE(String ORGSCOPE) {
        this.ORGSCOPE = ORGSCOPE == null ? null : ORGSCOPE.trim();
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress == null ? null : orgAddress.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public Integer getMANAGERID() {
        return MANAGERID;
    }

    public void setMANAGERID(Integer MANAGERID) {
        this.MANAGERID = MANAGERID;
    }

    public String getLINKMAN() {
        return LINKMAN;
    }

    public void setLINKMAN(String LINKMAN) {
        this.LINKMAN = LINKMAN == null ? null : LINKMAN.trim();
    }

    public String getLINKMANTEL() {
        return LINKMANTEL;
    }

    public void setLINKMANTEL(String LINKMANTEL) {
        this.LINKMANTEL = LINKMANTEL == null ? null : LINKMANTEL.trim();
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL == null ? null : EMAIL.trim();
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL == null ? null : webURL.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getPositionID() {
        return positionID;
    }

    public void setPositionID(Integer positionID) {
        this.positionID = positionID;
    }

    public String getArchiveNum() {
        return archiveNum;
    }

    public void setArchiveNum(String archiveNum) {
        this.archiveNum = archiveNum == null ? null : archiveNum.trim();
    }

    public Integer getSortID() {
        return sortID;
    }

    public void setSortID(Integer sortID) {
        this.sortID = sortID;
    }

    public String getOrgClass() {
        return orgClass;
    }

    public void setOrgClass(String orgClass) {
        this.orgClass = orgClass == null ? null : orgClass.trim();
    }

    public String getUserGroupID() {
        return userGroupID;
    }

    public void setUserGroupID(String userGroupID) {
        this.userGroupID = userGroupID == null ? null : userGroupID.trim();
    }

    public String getdatachg_num() {
        return datachg_num;
    }

    public void setdatachg_num(String datachg_num) {
        this.datachg_num = datachg_num == null ? null : datachg_num.trim();
    }

    public String getsend_datachg_num() {
        return send_datachg_num;
    }

    public void setsend_datachg_num(String send_datachg_num) {
        this.send_datachg_num = send_datachg_num == null ? null : send_datachg_num.trim();
    }

    public String getrec_datachg_num() {
        return rec_datachg_num;
    }

    public void setrec_datachg_num(String rec_datachg_num) {
        this.rec_datachg_num = rec_datachg_num == null ? null : rec_datachg_num.trim();
    }

    public Integer getSortAll() {
        return sortAll;
    }

    public void setSortAll(Integer sortAll) {
        this.sortAll = sortAll;
    }

    public String getExtParam1() {
        return extParam1;
    }

    public void setExtParam1(String extParam1) {
        this.extParam1 = extParam1 == null ? null : extParam1.trim();
    }

    public String getExtParam2() {
        return extParam2;
    }

    public void setExtParam2(String extParam2) {
        this.extParam2 = extParam2 == null ? null : extParam2.trim();
    }

    public Integer getSkinId() {
        return skinId;
    }

    public void setSkinId(Integer skinId) {
        this.skinId = skinId;
    }

    public Integer getIsOrg() {
        return isOrg;
    }

    public void setIsOrg(Integer isOrg) {
        this.isOrg = isOrg;
    }

    public Integer getIsVirOrg() {
        return isVirOrg;
    }

    public void setIsVirOrg(Integer isVirOrg) {
        this.isVirOrg = isVirOrg;
    }

    public Integer getAttachedOrgId() {
        return attachedOrgId;
    }

    public void setAttachedOrgId(Integer attachedOrgId) {
        this.attachedOrgId = attachedOrgId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public byte[] getADDRESSCODE() {
        return ADDRESSCODE;
    }

    public void setADDRESSCODE(byte[] ADDRESSCODE) {
        this.ADDRESSCODE = ADDRESSCODE;
    }



}