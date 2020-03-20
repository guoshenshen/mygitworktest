package com.elearning.service.pub;

import com.elearning.common.CacheUtils;
import com.elearning.common.DAOTool;
import com.elearning.common.OpenScopeType;
import com.elearning.dao.pub.EosorgTOrganizationMapper;
import com.elearning.dao.systemManage.TenantMapper;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.vo.EosOrgTOrganizationVo;
import com.elearning.vo.pub.BasicOrgForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/6/24 10:02
 */
@Service("eosorgTOrganizationService")
public class EosorgTOrganizationServiceImpl implements IEosorgTOrganizationService{

    @Autowired
    private EosorgTOrganizationMapper eosorgTOrganizationMapper;

    @Autowired
    private TenantMapper tenantMapper;

    /**
     * 部门信息更新,外部调用请使用String update(EosOrgTOrganizationVo eosorgTOrganizationForm, Integer tenantOrgId)
     * @param eosorgTOrganization
     * @return
     */
    private Integer update(EosorgTOrganization eosorgTOrganization) {
        return eosorgTOrganizationMapper.updateByPrimaryKeySelective(eosorgTOrganization);
    }

    /**
     * 增添部门，外部调用请使用String save(EosOrgTOrganizationVo eosorgTOrganizationForm,int tenantOrgId)
     * @param eosorgTOrganization
     * @return
     */
    private Integer add(EosorgTOrganization eosorgTOrganization) {
        int _orgid1 = Math.abs(new Long(new Date().getTime()).intValue());
        int _orgid = this.uniqueOrgId(_orgid1);
        eosorgTOrganization.setOrgID(_orgid);
        return eosorgTOrganizationMapper.insertSelective(eosorgTOrganization);
    }

    @Override
    public String update(EosOrgTOrganizationVo eosorgTOrganizationForm, Integer tenantOrgId){
        StringBuffer message = null;
        if(eosorgTOrganizationForm.getOrgCode()!=null){
            //在某组织机构（tenantOrg）下插入部门，首先判断tenantOrg是否有重复编号部门!!!!!!!!!!!!!!!!!!!!!!
            EosorgTOrganization tenantOrg=this.findById(tenantOrgId);
            List<BasicOrgForm> orgWithSameCode = this.findByOrgCode(eosorgTOrganizationForm.getOrgCode(),tenantOrg) ;
            boolean flag1 = false;
            if(orgWithSameCode==null||orgWithSameCode.size()==0||(orgWithSameCode.size()==1&&orgWithSameCode.get(0).getOrgId().equals(eosorgTOrganizationForm.getOrgId())));
            else{
                message=new StringBuffer("组织机构代码与系统中已有组织机构代码重复，无法保存，请修改。编号相同部门包括：");
                for(BasicOrgForm org:orgWithSameCode) {
                    message.append(org.getOrgName()+" ");
                }
                return message.toString();
            }

        }
        EosorgTOrganization _EosorgTOrganization = this.findById(eosorgTOrganizationForm.getOrgId());
        //	BeanUtils. copyProperties(eosorgTOrganizationForm,_EosorgTOrganization);
        _EosorgTOrganization= getEosorgTOrganization(eosorgTOrganizationForm);
        this.update(_EosorgTOrganization);
        message =new StringBuffer(_EosorgTOrganization.getOrgID().toString());
        return message.toString();
    }

