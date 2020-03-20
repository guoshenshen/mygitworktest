package com.elearning.controller.systemManage;


import com.elearning.common.CacheUtils;
import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.systemManage.PageSetting;
import com.elearning.service.systemManage.IPageSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/system/")
public class SystemController {

    @Autowired
    private IPageSettingService pageSettingService;


    /**
     * 进入部门管理
     * @param request
     * @return
     * @throws IOException
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "orgManage.do")
    public String orgManage(HttpServletRequest request) throws IOException {
        Integer orgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        request.setAttribute("orgId", orgId);
        request.setAttribute("targetUrl", "/eosorgTOrganization/detail.do?");
        return "system/defaultTreeFrame";
    }


    /**
     * 进入用户管理
     * @param request
     * @return
     * @throws IOException
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "userManage.do")
    public String userManage(HttpServletRequest request) throws IOException {
        Integer orgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        request.setAttribute("orgId", orgId);
        request.setAttribute("targetUrl", "/eosorgTEmployee/userListFrame.do?");
        return "system/defaultTreeFrame";
    }

    /**
     * 进入页面默认设置
     * @param request
     * @return
     * @throws IOException
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "pageSettingManage.do")
    public String pageSettingManage(HttpServletRequest request) throws IOException {
        Integer pageSize = 5;
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer operatorId=0;
        if(operator!=null)
            operatorId = operator.getOperatorId();
        PageSetting pageSetting = null;
        if(operatorId!=0)
            pageSetting = this.pageSettingService.findByOperatorId(operatorId);
        if(pageSetting != null){
            pageSize = pageSetting.getPageSize();
        }
        request.setAttribute("pageSize", pageSize);
        int flag = 0;
        if(request.getParameter("flag")!=null && !request.getParameter("flag").equals(""))
            flag = Integer.parseInt(request.getParameter("flag"));
        request.setAttribute("flag", flag);

        return "system/pageSetting";
    }

    /**
     * 进入角色管理页面
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "roleFrame.do")
    public String roleFrame(HttpServletRequest request){
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        request.setAttribute("login_user",operator);
        return "system/roleList";
    }



    @IsCheckUserLogin(check = true)
    @ResponseBody
    @RequestMapping(value = "pageSettingSave.do")
    public ServiceResponse pageSettingSave(HttpServletRequest request,Integer pageSize) throws IOException {

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer operatorId=0;
        PageSetting pageSetting=null;
        if(operator!=null)
            operatorId = operator.getOperatorId();
        if(operatorId != 0)
            pageSetting = this.pageSettingService.findByOperatorId(operatorId);
        Boolean result=true;
        if(pageSetting != null){
            pageSetting.setPageSize(pageSize);
            result=this.pageSettingService.update(pageSetting);
        }
        else{
            pageSetting = new PageSetting();
            pageSetting.setOperatorID(operatorId);
            pageSetting.setPageSize(pageSize);
            result=this.pageSettingService.save(pageSetting);
        }
        if(result){
            CacheUtils.updatePageSetting(pageSetting);
            request.getSession().setAttribute("defaultPageSize", pageSize);
            return ServiceResponse.createBySuccessMessage("页面默认设置成功！");
        }
        else{
            return ServiceResponse.createByErrorMessage("页面默认设置失败");
        }
    }

}
