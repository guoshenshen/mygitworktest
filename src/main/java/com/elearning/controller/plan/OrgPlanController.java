package com.elearning.controller.plan;

import com.elearning.common.CacheUtils;
import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.service.systemManage.IUserRoleService;
import com.elearning.vo.pub.BasicOrgForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/orgPlan/")
public class OrgPlanController {

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ITenantService tenantService;

    @RequestMapping("getUpreportOrgForTrain.do")
    @IsCheckUserLogin(check = true)
    @ResponseBody
    public ServiceResponse getUpreportOrgForTrain(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        //必须上报的机构
        List<BasicOrgForm> mustUpOrg=new ArrayList<BasicOrgForm>();

        //可选择上报的机构（即使该机构不审核，也可以推进计划-项目的实施）
        List<BasicOrgForm> chooseUpOrg=new ArrayList<BasicOrgForm>();

        try {
            Integer manageOrgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
            Tenant currentTenant= this.tenantService.findById(operator.getTenantId());

            if(currentTenant.getOrgId().equals(manageOrgId)&&(currentTenant.getTenantId().equals(3000))){
                //当前培训主办机构为科协顶层机构
                //设置科协顶层机构为默认可选择上报机构
            } else{
                EosorgTOrganization topOrg=this.eosorgTOrganizationService.findById(this.tenantService.findById(1000).getOrgId());
                mustUpOrg.add(this.eosorgTOrganizationService.getSimpleOrgInfo(topOrg));
            }
        } catch (Exception e) {
            ServiceResponse.createByError();
            e.printStackTrace();
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("mustUp",mustUpOrg);
        resultMap.put("chooseUp",chooseUpOrg);

        return ServiceResponse.createBySuccess(resultMap);
    }















}