    @Override
    public String save(EosOrgTOrganizationVo eosorgTOrganizationForm,int tenantOrgId){
        StringBuffer message = null;
        if(eosorgTOrganizationForm.getOrgCode()!=null){
            //在某组织机构（tenantOrg）下插入部门，首先判断tenantOrg是否有重复编号部门!!!!!!!!!!!!!!!!!!!!!!
            EosorgTOrganization tenantOrg=this.findById(tenantOrgId);
            List<BasicOrgForm> orgWithSameCode = this.findByOrgCode(eosorgTOrganizationForm.getOrgCode(),tenantOrg) ;
            boolean flag1 = false;
            if(orgWithSameCode!=null&&orgWithSameCode.size()>0){
                message=new StringBuffer("组织机构代码与系统中已有组织机构代码重复，无法保存，请修改。编号相同部门包括：");
                for(BasicOrgForm org:orgWithSameCode) {
                    message.append(org.getOrgName()+" ");
                }
                return message.toString();
            }
        }

        EosorgTOrganization _eosorgTOrganization = new EosorgTOrganization();
        //	BeanUtils. copyProperties(eosorgTOrganizationForm,_eosorgTOrganization);
        _eosorgTOrganization=getEosorgTOrganization(eosorgTOrganizationForm);
        EosorgTOrganization parentOrg = this.findById(eosorgTOrganizationForm.getParentOrgId());
        this.add(_eosorgTOrganization);
        String orgSeq = parentOrg.getOrgSEQ()+_eosorgTOrganization.getOrgID()+".";
        _eosorgTOrganization.setOrgSEQ(orgSeq);
        this.update(_eosorgTOrganization);
        message =new StringBuffer(_eosorgTOrganization.getOrgID().toString());
        return message.toString();
    }

    @Override
    public EosorgTOrganization getEosorgTOrganizationById(Integer orgId) {
        return eosorgTOrganizationMapper.selectByPrimaryKey(orgId);
    }

    @Override
    public EosorgTOrganization findById(Integer orgId){
        return this.getEosorgTOrganizationById(orgId);
    }


    private int uniqueOrgId(int _orgId){
        if(eosorgTOrganizationMapper.selectByPrimaryKey(_orgId) != null){
            _orgId = _orgId+1;
            return uniqueOrgId(_orgId);
        }else {
            return _orgId;
        }
    }



    public static List<String> orgSEQList(String orgSEQ){
        List<String> orgSeqList=new ArrayList<String>();
        StringBuffer orgSEQStr=new StringBuffer("");
        String[] orgs=orgSEQ.split("\\.");
        for(String orgStr:orgs){
            orgSEQStr.append(orgStr);
            orgSEQStr.append(".");
            orgSeqList.add(orgSEQStr.toString());

        }
        return orgSeqList;
    }

