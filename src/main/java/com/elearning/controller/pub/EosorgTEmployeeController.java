package com.elearning.controller.pub;

import com.elearning.common.*;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.pub.UserForm;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.pojo.systemManage.UserRole;
import com.elearning.pojo.systemManage.VUserInfo;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.IUserRoleService;
import com.elearning.vo.BasicUserForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;
import sun.misc.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

/**
 *
 */
@Controller
@RequestMapping("/eosorgTEmployee/")
public class EosorgTEmployeeController {

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @RequestMapping("getEosorgTEmployeeByID.do")
    @ResponseBody
    public ServiceResponse getEosorgTEmployeeByID(Integer operatorID, Model model){
        EosorgTEmployee eosorgTEmployee = eosorgTEmployeeService.findById(operatorID);

        return ServiceResponse.createBySuccess(eosorgTEmployee);
    }


    /**
     * 获取某用户的详细信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("JSONFullDetail.do")
    @ResponseBody
    public ServiceResponse JSONFullDetail(HttpServletRequest request){
        StringBuffer sb=new StringBuffer("{");
        Boolean status=true;
        try {
            Integer operatorId = Integer.parseInt(request.getParameter("operatorId"));
            EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
            Integer adminOrgId = (Integer)request.getSession().getAttribute(Constants.ROOTORGID_KEY.toString());
            EosorgTOrganization adminOrg=this.eosorgTOrganizationService.findById(adminOrgId);
            Integer tenantId=null;
            if(eosoperator!=null){
                tenantId=eosoperator.getTenantId();
            }
            UserForm userForm = this.eosorgTEmployeeService.findByUserId(operatorId,tenantId);
            /*
            sb.append("\"isFull\":");
            Boolean isFull=false;
            if(userForm.getOrgSeq()!=null&&userForm.getOrgSeq().startsWith(adminOrg.getOrgSEQ())){
                isFull=true;
            }
            sb.append(isFull);
            sb.append(",\"userInfo\":");
            sb.append(BeanToJSONTool.getJSON(userForm));
            sb.append(",");
            */
            return ServiceResponse.createBySuccess(userForm);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResponse.createByErrorMessage("用户信息获取失败");
        }
    }

    /**
     * 获取学员组的基本信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("JSONDetails.do")
    @ResponseBody
    public ServiceResponse JSONDetails(HttpServletRequest request){

        String[] operatorIds = request.getParameterValues("operatorIds");
        List<UserForm> users = new ArrayList<>();
        if(operatorIds!=null && operatorIds.length>0){
            for(String operatorIdStr : operatorIds){
                try {
                    Integer operatorId=Integer.parseInt(operatorIdStr);
                    UserForm userForm = this.eosorgTEmployeeService.findByUserId(operatorId,null);
                    users.add(userForm);
                } catch (Exception e) {
                    continue;
                }
            }
        }

        return ServiceResponse.createBySuccess(users);
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("JSONDetailOfOperator.do")
    @ResponseBody
    public ServiceResponse JSONDetailOfOperator(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        Integer tenantId=null;
        if(eosoperator!=null){
            tenantId=eosoperator.getTenantId();
        }
        return ServiceResponse.createBySuccess(eosorgTEmployeeService.findByUserId(eosoperator.getOperatorId(),tenantId));

    }

    /**
     * 获取某机构的全部成员的简要信息
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getUserinfoOfSpecifiedOrg.do")
    @ResponseBody
    public ServiceResponse getUserinfoOfSpecifiedOrg(Integer orgId,HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer tenantId=null;
        if(eosoperator!=null){
            tenantId=eosoperator.getTenantId();
        }
        if(request.getParameter("orgId")!=null){
            orgId=Integer.parseInt(request.getParameter("orgId").trim());
        }
        else{
            Tenant tenant= CacheUtils.getTenant(tenantId);
            orgId=tenant.getOrgId();
        }
        try {
            List<BasicUserForm> userList=this.eosorgTEmployeeService.findBasicUserInfoByOrgId(orgId,tenantId,new HashMap<String, Object>());
            return ServiceResponse.createBySuccess(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResponse.createByErrorMessage("用户信息加载失败");
        }

    }


    /********************************************系统管理-人员管理 (start)**************************************************/

    /**
     * 在系统管理>人员管理中分页显示人员信息
     * @param operatorName
     * @param newEmployeeYear
     * @param isCtnEduRequire
     * @param status
     * @param startIndex
     * @param count
     * @param request
     * @param session
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("listUsers.do")
    @ResponseBody
    public ServiceResponse listUsersForAdmin(String orgseq,String operatorName,Integer newEmployeeYear,Integer isCtnEduRequire,Integer status, @RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                     @RequestParam(value = "count",defaultValue = "10")Integer count,
                                     HttpServletRequest request, HttpSession session){

        Map<String,Object> condition=new HashMap<String,Object>();
        condition.put("startIndex",startIndex);
        condition.put("count",count);
        condition.put("orgseqLike",orgseq+"%");
        if(operatorName!=null&&operatorName.trim().length()>0){
            condition.put("operatorName", "%"+operatorName+"%");
            request.setAttribute("operatorName",operatorName);
        }
        if(newEmployeeYear!=null){
            condition.put("newEmployeeYear",newEmployeeYear);
            request.setAttribute("newEmployeeYear",newEmployeeYear);
        }
        if(isCtnEduRequire!=null){
            condition.put("isCtnEduRequire",isCtnEduRequire);
            request.setAttribute("isCtnEduRequire",isCtnEduRequire);
        }
        if(status!=null){
            condition.put("status",status);
            request.setAttribute("status",status);
        }
        PageHelper.startPage((Integer)condition.get("startIndex"),(Integer) condition.get("count"));
        List<VUserInfo> userInfoList=this.eosorgTEmployeeService.getFullInfoList(condition);
        PageInfo pageInfo = new PageInfo(userInfoList);
        pageInfo.setList(userInfoList);
        return ServiceResponse.createBySuccess(pageInfo);
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("userListFrame.do")
    public String userListFrame(Integer orgId,HttpServletRequest request){
        HashMap map = new HashMap();
        EosorgTOrganization org=this.eosorgTOrganizationService.findById(orgId);
        request.setAttribute("status",org.getStatus());
        request.setAttribute("isVirOrg",org.getIsVirOrg());
        request.setAttribute("orgseq",org.getOrgSEQ());
        request.setAttribute("orgId",org.getOrgID());
        return "system/orgUserList";
    }


    /**
     * 批量修改多个用户的多个信息
     * @param title
     * @param job
     * @param isCtnEduRequire
     * @param orgId
     * @param regDate
     * @param status
     * @param operatorIds
     * @param request
     * @return
     */
    @IsCheckUserLogin(check=true)
    @RequestMapping("batchUpdate.do")
    @ResponseBody
    public ServiceResponse batchUpdate(String title,String job,Integer isCtnEduRequire,Integer orgId, Date regDate, Integer status,String[] operatorIds,HttpServletRequest request){
        EosOperator adminOperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        List<String> msgList=new ArrayList<String>();
        Integer successCount=0;
        Map<Integer,String> tenantOrgSEQMap=new HashMap<Integer,String>();
        for(String operatorId:operatorIds){
            Boolean canUpdate=true;
            EosorgTEmployee employee=this.eosorgTEmployeeService.findById(Integer.parseInt(operatorId.trim()));
            EosOperator operator=this.eosOperatorService.findById(employee.getOperatorID());
            UserForm userInfo=new UserForm(employee,operator);

            if(title!=null){
                employee.setTitle(title);
            }
            if(job!=null){
                employee.setJob(job);
            }
            if(isCtnEduRequire!=null){
                employee.setIsCtnEduRequire(isCtnEduRequire);
            }
            if(regDate!=null){
                employee.setRegDate(regDate);
                employee.setNewEmployeeYear(Integer.valueOf(Tools.getNowYear(regDate)));
            }

            if(status!=null&&status==1){
                String orgseq=tenantOrgSEQMap.get(userInfo.getOrgId());
                if(orgseq!=null);
                else{
                    Tenant tenant=this.eosorgTOrganizationService.findTenant(userInfo.getOrgId());
                    orgseq=CacheUtils.getTenantOrgSeq(tenant.getTenantId());
                    tenantOrgSEQMap.put(userInfo.getOrgId(),orgseq);
                }
                Map<String,Object> canResult=this.eosorgTEmployeeService.canUpdateUser(userInfo,orgseq);
                if((Boolean)canResult.get("status")){

                }
                else{
                    canUpdate=false;
                    msgList.add("用户"+operator.getOperatorName()+"信息修改失败"+canResult.get("msg"));
                }
            }
            if(canUpdate){
                Integer hasSuccess=0;
                if(orgId!=null){
                    //如果批量对人员的组织机构进行调整则启动深度更新
                    hasSuccess=this.eosorgTEmployeeService.deepUpdate(employee, adminOperator.getOperatorId());
                }
                else{
                    hasSuccess=this.eosorgTEmployeeService.update(employee,adminOperator.getOperatorId());
                }
                if(hasSuccess==0){
                    msgList.add("用户"+operator.getOperatorName()+"信息修改失败");
                }
                successCount+=hasSuccess;
            }
            msgList.add("共有用户"+successCount+"名信息修改成功");
        }
        return ServiceResponse.createBySuccess(msgList);
    }

    /**
     * 切换学员的某一属性
     * @return
     */
    @IsCheckUserLogin(check=true)
    @RequestMapping("toggleProp.do")
    @ResponseBody
    public ServiceResponse toggleProp(Integer operatorId,Boolean status,Boolean password,Boolean isCtnEduRequire,HttpServletRequest request){

        EosOperator adminOperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        String message=null;
        Integer changeCount=0;

        if(status!=null){
            //学员状态改变
            EosOperator operator=this.eosOperatorService.findById(operatorId);
            if(operator.getStatus()==null){
                operator.setStatus(0);
            }
            Integer newStatus=1-operator.getStatus();
            Boolean updatable=true;
            if(newStatus==1){
                EosorgTEmployee employee=this.eosorgTEmployeeService.findById(operator.getOperatorId());
                Tenant tenant=this.eosorgTOrganizationService.findTenant(employee.getOrgID());
                EosorgTOrganization tenantOrg=this.eosorgTOrganizationService.findById(tenant.getOrgId());
                UserForm userInfo=new UserForm(employee,operator);
                Integer orgId=userInfo.getOrgId();
                Map<String,Object> canUpdateInfo=this.eosorgTEmployeeService.canUpdateUser(userInfo,tenantOrg.getOrgSEQ());
                updatable=(Boolean)canUpdateInfo.get("status");
                if(updatable){
                    operator.setStatus(newStatus);
                    changeCount=this.eosorgTEmployeeService.update(operator,adminOperator.getOperatorId());
                }
                else{
                    message=canUpdateInfo.get("msg").toString();
                }
            }

            if(changeCount>0){
                message="学员"+operator.getOperatorName()+"已切换为"+(operator.getStatus()==1?"有效":"无效");
            }
        }
        else if(password!=null){
            //密码重置
            EosOperator operator=this.eosOperatorService.findById(operatorId);
            operator.setPassword(new BASE64Encoder().encodeBuffer(Constants.password.getBytes()).replace("\r", "").replace("\n", ""));
            operator.setPwdModifyDate(new Date());
            changeCount=this.eosorgTEmployeeService.update(operator,adminOperator.getOperatorId());
            if(changeCount>0){
                message="学员"+operator.getOperatorName()+"已重置密码为初始密码："+Constants.password;
            }
        }
        else if(isCtnEduRequire!=null){
            //是否有学时要求
            EosorgTEmployee employee=this.eosorgTEmployeeService.findById(operatorId);
            if(employee.getIsCtnEduRequire()==null){
                employee.setIsCtnEduRequire(0);
            }
            employee.setIsCtnEduRequire(1-employee.getIsCtnEduRequire());
            changeCount=this.eosorgTEmployeeService.update(employee,adminOperator.getOperatorId());
            if(changeCount>0){
                message="已修改学员类型为"+(employee.getIsCtnEduRequire()==1?"有学时要求":"无学时要求");;
            }
        }
        if(changeCount>0){
            return ServiceResponse.createBySuccess(message);
        }
        else{
            return ServiceResponse.createByErrorMessage(message);
        }
    }

    /**
     * 判断是否可以添加/修改用户信息
     * @param userForm
     * @return
     */
    public ServiceResponse canUpdateUser(UserForm userForm,Integer orgId){
        Tenant tenant=this.eosorgTOrganizationService.findTenant(orgId);
        EosorgTOrganization tenantOrg=this.eosorgTOrganizationService.findById(tenant.getOrgId());
        Map<String,Object> result=this.eosorgTEmployeeService.canUpdateUser(userForm,tenantOrg.getOrgSEQ());
        return ServiceResponse.createBySuccess(result);
    }

    /**
     * 在用户基本信息编辑页面更新某用户基本信息
     * @param userForm
     * @param request
     * @return
     */
    @IsCheckUserLogin(check=true)
    @RequestMapping("updateUser.do")
    @ResponseBody
    public ServiceResponse updateUser(UserForm userForm,Integer orgId,HttpServletRequest request){
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        try {
            userForm.setLastModifyTime(Tools.DateSecondToString(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(operator != null && operator.getOperatorId() != null){
            userForm.setLastModifyUserId(operator.getOperatorId());
        }

        try {
            Map<String,Object> result=null;
            Tenant tenant=this.eosorgTOrganizationService.findTenant(orgId);
            EosorgTOrganization tenantOrg=this.eosorgTOrganizationService.findById(tenant.getOrgId());
            userForm.setLastModifyUserId(operator.getOperatorId());
            Integer originalOperatorId=userForm.getOperatorId();
            result=this.eosorgTEmployeeService.updateUser(userForm,tenantOrg.getOrgSEQ(),operator.getOperatorId());

            if((Boolean)result.get("status")){
                if(originalOperatorId==null){
                    this.userRoleService.createStudentRole(userForm.getOperatorId(),operator.getOperatorId(),null);
                }
                else{
                }
                return ServiceResponse.createBySuccess("用户信息编辑成功",result.get("userForm"));
            }
            else{
                return ServiceResponse.createByErrorMessage(result.get("msg").toString());
            }

        } catch (Exception e) {
            String message="系统异常，用户信息修改失败！";
            e.printStackTrace();
            return ServiceResponse.createByErrorMessage(message);
        }
    }


    /**
     * 进入学员信息编辑页面
     * @param operatorId
     * @param request
     * @return
     */
    @IsCheckUserLogin(check=true)
    @RequestMapping("forAddOrUpdateUser.do")
    public String forUpdateUser(Integer operatorId,Integer orgId,HttpServletRequest request,HttpSession session) throws ParseException {
        UserForm userInfo=null;
        EosorgTOrganization org = this.eosorgTOrganizationService.findById(orgId);
        if(operatorId!=null){
            userInfo=this.eosorgTEmployeeService.findByUserId(operatorId);
            Integer changable = 1;
            if(org!=null&&org.getIsVirOrg()==1){
                //判断机构名称属性是否可修改
                changable = 0;
            }
            request.setAttribute("changable", changable);
        }
        else{
            userInfo =new UserForm();
            userInfo.setOrgId(org.getOrgID());
        }
        request.setAttribute("orgName", org.getOrgName()!=null?org.getOrgName():"");
        request.setAttribute("orgCode", org.getOrgCode()!=null?org.getOrgCode():"");
        request.setAttribute("user",userInfo);
        return "system/userforupdate";
    }

    /********************************************系统管理-人员管理 (end)**************************************************/


}
