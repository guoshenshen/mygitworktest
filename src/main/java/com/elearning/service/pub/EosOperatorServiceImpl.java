package com.elearning.service.pub;

import com.elearning.common.Constants;
import com.elearning.dao.pub.EosOperatorMapper;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.systemManage.Log;
import com.elearning.service.systemManage.ILogService;
import com.elearning.service.systemManage.ISystemSettingService;
import com.elearning.service.systemManage.IUserRoleService;
import com.elearning.vo.BasicUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service("eosOperatorService")
public class EosOperatorServiceImpl implements IEosOperatorService {

    @Autowired
    private EosOperatorMapper eosOperatorMapper;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private ISystemSettingService systemSettingService;

    @Autowired
    private ILogService logService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    public void getFullInfoByBasicInfo(EosOperator operator){
        EosorgTEmployee eosorgTEmployee=null;
        if(operator.getEosorgTEmployee()==null){
            eosorgTEmployee = this.eosorgTEmployeeService.findById(operator.getOperatorId());
            operator.setEosorgTEmployee(eosorgTEmployee);
            eosorgTEmployee.setEosoperator(operator);
        }
        this.eosorgTEmployeeService.getFullInfoByBasicInfo(eosorgTEmployee);
    }


    public EosOperator selectByPrimaryKey(Integer ID){

        return eosOperatorMapper.selectByPrimaryKey(ID);
    }

    @Override
    public EosOperator findById(Integer operatorId) {
        return this.eosOperatorMapper.selectByPrimaryKey(operatorId);
    }

    @Override
    public EosOperator findByUserId(String userId) {
        return this.eosOperatorMapper.findByUserId(userId);
    }


    @Override
    public Boolean isValid(String userId, String password) {
        if(password==null) password = "";
        List<EosOperator> eosoperatorList = this.eosOperatorMapper.isValid(userId,new BASE64Encoder().encodeBuffer(password.getBytes()).replace("\r", "").replace("\n", ""));

        for(EosOperator operator:eosoperatorList){
            if(operator.getStatus().intValue()==1){
                return true;
            }
			/*
			Integer operatorId = operator.getOperatorId();
			if(this.userVorganizationDAO.isValid(operatorId)) return true;
			*/
        }
        return false;
    }

    public Boolean validateEnter(HttpServletRequest request) {
        Integer operatorId = 0;
        if(request.getParameter("operatorId")!=null && !request.getParameter("operatorId").toString().trim().equals("")){
            operatorId = Integer.valueOf(request.getParameter("operatorId").toString());
            EosOperator operator = this.findById(operatorId);
            if(operator!=null){
                String userRolesInString = this.userRoleService.getUserRolesInString(operator.getOperatorId(),Constants.tenantId);
                if(userRolesInString!=null){
                    operator.setUser_idcode(userRolesInString);    //将人员角色list存储到session 中
                } else{
                    operator.setUser_idcode("");
                }
                request.getSession(true).setAttribute(Constants.USERINFO_KEY, operator);

                //获取当前年份
                String year = "";
                if(this.systemSettingService.getCurrenTimeSetting()==null || this.systemSettingService.getCurrenTimeSetting().getYear().trim().equals("")){
                    year = String.valueOf(1900+new Date().getYear());
                } else{
                    year = this.systemSettingService.getCurrenTimeSetting().getYear();
                }
                request.getSession(true).setAttribute(Constants.YEAR_KEY, year);

                //将登陆事件存入数据库
                Log logonLog = new Log();
                logonLog.setAction("logon");
                logonLog.setOperatorID(operator.getOperatorId());
                logonLog.setCreatedate(new Date());
                this.logService.save(logonLog);

                //查找到登录用户的最大权限可以看到的组织机构树根节点，放到Tools.rootOrgId中
                Integer rootOrgId = this.findOrgId(operator.getOperatorId());
                //	Tools.getRootOrgIdMap().put(operator.getOperatorId(), rootOrgId);
                request.getSession(true).setAttribute(Constants.ROOTORGID_KEY, rootOrgId);
            }else{
                return false;
            }
        }
        return true;
    }

    //查找到登录用户的最大权限可以看到的组织机构树根节点
    public int findOrgId(int employeeID) {
        int orgId = 0;
        EosorgTEmployee employee=this.eosorgTEmployeeService.findById(employeeID);
        if(employee!=null){
            orgId = employee.getOrgID();
            return this.eosorgTOrganizationService.findOrgId(orgId);
        }
        return orgId;
    }

    @Override
    public List<BasicUserVo> findBasicUserInfoById(List<Integer> operatorIdList, Integer tenantId) {

        List<BasicUserVo> userFormList=new ArrayList<>();

        if(operatorIdList==null || operatorIdList.size()<=0){
            return userFormList;
        }

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("operatorIdList",operatorIdList);

        userFormList = this.eosOperatorMapper.findByConditionSQL(parmMap);

        return userFormList;
    }

    @Override
    public List<EosOperator> findByOperatorName(String operatorName){

        return this.eosOperatorMapper.findByOperatorName(operatorName);
    }




}