    @Override
    public EosOrgTOrganizationVo getEosOrgTOrganizationVo(EosorgTOrganization eosorgTOrganization){
        EosOrgTOrganizationVo eosorgTOrganizationForm = new EosOrgTOrganizationVo();
        if(eosorgTOrganization.getOrgID()!=null)
            eosorgTOrganizationForm.setOrgId(eosorgTOrganization.getOrgID());
        if(eosorgTOrganization.getADDRESSCODE()!=null)
            eosorgTOrganizationForm.setAddresscode(eosorgTOrganization.getADDRESSCODE().toString());
        if(eosorgTOrganization.getOrgName()!=null)
            eosorgTOrganizationForm.setOrgName(eosorgTOrganization.getOrgName());
        if(eosorgTOrganization.getArchiveNum()!=null)
            eosorgTOrganizationForm.setArchiveNum(eosorgTOrganization.getArchiveNum());
        if(eosorgTOrganization.getdatachg_num()!=null)
            eosorgTOrganizationForm.setDatachgNum(eosorgTOrganization.getdatachg_num());
        if(eosorgTOrganization.getEMAIL()!=null)
            eosorgTOrganizationForm.setEmail(eosorgTOrganization.getEMAIL());
        if(eosorgTOrganization.getExtParam1()!=null)
            eosorgTOrganizationForm.setExtParam1(eosorgTOrganization.getExtParam1());
        if(eosorgTOrganization.getExtParam2()!=null)
            eosorgTOrganizationForm.setExtParam2(eosorgTOrganization.getExtParam2());
        if(eosorgTOrganization.getIsOrg()!=null)
            eosorgTOrganizationForm.setIsOrg(eosorgTOrganization.getIsOrg());
        if(eosorgTOrganization.getIsVirOrg()!=null)
            eosorgTOrganizationForm.setIsVirOrg(eosorgTOrganization.getIsVirOrg());
        if(eosorgTOrganization.getLINKMAN()!=null)
            eosorgTOrganizationForm.setLinkman(eosorgTOrganization.getLINKMAN());
        if(eosorgTOrganization.getLINKMANTEL()!=null)
            eosorgTOrganizationForm.setLinkmantel(eosorgTOrganization.getLINKMANTEL());
        if(eosorgTOrganization.getMANAGERID()!=null)
            eosorgTOrganizationForm.setManagerid(eosorgTOrganization.getMANAGERID());
        if(eosorgTOrganization.getMemo()!=null)
            eosorgTOrganizationForm.setMemo(eosorgTOrganization.getMemo());
        if(eosorgTOrganization.getOrgAddress()!=null)
            eosorgTOrganizationForm.setOrgAddress(eosorgTOrganization.getOrgAddress());
        if(eosorgTOrganization.getOrgClass()!=null)
            eosorgTOrganizationForm.setOrgClass(eosorgTOrganization.getOrgClass());
        if(eosorgTOrganization.getOrgCode()!=null)
            eosorgTOrganizationForm.setOrgCode(eosorgTOrganization.getOrgCode());
        if(eosorgTOrganization.getOrgID()!=null)
            eosorgTOrganizationForm.setOrgId(eosorgTOrganization.getOrgID());
        if(eosorgTOrganization.getORGLEVEL()!=null)
            eosorgTOrganizationForm.setOrglevel(eosorgTOrganization.getORGLEVEL());
        if(eosorgTOrganization.getOrgName()!=null)
            eosorgTOrganizationForm.setOrgName(eosorgTOrganization.getOrgName());
        if(eosorgTOrganization.getORGSCOPE()!=null)
            eosorgTOrganizationForm.setOrgscope(eosorgTOrganization.getORGSCOPE());
        if(eosorgTOrganization.getOrgSEQ()!=null)
            eosorgTOrganizationForm.setOrgSeq(eosorgTOrganization.getOrgSEQ());
        if(eosorgTOrganization.getOrgTypeID()!=null)
            eosorgTOrganizationForm.setOrgTypeId(eosorgTOrganization.getOrgTypeID());
        if(eosorgTOrganization.getPositionID()!=null)
            eosorgTOrganizationForm.setPositionId(eosorgTOrganization.getPositionID());
        if(eosorgTOrganization.getrec_datachg_num()!=null)
            eosorgTOrganizationForm.setRecDatachgNum(eosorgTOrganization.getrec_datachg_num());
        if(eosorgTOrganization.getsend_datachg_num()!=null)
            eosorgTOrganizationForm.setSendDatachgNum(eosorgTOrganization.getsend_datachg_num());
        if(eosorgTOrganization.getSortAll()!=null)
            eosorgTOrganizationForm.setSortAll(eosorgTOrganization.getSortAll());
        if(eosorgTOrganization.getSortID()!=null)
            eosorgTOrganizationForm.setSortId(eosorgTOrganization.getSortID());
        if(eosorgTOrganization.getUserGroupID()!=null)
            eosorgTOrganizationForm.setUserGroupId(eosorgTOrganization.getUserGroupID());
        if(eosorgTOrganization.getWebURL()!=null)
            eosorgTOrganizationForm.setWebUrl(eosorgTOrganization.getWebURL());
        if(eosorgTOrganization.getLogo()!=null)
            eosorgTOrganizationForm.setLogo(eosorgTOrganization.getLogo());
        if(eosorgTOrganization.getStatus()!=null)
            eosorgTOrganizationForm.setStatus(eosorgTOrganization.getStatus());
        if(eosorgTOrganization.getTenantId()!=null){
            eosorgTOrganizationForm.setTenantId(eosorgTOrganization.getTenantId());
        }
        if(eosorgTOrganization.getParentOrgID()!=null){
            String parentOrgName = this.eosorgTOrganizationMapper.selectByPrimaryKey(eosorgTOrganization.getParentOrgID()).getOrgName();
            eosorgTOrganizationForm.setParentOrgId(eosorgTOrganization.getParentOrgID());
            eosorgTOrganizationForm.setParentOrgName(parentOrgName);
        }else{
        }
        if(eosorgTOrganization.getAttachedOrgId()!=null
                &&eosorgTOrganization.getAttachedOrgId().intValue()!=0){
            int attachedOrgId = eosorgTOrganization.getAttachedOrgId();
            String attachedOrgName = eosorgTOrganizationMapper.selectByPrimaryKey(attachedOrgId).getOrgName();
            eosorgTOrganizationForm.setAttachedOrgName(attachedOrgName);
            eosorgTOrganizationForm.setAttachedOrgId(attachedOrgId);
        }else{
        }
        return eosorgTOrganizationForm;
    }

