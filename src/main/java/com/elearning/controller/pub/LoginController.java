package com.elearning.controller.pub;


import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.common.SystemTool;
import com.elearning.common.Tools;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.*;
import com.elearning.pojo.systemManage.Log;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.pojo.systemManage.TimeSetting;
import com.elearning.service.pub.*;
import com.elearning.service.systemManage.*;
import com.elearning.util.PropertiesUtil;
import com.elearning.vo.pub.EosoperatorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author zq
 * @createDate 2019.08.01
 */
@Controller
@RequestMapping("/login/")
public class LoginController {

    @Autowired
    private IQRLoginService qrLoginService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ISessionCountService sessionCountService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IUserMenuService userMenuService;

    @Autowired
    private IDynamicLoginService dynamicLoginService;

    @Autowired
    private ILogService logService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private ISystemSettingService systemSettingService;

    @Autowired
    private IUserResourceService userResourceService;



    /**
     * @author zq@cnic.cn
     * 二维码扫码信息消触线程,若选择二维码登录模式登录50s内未完成扫码认证操作,将删除二维码数据库日志（点击扫码登录图标激活该线程）
     *
     */
    private class qrCodeDeleteThread implements Runnable{

        private QRLogin qrlogin;

        public qrCodeDeleteThread(QRLogin qrLoginInfo){
            this.qrlogin=qrLoginInfo;
        }

        @Override
        public void run() {
            try {
                System.out.println("启动qrcode删除任务,首先进入50s睡眠状态");
                Thread.currentThread().sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            qrLoginService.delete(qrlogin);
            System.out.println("qrcode删除任务已执行");
        }
    }

    //生成二维码标识信息
    @RequestMapping("generateQR")
    @ResponseBody
    public ServiceResponse generateQrLoginInfo(){
        QRLogin loginInstance=new QRLogin();
        StringBuffer qrInfo=new StringBuffer("");
        try {
            this.qrLoginService.save(loginInstance);
            Long randNum=loginInstance.getRandNum();
            qrCodeDeleteThread qrDeleteThread=new qrCodeDeleteThread(loginInstance);
            Thread qrThread=new Thread(qrDeleteThread);
            qrThread.start();
            return ServiceResponse.createBySuccess(randNum);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResponse.createByError();
        }
    }

    @RequestMapping("validate")
    @ResponseBody
    public ServiceResponse validateLegalUser(HttpServletRequest request,HttpSession session,EosoperatorForm eosoperatorForm){
        String loginType=request.getParameter("loginType");
        String isH5 = request.getParameter("isH5");
        Map<String,Object> result=new HashMap<String,Object>();
        if("1".equals(isH5)){
            session.setAttribute("courseVideo", "videoCourseWare_h5.jsp");
        }if("0".equals(isH5)){
            session.setAttribute("courseVideo", "videoCourseWare.jsp");
        }
        if(loginType!=null&&loginType.trim().equals("qrLogin")){
            //二维码扫码登录
            Long randNum=Long.parseLong(request.getParameter("randNum"));
            QRLogin example=this.qrLoginService.findByRandNum(randNum);
            if(example!=null){
                if(example.getIsChecked()!=null&&example.getIsChecked().equals(Short.parseShort("1"))){

                    Integer operatorId=example.getOperatorId();
                    EosOperator operator=null;
                    if(operatorId!=null){
                        operator=this.eosOperatorService.findById(operatorId);
                    }
                    if(operator!=null){
                        eosoperatorForm = new EosoperatorForm(operator);
                        this.init(request,  eosoperatorForm);
                        result.putAll(this.loginAdviceInfoForLegalUser(request));
                        result.put("ischecked",true);
                    }
                    else{
                        //二维码已被扫描确认，但用户身份有问题
                        result.put("result",true);
                        result.put("ischecked",false);
                    }
                }
                else{
                    //二维码有效但是尚未扫描
                    result.put("result",true);
                    result.put("ischecked",false);
                }
            }
            else{
                //二维码已经失效
                result.put("result",false);
                result.put("message","用户认证失败,二维码已失效");
            }
        }
        else{
            String userId = eosoperatorForm.getUserId();
            String password = eosoperatorForm.getPassword();
            String verifyCode = request.getParameter("verifyCode");
            if (verifyCode == null || verifyCode.trim().length() == 0
                    || session.getAttribute("rand") == null) {
                result.put("result",false);
                result.put("message","用户认证失败,请重新输入验证码");
            } else {
                String randCode = (String) request.getSession()
                        .getAttribute("rand");
                if (randCode.equalsIgnoreCase(verifyCode)) {
                    if (eosOperatorService.isValid(userId, password)) {

                        this.init(request,eosoperatorForm);
                        result.putAll(this.loginAdviceInfoForLegalUser(request));
						/*
						if(!password.equals(Constants.password) && Tools.isValidPassword(password)){
							this.init(request, mapping, eosoperatorForm);
							result.append(this.loginAdviceInfoForLegalUser(request));
						}else{
							//初始密码有时间限制，超过固定时间，初始密码失效
							Eosoperator eosoperator = getEosoperatorService().findByUserId(
									eosoperatorForm.getUserId());
							boolean isInitalPwdExceedTime = getEosoperatorService().isInitalPwdExceedTime(userId);
							if(password.equals(Constants.password) && isInitalPwdExceedTime){
								result.append("\"result\":\"false\"");
								result.append(",\"message\":\"").append("初始密码已失效,请点击<font style='font-weight:bold;'>忘记密码</font>")
										.append("\"");
								logger.info(new Date() + ":用户[" + userId + "]认证失败,初始密码已失效，请点击‘忘记密码’");
							}else{
								result.append(this.loginAdviceInfoForLegalUserNeedChangePwd(request,eosoperator.getOperatorId()));
							}
						}
						*/
                    } else {
                        result.put("result",false);
                        result.put("message","用户认证失败,请检查账号及密码");
                    }
                } else {
                    result.put("result",false);
                    result.put("message","用户认证失败,请重新输入验证码");
                }
            }
        }

        Boolean status=Boolean.valueOf(result.get("result").toString());
        String message=result.get("message").toString();
        if(status){
            result.remove("result");
            result.remove("message");
            return ServiceResponse.createBySuccess(message,result);
        }
        else{
            return ServiceResponse.createByErrorMessage(message);
        }
    }

