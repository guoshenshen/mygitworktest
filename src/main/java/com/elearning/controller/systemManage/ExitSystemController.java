package com.elearning.controller.systemManage;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.systemManage.IExitSystemService;
import com.elearning.service.systemManage.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/exitSystem/")
public class ExitSystemController {

    @Autowired
    private IExitSystemService exitSystemService;

    @Autowired
    private ITenantService tenantService;



    @RequestMapping("logOut.do")
    @ResponseBody
    public ServiceResponse logOut(HttpServletRequest request){
        //=======================================================
        //Integer id=1;
        //return exitSystemService.selectByPrimaryKey(id);
        //=======================================================

        try {
            if(request.getSession() != null){//session未过期
                EosOperator eosoperator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);

                int operatorId=0;
                if(eosoperator!=null){
                    operatorId=eosoperator.getOperatorId();
                }
                request.getSession().invalidate();
                Constants.locationMap.remove(operatorId+"_"+"parentId");
                Constants.locationMap.remove(operatorId+"_"+"childId");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResponse.createByErrorMessage("");
        }finally{
            //String[] unredirectTenants=request.getParameterValues("unRedirect");
            //result.append("\"url\":");
            Map<String,Object> returnMap = new HashMap<>();

            String url="";
            Constants.tenantId=6;
            Tenant currentWebTenant=this.tenantService.findById(Constants.tenantId);

            System.out.println("---------------");
            System.out.println(currentWebTenant.getIsRedirectToPortalWeb());
            System.out.println("---------------");

            if(currentWebTenant.getIsRedirectToPortalWeb().equals(0) || currentWebTenant.getIsRedirectToPortalWeb().equals(-1)){
                //forward = mapping.findForward("logout");
                url="stuLogonAction.do";
                if(currentWebTenant.getTenantId().equals(1000)){
                    url="https://passport.escience.cn/logout?WebServerURL="+currentWebTenant.getEnterUrl();
                }
            } else{
                //forward = mapping.findForward("logoutBasicSystem");//   exitSystem.do?method=exitSystem
                //url=this.tenantService.findById(1000).getEnterUrl();//  +"exitSystem"
                url="http://localhost:8080/index.jsp";
            }
            returnMap.put("url",url);

            return ServiceResponse.createBySuccess(returnMap);
        }



        /*try {
            if(request.getSession() != null){//session未过期
                EosOperator eosoperator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);

                int operatorId=0;
                if(eosoperator!=null){
                    operatorId=eosoperator.getOperatorId();
                }
                request.getSession().invalidate();
                Constants.locationMap.remove(operatorId+"_"+"parentId");
                Constants.locationMap.remove(operatorId+"_"+"childId");

                result.append("\"result\":");
                result.append("\"true\",");


            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //String[] unredirectTenants=request.getParameterValues("unRedirect");
            result.append("\"url\":");

            String url="";
            Tenant currentWebTenant=this.getTenantService().findById(Constants.tenantId);
            if(currentWebTenant.getIsRedirectToPortalWeb().equals(0)||currentWebTenant.getIsRedirectToPortalWeb().equals(-1)){
                forward = mapping.findForward("logout");
                if(currentWebTenant.getTenantId().equals(1000)){
                    url="https://passport.escience.cn/logout?WebServerURL="+currentWebTenant.getEnterUrl();
                }
                else{
                    url=forward.getPath().substring(1);
                }
            }
            else{
                forward = mapping.findForward("logoutBasicSystem");
                url=this.tenantService.findById(1000).getEnterUrl()+forward.getPath();
            }
            result.append("\"");
            result.append(url);
            result.append("\"");
            result.append("}");
            response.getWriter().print(result.toString());
        }*/

    }















}