    @Override
    public List<EosOrgTOrganizationVo> findOrgInfoById(List<Integer> orgIdList) {
        Map<String,Object> map = new HashMap<>();
        map.put("orgIdList",orgIdList);
        List<EosorgTOrganization> eosorgTOrganizationList = eosorgTOrganizationMapper.getListByIds(map);
        List<EosOrgTOrganizationVo> orgFormList = new ArrayList<>();
        for (EosorgTOrganization eosorgTOrganization : eosorgTOrganizationList) {
            if (eosorgTOrganization != null) {
                EosOrgTOrganizationVo orgFormTemp = this.getEosOrgTOrganizationVo(eosorgTOrganization);
                orgFormList.add(orgFormTemp);
            }
        }
        return orgFormList;
    }


    /**
     * 根据部门orgId获得父机构中的二级机构,例如"中国科学院计算机网络信息中心"就是"中国科学院"
     * Date:2017-05-25
     * author:xiongying
     * 方法二、正则表达式处理字符串的方式
     * 失败：则返回0
     * @param
     * @return
     */
    @Override
    public int findSecondaryParentOrgId(int orgId) {
        String orgSeq = eosorgTOrganizationMapper.findSecondaryParentOrgId(orgId);
        int secondaryParentOrgId = 0 ;
        if(orgSeq != null ){
            String regex = "(.*?)\\.(.*?)\\.(.*?)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(orgSeq);
            if(matcher.matches()){
                secondaryParentOrgId = Integer.parseInt(matcher.group(2));
            }
        }
        return secondaryParentOrgId;
    }


    /**
     * 根据tenant的orgId查询出所有的tenantId
     * @param map
     */
    @Override
    public void putSameSecondaryParentTenantIdListInMap(Map<String, Object> map) {
        int secondaryParentOrgId = findSecondaryParentOrgId(Integer.parseInt(map.get("tenantOrgId").toString()));
        List<Integer> sameSecondaryParentTenantIdList = new ArrayList<>();
        if(secondaryParentOrgId>0){
            sameSecondaryParentTenantIdList = eosorgTOrganizationMapper.findOrgAndSubTenantIdList(secondaryParentOrgId);
            if(sameSecondaryParentTenantIdList != null && sameSecondaryParentTenantIdList.size()>0){
                map.put("sameSecondaryParentTenantIdList",sameSecondaryParentTenantIdList);
            }
        }else{//orgId=1的情况
            List<Tenant> t = tenantMapper.findByOrgId(Integer.parseInt(map.get("tenantOrgId").toString()));
            if(t.size()>0){
                sameSecondaryParentTenantIdList.add(t.get(0).getTenantId());
                map.put("sameSecondaryParentTenantIdList",sameSecondaryParentTenantIdList);
            }
        }
    }

    /**
     * 根据 orgId 公开范围查询 orgSEQ
     * @param orgId
     * @param openScope
     * @return
     */
    @Override
    public String getOpenOrgSEQ(Integer orgId, Short openScope) {
        String openOrgSEQ="";
        OpenScopeType scope= OpenScopeType.findByCode(openScope);
        EosorgTOrganization targetOrg=null;
        Tenant belongTenant=null;
        switch(scope){
            case NotOpen:
                openOrgSEQ="-1";
                break;
            case BranchOpen:
                targetOrg= eosorgTOrganizationMapper.selectByPrimaryKey(orgId);
                openOrgSEQ=targetOrg.getOrgSEQ();
                break;
            case UnitOpen:
                belongTenant = tenantMapper.selectByPrimaryKey(eosorgTOrganizationMapper.selectByPrimaryKey(orgId).getTenantId());
                openOrgSEQ = eosorgTOrganizationMapper.selectByPrimaryKey(belongTenant.getOrgId()).getOrgSEQ();
                break;
            case CASOpen:
                openOrgSEQ="1.2.";
                break;
            case CompleteOpen:
                openOrgSEQ="1.";
                break;
            default:

        }
        return openOrgSEQ;
    }

