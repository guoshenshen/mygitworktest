package com.elearning.service.pub;

import com.elearning.common.CacheUtils;
import com.elearning.common.DAOTool;
import com.elearning.dao.pub.EosOperatorMapper;
import com.elearning.dao.pub.EosorgTEmployeeMapper;
import com.elearning.dao.systemManage.VUserInfoMapper;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.pub.UserForm;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.pojo.systemManage.VUserInfo;
import com.elearning.vo.BasicUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("eosorgTEmployeeService")
public class EosorgTEmployeeServiceImpl implements IEosorgTEmployeeService {

    @Autowired
    private VUserInfoMapper vUserInfoMapper;

    @Autowired
    private EosOperatorMapper eosOperatorMapper;

    @Autowired
    private EosorgTEmployeeMapper eosorgTEmployeeMapper;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IEosOperatorService eosOperatorService;


    public void getFullInfoByBasicInfo(EosorgTEmployee employee){
        EosOperator operator=null;
        EosorgTOrganization org=null;
        if(employee.getEosoperator()==null){
            operator=this.eosOperatorService.findById(employee.getOperatorID());
            employee.setEosoperator(operator);
        }
        else{
            operator=employee.getEosoperator();
        }
        if(employee.getEosorgTOrganization()==null){
            org=this.eosorgTOrganizationService.getEosorgTOrganizationById(employee.getOrgID());
            employee.setEosorgTOrganization(org);
        }
        operator.setTenantId(org.getTenantId());
    }


    public EosorgTEmployee findById(Integer operatorID) {

        return eosorgTEmployeeMapper.selectByPrimaryKey(operatorID);
    }

    @Override
    public UserForm findByUserId(Integer operatorId){
        EosorgTEmployee _eosorgTEmployee = eosorgTEmployeeMapper.selectByPrimaryKey(operatorId);
        this.getFullInfoByBasicInfo(_eosorgTEmployee);
        UserForm _tempUserForm=new UserForm(_eosorgTEmployee,_eosorgTEmployee.getEosoperator());
        return _tempUserForm;
    }