    public void init(HttpServletRequest request, EosoperatorForm eosoperatorForm) {
        HttpSession session = request.getSession(true);

        // 查找现有session vector 中是否存在相同用户，保证同一时间一个帐号只能有一个人登录
        HttpSession existSession = this.sessionCountService.getSession(
                eosoperatorForm.getUserId());

        if (existSession != null) {
            this.sessionCountService.sessionInvalidate(
                    eosoperatorForm.getUserId());
        }
        EosOperator eosoperator = this.eosOperatorService.findByUserId(
                eosoperatorForm.getUserId());

        this.eosOperatorService.getFullInfoByBasicInfo(eosoperator);
        Tenant tenant = this.eosorgTOrganizationService.findTenant(eosoperator.getEosorgTEmployee().getOrgID());
        eosoperator.setTenantId(tenant.getTenantId());
        String userRolesInString = this.userRoleService
                .getUserRolesInString(eosoperator.getOperatorId(), SystemTool.currentTenant.getTenantId());
        eosoperator.setUser_idcode(userRolesInString);
        session.setAttribute(Constants.USERINFO_KEY, eosoperator);
        this.sessionCountService.addSession(session);
        // 处理非正常退出系统，路径显示错位问题
        if (Constants.locationMap.get(eosoperator.getOperatorId() + "_"
                + "parentId") != null)
            Constants.locationMap.remove(eosoperator.getOperatorId() + "_"
                    + "parentId");
        if (Constants.locationMap.get(eosoperator.getOperatorId() + "_"
                + "childId") != null)
            Constants.locationMap.remove(eosoperator.getOperatorId() + "_"
                    + "childId");

        // 获取当前年份
        String year = "";
        TimeSetting timeSetting=this.systemSettingService.getCurrenTimeSetting();
        if (timeSetting == null || timeSetting.getYear().trim().equals(""))
            year = String.valueOf(1900 + new Date().getYear());
        else
            year = timeSetting.getYear();
        session.setAttribute(Constants.YEAR_KEY, year);

        // 将登陆事件存入数据库
        Log logonLog = new Log();
        logonLog.setAction("logon");
        logonLog.setOperatorID(eosoperator.getOperatorId());
        logonLog.setOperatorName(eosoperator.getOperatorName());
        logonLog.setCreatedate(new Date());
        logonLog.setTenantId(SystemTool.currentTenant.getTenantId());
        logonLog.setTenantName(this.tenantService.findById(SystemTool.currentTenant.getTenantId())
                .getTenantName());
        this.logService.save(logonLog);

        HashMap map = this.userRoleService.queryUserRoleOrgId(
                eosoperator.getOperatorId(), SystemTool.currentTenant.getTenantId());
        request.getSession().setAttribute(Constants.USERROLE_KEY, map);
        int currentRoleId = 0;
        int studentRoleId = 0;
        if (map.size() > 0) {
            Set set = map.keySet();
            Iterator it = set.iterator();
            boolean hasStudentRole = false;
            while (it.hasNext()) {
                Integer roleId = (Integer) it.next();
                Integer studentOrAdminRole = (Integer) (map.get(roleId));
                if (studentOrAdminRole == -1) {//学员角色
                    studentRoleId = roleId;
                    hasStudentRole = true;
                }
                if (studentOrAdminRole.intValue() != -1) { //管理员角色
                    currentRoleId = roleId;
                    //以下注释部分为classupload平台所用的，部署时需打开
				   /* Integer orgId = (Integer) (map.get(roleId));
					request.getSession().setAttribute(Constants.ROOTORGID_KEY,
							orgId);*/
                    if(hasStudentRole){// 如果已经放入学员角色，则不必再循环，如果没有，则需遍历全部角色
                        break;
                    }
                }
            }
        }

        // 系统菜单静态化
        if (this.userMenuService.isHasMenuByOperatorIdAndTenantId(eosoperator.getOperatorId(),
                SystemTool.currentTenant.getTenantId())) {
            HashMap roleMenu_map = this.userRoleService
                    .queryUserRoleResourceList(eosoperator.getOperatorId());
            HashMap roleParent_map = this.userRoleService
                    .queryUserRoleParentResourceList(roleMenu_map);
            HashMap resourcerelation_map = this.userRoleService
                    .queryRoleParentAndChildResourceList(roleMenu_map);
            HashMap operatorIconResource_map = this.userResourceService
                    .queryOperatorResourceList(eosoperator.getOperatorId());
            if (operatorIconResource_map != null
                    && operatorIconResource_map.size() > 0)
                Tools.getOperatorIconmap().putAll(operatorIconResource_map);
            if (Constants.ICON_RESOURCE_lIST == null
                    || Constants.ICON_RESOURCE_lIST.size() == 0)
                Constants.ICON_RESOURCE_lIST = this.userResourceService
                        .queryIconResourceList();
            Tools.getRoleresourcemap().putAll(roleParent_map);
            Tools.getResourcerelationmap().putAll(resourcerelation_map);
            UserMenu userMenu = new UserMenu();
            userMenu.setOperatorId(eosoperator.getOperatorId());
            userMenu.setIsHasMenu(1);
            userMenu.setTenantId(SystemTool.currentTenant.getTenantId());
            //userMenu.setId(getUniqueId());
            this.userMenuService.insert(userMenu);
        }

        if(SystemTool.currentTenant.getTenantId() != 1005)
            currentRoleId = studentRoleId;

        request.getSession().setAttribute("userRoleId", currentRoleId);// -1
        // 代表学员
    }