    @Override
    public List<BasicOrgForm> findOrderTenantOrgByCondition( Map<String, Object> condition) {
        Map<String,Object> conditionForSearch=new HashMap<String,Object>();
        //condition.put("openOrgSEQ",orgSEQList(condition.get("openOrgSEQ").toString()));
        //return eosorgTOrganizationMapper.findBasicInfoByCondition(condition);

        StringBuffer sql=new StringBuffer(", orderTenant ot  ");
        String orderPageString=" order by ot.order asc ";
        String openOrgSeq="1.";
        if(condition==null){
            condition=new HashMap<String,Object>();
        }
        if(condition.get("openOrgSEQ")!=null && !condition.get("openOrgSEQ").toString().trim().equals("")){
            openOrgSeq=(String) condition.get("openOrgSEQ");
        }
        List<String> orgSEQList=orgSEQList(openOrgSeq);
        conditionForSearch.put("openOrgSEQ", orgSEQList);
        sql.append(" org.orgId=ot.orgId and ot.openOrgSEQ in (:openOrgSEQ)");
        return this.findByConditionSQL(sql.toString(),conditionForSearch,orderPageString);
    }

    @Override
    public String getStandardOrgName(EosorgTOrganization targetOrg, Integer tenantId) {
        String standardOrgName="";
        Integer orgTenantId=this.findTenantId(targetOrg.getOrgID());
        targetOrg.setTenantId(orgTenantId);
        if(orgTenantId.equals(tenantId)){
            standardOrgName=targetOrg.getOrgName();
        }
        else{
            standardOrgName=CacheUtils.getTenantName(orgTenantId);
        }
        return standardOrgName;
    }

    @Override
    public String getTenantOrgName(EosorgTOrganization targetOrg) {
        String tenantName="";
        Integer orgId=targetOrg.getOrgID();
        Integer orgTenantId=this.findTenantId(orgId);
        tenantName=CacheUtils.getTenantName(orgTenantId);
        return tenantName;
    }


    public static List<Integer> getParentsOrgIdList(String orgSEQ){
        List<Integer> orgIdList=new ArrayList<Integer>();
        String[] orgs=orgSEQ.split("\\.");
        for(String orgStr:orgs){
            orgIdList.add(Integer.parseInt(orgStr));
        }
        return orgIdList;
    }

    public Integer findOrgId(Integer orgid){
        EosorgTOrganization org = this.eosorgTOrganizationMapper.selectByPrimaryKey(orgid);
        if(org.getIsOrg()!=null && org.getIsOrg().intValue()==1){
            return orgid;
        } else{
            EosorgTOrganization targetOrg= this.findOrg(org);
            if(targetOrg!=null){
                return targetOrg.getOrgID();
            }
            else{
                return null;
            }
        }
    }

    public EosorgTOrganization findOrg(EosorgTOrganization org){
        if(org==null){
            return null;
        }
        if(org.getIsOrg()!=null && org.getIsOrg().intValue()==1){
            return org;
        } else{
            EosorgTOrganization parentOrg = this.eosorgTOrganizationMapper.selectByPrimaryKey(org.getParentOrgID());
            if(parentOrg==null){
                return null;
            } else if(parentOrg.getIsOrg().intValue()==1){
                return parentOrg;
            } else{
                return findOrg(parentOrg);
            }
        }
    }


    public Integer findParentOrgId(Integer orgd){
        EosorgTOrganization org = this.findById(orgd);
        EosorgTOrganization parentOrg=this.findParentOrg(org);
        if(parentOrg!=null){
            return parentOrg.getOrgID();
        }
        else{
            return null;
        }
    }