    @Override
    public UserForm findByUserId(Integer operatorId, Integer adminTenantId) {
        EosorgTEmployee _eosorgTEmployee = eosorgTEmployeeMapper.selectByPrimaryKey(operatorId);

        //EosOperator _tempOperator = _eosorgTEmployee.getEosoperator();
        //EosOperator _tempOperator = eosOperatorService.selectByPrimaryKey(operatorId);

        this.getFullInfoByBasicInfo(_eosorgTEmployee);
        UserForm _tempUserForm=new UserForm(_eosorgTEmployee,_eosorgTEmployee.getEosoperator());
    /*
        UserForm _tempUserForm = new UserForm();
        if (_eosorgTEmployee != null && _tempOperator != null) {

            _tempUserForm.setOperatorId(_eosorgTEmployee.getOperatorId());
            if (_eosorgTEmployee.getAddress() != null){
                _tempUserForm.setAddress(_eosorgTEmployee.getAddress());
            }
            if (_eosorgTEmployee.getBirthDate() != null){
                try {
                    _tempUserForm.setBirthDate(DateTimeUtil.dateToStr(_eosorgTEmployee.getBirthDate()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (_eosorgTEmployee.getBpno() != null){
                _tempUserForm.setBpno(_eosorgTEmployee.getBpno());
            }
            if (_eosorgTEmployee.getCreateTime() != null){
                try {
                    _tempUserForm.setCreateTime(DateTimeUtil.dateToStr(_eosorgTEmployee.getCreateTime()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (_eosorgTEmployee.getCustcolumn() != null){
                _tempUserForm.setCustcolumn(_eosorgTEmployee.getCustcolumn());
            }
            if (_eosorgTEmployee.getDegree() != null){
                _tempUserForm.setDegree(_eosorgTEmployee.getDegree());
            }
            if (_eosorgTEmployee.getEmpCode() != null){
                _tempUserForm.setEmpCode(_eosorgTEmployee.getEmpCode());
            }
            if (_eosorgTEmployee.getFaxNo() != null){
                _tempUserForm.setFaxNo(_eosorgTEmployee.getFaxNo());
            }
            if (_eosorgTEmployee.getGender() != null){
                _tempUserForm.setGender(_eosorgTEmployee.getGender());
            }
            if (_eosorgTEmployee.getHaddress() != null){
                _tempUserForm.setHaddress(_eosorgTEmployee.getHaddress());
            }
            if (_eosorgTEmployee.getHobby() != null){
                _tempUserForm.setHobby(_eosorgTEmployee.getHobby());
            }
            if (_eosorgTEmployee.getOemail() != null){
                _tempUserForm.setOemail(_eosorgTEmployee.getOemail());
            }
            if (_eosorgTEmployee.getHtel() != null){
                _tempUserForm.setHtel(_eosorgTEmployee.getHtel());
            }
            if (_eosorgTEmployee.getOtel1() != null){
                _tempUserForm.setOtel1(_eosorgTEmployee.getOtel1());
            }
            if (_eosorgTEmployee.getMobileNo() != null){
                _tempUserForm.setMobileNo(_eosorgTEmployee.getMobileNo());
            }
            if (_eosorgTEmployee.getRegDate() != null) {
                try {
                    _tempUserForm.setRegDate(DateTimeUtil.dateToStr(_eosorgTEmployee.getRegDate()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (_eosorgTEmployee.getNewEmployeeYear() != null){
                _tempUserForm.setNewEmployeeYear(_eosorgTEmployee.getNewEmployeeYear());
            }
            if (_eosorgTEmployee.getLastModifyTime() != null) {
                try {
                    _tempUserForm.setLastModifyTime(DateTimeUtil.dateToStr(_eosorgTEmployee.getLastModifyTime()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (_eosorgTEmployee.getTitle() != null){
                _tempUserForm.setTitle(_eosorgTEmployee.getTitle());
            }
            if (_eosorgTEmployee.getJob() != null){
                _tempUserForm.setJob(_eosorgTEmployee.getJob());
            }
            if (_eosorgTEmployee.getPosition() != null){
                _tempUserForm.setPosition(_eosorgTEmployee.getPosition());
            }
            if (_eosorgTEmployee.getType() != null){
                _tempUserForm.setType(_eosorgTEmployee.getType());
            }
            if (_eosorgTEmployee.getEmailHost() != null){
                _tempUserForm.setEmailHost(_eosorgTEmployee.getEmailHost());
            } else{
                _tempUserForm.setEmailHost("smtp.cnic.ac.cn");
            }
            if (_eosorgTEmployee.getEmailPwd() != null){
                try {
                    _tempUserForm.setEmailPwd(new String(new BASE64Decoder().decodeBuffer(_eosorgTEmployee.getEmailPwd())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (_eosorgTEmployee.getEmailSetEnable() != null){
                _tempUserForm.setEmailSetEnable(_eosorgTEmployee.getEmailSetEnable());
            }else{
                _tempUserForm.setEmailSetEnable(0);//默认不启用个人邮箱发邮件
            }
            if (_eosorgTEmployee.getIsKeyFigure() != null){
                _tempUserForm.setIsKeyFigure(_eosorgTEmployee.getIsKeyFigure());
            } else{
                _tempUserForm.setIsKeyFigure(0);//默认不是骨干人员
            }
            if (_eosorgTEmployee.getOrgId() != null){
                _tempUserForm.setOrgId(_eosorgTEmployee.getOrgId());
            }
            if (_eosorgTEmployee.getIsTip() != null){
                _tempUserForm.setIsTip(_eosorgTEmployee.getIsTip());
            }

            //getEosorgTOrganization()
            //EosorgTOrganization belongOrg = _eosorgTEmployee.getEosorgTOrganization();

            EosorgTOrganization belongOrg = this.eosorgTOrganizationService.getEosorgTOrganizationById(_eosorgTEmployee.getOrgId());

            //==========================================================================================================
            /*
            String orgName = "";
            Integer orgTenantId=null;
            if(belongOrg.getOrgID()!=null){
                Tenant tenant=null;
                if(belongOrg.getOrgID()!=null){
                    EosorgTOrganization org=eosorgTOrganizationMapper.selectByPrimaryKey(belongOrg.getOrgID());
                    if(org.getTenantId()!=null){
                        tenant=tenantMapper.selectByPrimaryKey(org.getTenantId());
                    } else{

                        List<Integer> orgIdList=new ArrayList<>();
                        String[] orgs=org.getOrgSEQ().split("\\.");
                        for(String orgStr:orgs){
                            orgIdList.add(Integer.parseInt(orgStr));
                        }

                        Map<String,Object> condition=new HashMap<>();
                        List<EosorgTOrganization> parentOrgs;
                        if(orgIdList.size()>0){
                            condition.put("orgIdList", orgIdList);
                            parentOrgs  = this.eosorgTOrganizationMapper.getListByIds(condition);

                            if(parentOrgs!=null){
                                EosorgTOrganization tenantOrg=parentOrgs.get(0);
                                tenant=tenantMapper.selectByPrimaryKey(org.getTenantId());
                            }
                        }
                    }
                }

                if(tenant!=null){
                    orgTenantId=tenant.getTenantId();
                }
            }

            belongOrg.setTenantId(orgTenantId);

            if(orgTenantId.equals(adminTenantId)){
                orgName=belongOrg.getOrgName();
            } else{
                Tenant searchTenant= tenantMapper.selectByPrimaryKey(orgTenantId);
                if(searchTenant!=null){
                    orgName = searchTenant.getTenantName();
                }
            }*/

            //==============================================================================================
           /* if(belongOrg.getOrgName()!=null){
                _tempUserForm.setOrgName(belongOrg.getOrgName());
            }
            _tempUserForm.setOrgName(belongOrg.getOrgName());
            _tempUserForm.setLogo(belongOrg.getLogo());

            if (_tempOperator.getOperatorName() != null){
                _tempUserForm.setOperatorName(_tempOperator.getOperatorName());
            }
            if (_tempOperator.getUserId()!= null){
                _tempUserForm.setUserId(_tempOperator.getUserId());
            }
            if (_tempOperator.getStatus() != null) {
                _tempUserForm.setStatus(_tempOperator.getStatus());
            }
            _tempUserForm.setPassword(_tempOperator.getPassword());
            _tempUserForm.setPwdModifyDate(_tempOperator.getPwdModifyDate());

        }*/
        return _tempUserForm;

    }