    @RequestMapping("toLogin")
    @IsCheckUserLogin(check = true)
    public String logon(HttpServletRequest request,String loginType,String nextAction,EosoperatorForm eosoperatorForm) {
        Boolean passValidation=false;
        EosOperator operator=null;

        if (nextAction != null && nextAction.trim().length() > 0) {
            if(nextAction.contains("logonByAuth")){
                String key=request.getParameter("key");
                return "redirect:"+nextAction+"&key="+key;
            }
            else{
                request.setAttribute("nextAction", nextAction);
            }

        }
        if(loginType!=null&&loginType.trim().length()>0){
            //二维码扫码登录
            Long randNum=Long.parseLong(request.getParameter("randNum"));
            QRLogin example=this.qrLoginService.findByRandNum(randNum);
            Integer operatorId=example.getOperatorId();
            operator=this.eosOperatorService.findById(operatorId);
            eosoperatorForm = new EosoperatorForm(operator);
            passValidation=true;
        }
        else{
            //一般认证登录
            String userId = eosoperatorForm.getUserId();
            String password = eosoperatorForm.getPassword();
            passValidation=this.eosOperatorService.isValid(userId, password);
            if(passValidation){
                operator=this.eosOperatorService.findByUserId(userId);
                UserForm userForm=new UserForm(operator.getEosorgTEmployee(),operator);
                if(userForm!=null && password.equals(Constants.password)){//初始密码,pwdCode:0
                    request.setAttribute("pwdCode", 0);
                    request.setAttribute("user", userForm);
                    this.logoutSystem(request);
                    return "portal/setPsd.jsp";
                }else{
                    if(userForm!=null && !Tools.isValidPassword(password)){//非法密码,pwdCode:-1
                        request.setAttribute("pwdCode", -1);
                        request.setAttribute("user", userForm);
                        this.logoutSystem(request);
                        return "portal/setPsd.jsp";
                    }
                }
            }
        }


        if (passValidation) {
            this.init(request, eosoperatorForm);
            if (nextAction != null && nextAction.trim().length() > 0) {
                return "redirect："+nextAction.trim();
            }
            else{
                return "portal/loginSuccess";
            }

        } else {
            return "portal/loginError";
        }
    }