    public EosorgTOrganization findParentOrg(EosorgTOrganization org){
        if(org==null){
            return null;
        }
        else{
            if(org.getParentOrgID()!=null){
                EosorgTOrganization parentOrg = this.findById(org.getParentOrgID());
                return this.findOrg(parentOrg);
            }
            else{
                return null;
            }
        }
    }


    @Override
    public Tenant findTenant(Integer orgId) {
        Tenant tenant=null;
        if(orgId!=null){
            EosorgTOrganization org = eosorgTOrganizationMapper.selectByPrimaryKey(orgId);
            tenant=this.findTenant(org);
        }
        return tenant;
    }


    @Override
    public Tenant findTenant(EosorgTOrganization org){
        Tenant tenant=null;
        if(org.getTenantId()!=null){
            tenant = tenantMapper.selectByPrimaryKey(org.getTenantId());
        }else{
            List<Integer> parentOrgIdList = getParentsOrgIdList(org.getOrgSEQ());
            Map<String,Object> map = new HashMap<>();
            map.put("orgIdList",parentOrgIdList);
            List<EosorgTOrganization> eosorgTOrganizationList = eosorgTOrganizationMapper.getTenantedOrg(map);

            if(eosorgTOrganizationList!=null){
                EosorgTOrganization tenantOrg = eosorgTOrganizationList.get(0);
                tenant = tenantMapper.selectByPrimaryKey(tenantOrg.getTenantId());
            }
        }
        return tenant;
    }


    @Override
    public Integer findTenantId(EosorgTOrganization org){
        Integer result=null;
        if(org!=null){
            result=org.getTenantId();
            if(result==null){
                Tenant tenant=this.findTenant(org);
                if(tenant!=null){
                    result=tenant.getTenantId();
                }
            }
        }
        else{

        }
        return result;
    }

    @Override
    public Integer findTenantId(Integer orgid) {
        Integer result=null;
        if(orgid==null);
        else{
            Tenant tenant=this.findTenant(orgid);
            if(tenant!=null){
                result=tenant.getTenantId();
            }
        }
        return result;
    }

    @Override
    public BasicOrgForm getSimpleOrgInfo(EosorgTOrganization eosorgTOrganization){
        BasicOrgForm orgForm=new BasicOrgForm();
        orgForm.setOrgId(eosorgTOrganization.getOrgID());
        orgForm.setOrgName(eosorgTOrganization.getOrgName());
        orgForm.setOrgCode(eosorgTOrganization.getOrgCode());
        orgForm.setOrgSeq(eosorgTOrganization.getOrgSEQ());
        orgForm.setStatus(eosorgTOrganization.getStatus());
        orgForm.setIsOrg(eosorgTOrganization.getIsOrg());
        orgForm.setIsVirOrg(eosorgTOrganization.getIsVirOrg());
        orgForm.setTenantId(eosorgTOrganization.getTenantId());
        if(orgForm.getTenantId()!=null){
            orgForm.setTenantName(CacheUtils.getTenantName(orgForm.getTenantId()));
        }
        else{
            orgForm.setTenantName("");
        }
        return orgForm;
    }

    @Override
    public EosorgTOrganization findByTenantId(Integer tenantId) {
        return this.eosorgTOrganizationMapper.findByTenantId(tenantId);
    }

    @Override
    public List<BasicOrgForm> findByConditionSQL(String sqlQuery,Map<String,Object> condition, String orderPageString) {

        String resetQuery=DAOTool.setParameterValue(sqlQuery,condition);
        return this.eosorgTOrganizationMapper.findBasicInfoByConditionSQL(resetQuery,orderPageString);
    }

    @Override
    public EosorgTOrganization getEosorgTOrganization(EosOrgTOrganizationVo eosorgTOrganizationForm) {
       return new EosorgTOrganization(eosorgTOrganizationForm);
    }