    @Override
    public List<VUserInfo> getFullInfoList(Map<String, Object> condition) {
        List<VUserInfo> userInfos = this.vUserInfoMapper.queryUserList(condition);
        return userInfos;
    }


    @Override
    public List<BasicUserForm> findBasicUserInfoByOrgId(Integer orgId, Integer tenantId,Map<String,Object> condition) {
        Map<String,Object> conditionForMapper=new HashMap<>();
        EosorgTOrganization org=this.eosorgTOrganizationService.findById(orgId);
        conditionForMapper.put("orgseq",org.getOrgSEQ()+"%");
        if(condition.containsKey("status")){
            conditionForMapper.put("status",condition.get("status"));
        }
        else{
            conditionForMapper.put("status",1);
        }
        StringBuffer sqlQuery=new StringBuffer(" where org.orgseq like :orgseq and operator.status=:status ");
        return this.findBasicUserInfoByCondition(sqlQuery.toString(),tenantId,conditionForMapper,null);
    }

    @Override
    public List<BasicUserForm> findBasicUserInfoById(List<Integer> operatorIdList, Integer tenantId) {
        StringBuffer sqlQuery=new StringBuffer(" where employee.operatorId in :operatorIdList ");
        Map<String,Object> conditionForMapper=new HashMap<>();
        conditionForMapper.put("operatorIdList",operatorIdList);
        return this.findBasicUserInfoByCondition(sqlQuery.toString(),tenantId,conditionForMapper,null);
    }


    @Override
    public List<BasicUserForm> findBasicUserInfoByCondition(String sqlQuery, Integer tenantId, Map<String, Object> condition,String orderPageString) {
        Map<String,Object> conditionForMapper=new HashMap<String,Object>();
        conditionForMapper.put("sqlQuery",DAOTool.setParameterValue(sqlQuery,condition));
        conditionForMapper.put("orderPageString",orderPageString);
        List<BasicUserForm> userList= this.eosorgTEmployeeMapper.findBasicUserInfoByCondition(conditionForMapper);
        if(userList!=null){
            for(BasicUserForm user:userList){
                if(user.getTenantId()!=null){
                    if(tenantId!=null&&user.getTenantId().equals(tenantId));
                    else{
                        user.setOrgName(CacheUtils.getTenantName(user.getTenantId()));
                    }
                }
            }
        }
        return userList;
    }

    @Override
    public Integer add(EosorgTEmployee eosorgTEmployee,Integer adminOperatorId){
        Integer operatorId=Math.abs(new Long(new Date().getTime()).intValue());
        if(eosorgTEmployee.getEosoperator()==null){
            return null;
        }
        else{
            Date currentTime=new Date();
            eosorgTEmployee.setOperatorID(operatorId);
            eosorgTEmployee.setLastModifyTime(currentTime);
            eosorgTEmployee.setCreateTime(currentTime);
            eosorgTEmployee.setLastModifyUserId(adminOperatorId);
            EosOperator operator=eosorgTEmployee.getEosoperator();
            operator.setOperatorId(operatorId);
            this.eosOperatorMapper.insert(operator);
            return this.eosorgTEmployeeMapper.insert(eosorgTEmployee);
        }
    }

    @Override
    public Integer update(EosorgTEmployee eosorgTEmployee, Integer adminOperatorId) {
        Date currentTime=new Date();
        eosorgTEmployee.setLastModifyTime(currentTime);
        eosorgTEmployee.setLastModifyUserId(adminOperatorId);
        return this.eosorgTEmployeeMapper.updateByPrimaryKey(eosorgTEmployee);
    }

    @Override
    public Integer update(EosOperator operator, Integer adminOperatorId) {
        Integer operatorId=operator.getOperatorId();
        Integer count=this.eosOperatorMapper.updateByPrimaryKey(operator);
        if(count>0){
            EosorgTEmployee employee=this.findById(operatorId);
            this.update(employee,adminOperatorId);
        }
        return count;
    }