    public String intoPage(HttpServletRequest request)
            throws Exception {

        String targetUrl = null;
        try {
            Boolean autoLogin=true;
            targetUrl = "";
            String sendDateStr = request.getParameter("sendDate");
            if (sendDateStr != null && sendDateStr.trim().length() > 0) {
                // sendDateStr格式：yyyy-MM-dd
                Date sendDate = Tools.stringToDate(sendDateStr);
                // 一个月内的邮件免登陆
                if (Tools.daysBetween(sendDate, new Date()) <= 31) {

                }
                else{
                    autoLogin=false;
                }
            }
            if(autoLogin){
                String operatorIdStr = request.getParameter("operatorId");
                Integer operatorId = Integer.parseInt(operatorIdStr.trim());
                EosOperator operator = this.eosOperatorService.findById(operatorId);
                EosoperatorForm operatorForm = new EosoperatorForm(operator);
                init(request, operatorForm);
                EosorgTEmployee employee = operator.getEosorgTEmployee();
                Tenant tenant = this.eosorgTOrganizationService.findTenant(employee.getOrgID());
                if (tenant != null && tenant.getEnterUrl() != null
                        && tenant.getEnterUrl().trim().length() > 0) {
                    EosOperator eosoperator = (EosOperator) request
                            .getSession().getAttribute(
                                    Constants.USERINFO_KEY);
                    eosoperator.setTenantId(tenant.getTenantId());
                    request.getSession().setAttribute(
                            Constants.USERINFO_KEY, eosoperator);
                }
            }
            String targetAction = request.getParameter("target").trim();
            Map<String, String[]> params = request.getParameterMap();
            Set<String> pkeys = params.keySet();
            StringBuffer paramsStr = new StringBuffer("");
            targetUrl += targetAction;
            for (String pkey : pkeys) {
                if (pkey.equals("method") || pkey.equals("target")
                        || pkey.equals("sendDate")) {
                    continue;
                }
                String[] values = params.get(pkey);
                for (String value : values) {
                    paramsStr.append("&").append(pkey).append("=")
                            .append(value);
                }
            }
            targetUrl += paramsStr.toString();
            targetUrl = targetUrl.replaceAll(targetAction + "&", targetAction
                    + "?");
            System.out.println(targetUrl);

        } catch (Exception e) {
            e.printStackTrace();
            targetUrl = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath()
                    + "/";
        }
        return "redirect："+targetUrl;
    }




    /**
     * 用户身份认证通过后后台数据提示，引导前端给予用户正确的信息显示并跳转向正确的服务站点
     * @param request
     * @return
     */
    private Map<String,Object> loginAdviceInfoForLegalUser(HttpServletRequest request){


        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        Map<String,Object> result=new HashMap<String,Object>();

        //获取用户角色信息序列
        String userRolesInString = this.userRoleService
                .getUserRolesInString(operator.getOperatorId(),
                        SystemTool.currentTenant.getTenantId());

        if(userRolesInString.equals("")){
            result.put("result",false);
            result.put("message","用户认证失败,没有相应权限");
        }else{
            EosorgTEmployee employee = operator.getEosorgTEmployee();
            String headPicAddress = employee.getADDRESS();
            String userName = operator.getOperatorName();
            result.put("result",true);
            if (userName != null && userName.trim().length() != 0) {
                result.put("userName",userName.trim());
            } else {
            }
            String gender = "1";
            if (employee.getGender() != null) {
                gender = employee.getGender() + "";
            }
            result.put("gender",gender);
            if (headPicAddress != null
                    && headPicAddress.trim().length() != 0) {
                result.put("headPic",headPicAddress);
            } else {
            }

            DynamicLogin dlogin=new DynamicLogin();
            dlogin.setCreateTime(new Date());
            dlogin.setType(IDynamicLoginService.AUTH_TYPE);
            dlogin.setOperatorId(employee.getOperatorID());
            this.dynamicLoginService.save(dlogin);
            Long dynamicKey=dlogin.getDynamicKey();
            result.put("key",dynamicKey);

            String openUrl= SystemTool.getRootUrlForRedirection(operator.getTenantId());
            result.put("openUrl",openUrl);
            result.put("basicUrl","login/toLogin");
            result.put("message","用户认证通过,正在初始化用户信息...");
        }
        return result;
    }

    private void logoutSystem(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        int operatorId=0;
        if(eosoperator!=null){
            operatorId=eosoperator.getOperatorId();
        }
        request.getSession().invalidate();
        Constants.locationMap.remove(operatorId+"_"+"parentId");
        Constants.locationMap.remove(operatorId+"_"+"childId");
    }

}