    @Override
    public EosOrgTOrganizationVo getOrgForm(EosorgTOrganization eosorgTOrganization) {
        EosOrgTOrganizationVo eosorgTOrganizationForm = new EosOrgTOrganizationVo();
        if(eosorgTOrganization.getOrgID()!=null)
            eosorgTOrganizationForm.setOrgId(eosorgTOrganization.getOrgID());
        //if(eosorgTOrganization.getADDRESSCODE()!=null)
          //  eosorgTOrganizationForm.setAddresscode(eosorgTOrganization.getADDRESSCODE());
        if(eosorgTOrganization.getOrgName()!=null)
            eosorgTOrganizationForm.setOrgName(eosorgTOrganization.getOrgName());
        if(eosorgTOrganization.getArchiveNum()!=null)
            eosorgTOrganizationForm.setArchiveNum(eosorgTOrganization.getArchiveNum());
        if(eosorgTOrganization.getdatachg_num()!=null)
            eosorgTOrganizationForm.setDatachgNum(eosorgTOrganization.getdatachg_num());
        if(eosorgTOrganization.getEMAIL()!=null)
            eosorgTOrganizationForm.setEmail(eosorgTOrganization.getEMAIL());
        if(eosorgTOrganization.getExtParam1()!=null)
            eosorgTOrganizationForm.setExtParam1(eosorgTOrganization.getExtParam1());
        if(eosorgTOrganization.getExtParam2()!=null)
            eosorgTOrganizationForm.setExtParam2(eosorgTOrganization.getExtParam2());
        if(eosorgTOrganization.getIsOrg()!=null)
            eosorgTOrganizationForm.setIsOrg(eosorgTOrganization.getIsOrg());
        if(eosorgTOrganization.getIsVirOrg()!=null)
            eosorgTOrganizationForm.setIsVirOrg(eosorgTOrganization.getIsVirOrg());
        if(eosorgTOrganization.getLINKMAN()!=null)
            eosorgTOrganizationForm.setLinkman(eosorgTOrganization.getLINKMAN());
        if(eosorgTOrganization.getLINKMANTEL()!=null)
            eosorgTOrganizationForm.setLinkmantel(eosorgTOrganization.getLINKMANTEL());
        if(eosorgTOrganization.getMANAGERID()!=null)
            eosorgTOrganizationForm.setManagerid(eosorgTOrganization.getMANAGERID());
        if(eosorgTOrganization.getMemo()!=null)
            eosorgTOrganizationForm.setMemo(eosorgTOrganization.getMemo());
        if(eosorgTOrganization.getOrgAddress()!=null)
            eosorgTOrganizationForm.setOrgAddress(eosorgTOrganization.getOrgAddress());
        if(eosorgTOrganization.getOrgClass()!=null)
            eosorgTOrganizationForm.setOrgClass(eosorgTOrganization.getOrgClass());
        if(eosorgTOrganization.getOrgCode()!=null)
            eosorgTOrganizationForm.setOrgCode(eosorgTOrganization.getOrgCode());
        if(eosorgTOrganization.getORGLEVEL()!=null)
            eosorgTOrganizationForm.setOrglevel(eosorgTOrganization.getORGLEVEL());
        if(eosorgTOrganization.getOrgName()!=null)
            eosorgTOrganizationForm.setOrgName(eosorgTOrganization.getOrgName());
        if(eosorgTOrganization.getORGSCOPE()!=null)
            eosorgTOrganizationForm.setOrgscope(eosorgTOrganization.getORGSCOPE());
        if(eosorgTOrganization.getOrgSEQ()!=null)
            eosorgTOrganizationForm.setOrgSeq(eosorgTOrganization.getOrgSEQ());
        if(eosorgTOrganization.getOrgTypeID()!=null)
            eosorgTOrganizationForm.setOrgTypeId(eosorgTOrganization.getOrgTypeID());
        if(eosorgTOrganization.getPositionID()!=null)
            eosorgTOrganizationForm.setPositionId(eosorgTOrganization.getPositionID());
        if(eosorgTOrganization.getrec_datachg_num()!=null)
            eosorgTOrganizationForm.setRecDatachgNum(eosorgTOrganization.getrec_datachg_num());
        if(eosorgTOrganization.getsend_datachg_num()!=null)
            eosorgTOrganizationForm.setSendDatachgNum(eosorgTOrganization.getsend_datachg_num());
        if(eosorgTOrganization.getSortAll()!=null)
            eosorgTOrganizationForm.setSortAll(eosorgTOrganization.getSortAll());
        if(eosorgTOrganization.getSortID()!=null)
            eosorgTOrganizationForm.setSortId(eosorgTOrganization.getSortID());
        if(eosorgTOrganization.getUserGroupID()!=null)
            eosorgTOrganizationForm.setUserGroupId(eosorgTOrganization.getUserGroupID());
        if(eosorgTOrganization.getWebURL()!=null)
            eosorgTOrganizationForm.setWebUrl(eosorgTOrganization.getWebURL());
        if(eosorgTOrganization.getLogo()!=null)
            eosorgTOrganizationForm.setLogo(eosorgTOrganization.getLogo());
        if(eosorgTOrganization.getStatus()!=null)
            eosorgTOrganizationForm.setStatus(eosorgTOrganization.getStatus());
        if(eosorgTOrganization.getTenantId()!=null){
            eosorgTOrganizationForm.setTenantId(eosorgTOrganization.getTenantId());
        }
        if(eosorgTOrganization.getParentOrgID()!=null){
            int parentOrgId = eosorgTOrganization.getParentOrgID();
            String parentOrgName = this.findById(parentOrgId).getOrgName();
            eosorgTOrganizationForm.setParentOrgId(parentOrgId);
            eosorgTOrganizationForm.setParentOrgName(parentOrgName);
        }else{

        }
        if(eosorgTOrganization.getAttachedOrgId()!=null
                &&eosorgTOrganization.getAttachedOrgId().intValue()!=0){
            int attachedOrgId = eosorgTOrganization.getAttachedOrgId();
            String attachedOrgName = this.findById(attachedOrgId).getOrgName();
            eosorgTOrganizationForm.setAttachedOrgName(attachedOrgName);
            eosorgTOrganizationForm.setAttachedOrgId(attachedOrgId);
        }else{

        }
        return eosorgTOrganizationForm;
    }