    @Override
    public Integer deepUpdate(EosorgTEmployee employee, Integer adminOperatorId) {
        return this.update(employee,adminOperatorId);
    }

    @Override
    public EosorgTOrganization findOrg(EosorgTEmployee employee, Boolean isOrgStatus) {
        EosorgTOrganization org=null;
        if(employee==null);
        else{
            org=this.eosorgTOrganizationService.findById(employee.getOrgID());
            if(isOrgStatus==null||!isOrgStatus){

            }
            else{
                //如果当前机构是独立组织机构
                if(org.getIsOrg()!=null&&org.getStatus()!=null&&org.getStatus().toString().equals("1"));

                else{
                    org=this.eosorgTOrganizationService.findParentOrg(org);
                }
            }
        }
        return org;
    }

    @Override
    public Map<String, Object> canUpdateUser(UserForm userForm,String orgseq) {
        Map<String,Object> result=new HashMap<String,Object>();
        if(orgseq==null||orgseq.trim().length()==0){
            Tenant tenant=this.eosorgTOrganizationService.findTenant(userForm.getOrgId());
            EosorgTOrganization tenantOrg=this.eosorgTOrganizationService.findById(tenant.getOrgId());
            orgseq=tenantOrg.getOrgSEQ();
        }
        Boolean status=true;
        Map<String,Object> condition=new HashMap<String,Object>();
        StringBuffer sqlQuery=new StringBuffer("where (operator.userId=:userId ");
        condition.put("userId",userForm.getUserId());
        condition.put("status",1);
        if(userForm.getEmpCode()!=null&&userForm.getEmpCode().trim().length()>0){
            sqlQuery.append("or (employee.empCode=:empCode and org.orgseq like :orgseq )");
            condition.put("empCode",userForm.getEmpCode());
            //获取当前单位隶属独立机构
            condition.put("orgseq",orgseq+"%");
        }
        sqlQuery.append(") and operator.status=:status ");
        List<BasicUserForm> userList=this.findBasicUserInfoByCondition(sqlQuery.toString(),null,condition,null);
        if(userList.size()>0){
            if(userForm.getOperatorId()!=null){
                //修改用户信息
                if(userList.size()>1){
                    status=false;
                }
                else{
                    if(!userList.get(0).getOperatorId().equals(userForm.getOperatorId())){
                        status=false;
                    }
                    else{
                        status=true;
                    }
                }
            }
            else{
                //新添用户信息
                status=false;
            }
            if(!status){
                StringBuffer msg=new StringBuffer("");
                Integer index=1;
                Integer sameNameCount=0,sameEmpCodeCount=0;
                for(BasicUserForm user:userList){
                    if(user.getOperatorId().equals(userForm.getOperatorId()));
                    else{
                        if(user.getUserId().equals(userForm.getUserId())){
                            sameNameCount++;
                        }
                        if(userForm.getEmpCode()!=null&&userForm.getEmpCode().equals(user.getEmpCode())){
                            sameEmpCodeCount++;
                        }
                    }
                    if(sameNameCount>0){
                        msg.append("已有"+sameNameCount+"位用户的用户名与"+userForm.getUserId()+"冲突;");
                    }
                    if(sameEmpCodeCount>0){
                        msg.append("已有"+sameEmpCodeCount+"位用户的用户编号与"+userForm.getUserId()+"冲突;");
                    }
                    result.put("msg",msg);
                }
            }
        }
        else{
            //可以添加
            status=true;
        }
        result.put("status",status);
        return result;
    }

    @Override
    public Map<String, Object> updateUser(UserForm userForm, String orgseq,Integer adminOperatorId) {
        Map<String,Object> result=this.canUpdateUser(userForm,orgseq);
        if((Boolean)result.get("status")){
            EosorgTEmployee eosorgTEmployee=new EosorgTEmployee(userForm);
            EosOperator operator=new EosOperator(userForm);
            if(userForm.getOperatorId()!=null){
                //修改用户
                try {
                    this.update(eosorgTEmployee,adminOperatorId);
                    this.eosOperatorMapper.updateByPrimaryKey(operator);
                    result.put("userForm",userForm);
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("status",false);
                    result.put("msg","系统异常，用户"+userForm.getUserId()+"信息更新失败");
                }
            }
            else{
                //新增用户
                try {
                    eosorgTEmployee.setEosoperator( operator);
                    this.add(eosorgTEmployee,adminOperatorId);
                    result.put("userForm",userForm);
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("status",false);
                    result.put("msg","系统异常，用户"+userForm.getUserId()+"信息插入失败");
                }
            }
        }
        return result;
    }

    @Override
    public List<EosorgTEmployee> findByEmpCode(String empCode) {

        return this.eosorgTEmployeeMapper.findByEmpCode(empCode);
    }


}