    @Override
    public Collection<? extends BasicOrgForm> getSubOrgInfo(Map<String, Object> condition) {
        return eosorgTOrganizationMapper.getSubOrgInfo(condition);
    }

    @Override
    public List<BasicOrgForm> findByOrgCode(String orgCode, EosorgTOrganization tenantOrg) {
        Map<String,Object> condition =new HashMap<String,Object>();
        condition.put("orgseqLike",tenantOrg.getOrgSEQ()+"%");
        condition.put("orgCode",orgCode.trim());
        return this.eosorgTOrganizationMapper.findBasicInfoByCondition(condition);
    }

    @Override
    public List<BasicOrgForm> findBasicOrgInfoById(List<Integer> orgIdList) {
        Map<String,Object> condition =new HashMap<String,Object>();
        condition.put("orgIdList",orgIdList);
        String sqlQuery="where org.orgId in (:orgIdList) and org.status=1 ";
        return this.findByConditionSQL(sqlQuery,condition,null);
    }

    @Override
    public List<EosorgTOrganization> getListByOrgNameAndParentOrgIdAndStatus( String orgName,Long parentOrgId,Boolean status) {
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("orgName",orgName);
        parmMap.put("parentOrgId",parentOrgId);
        parmMap.put("status",status);

        return this.eosorgTOrganizationMapper.getListByOrgNameAndParentOrgIdAndStatus(parmMap);
    }

    @Override
    public List<EosorgTOrganization> findOneStepSubOrg(int[] orgIds) {

        List<Integer> integers = new ArrayList<>();
        if(orgIds.length>0){
            for(int i=0;i<orgIds.length;i++){
                integers.add(orgIds[i]);
            }
        }
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("orgIdList",integers);

        return this.eosorgTOrganizationMapper.findOneStepSubOrg(parmMap);
    }

    @Override
    public List<Integer> getorgidlist(int orgID){

        return this.eosorgTOrganizationMapper.findSubOrgById(orgID);
    }


}
