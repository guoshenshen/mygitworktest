package com.elearning.controller.pub;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.vo.BasicUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/eosOperator/")
public class EosOperatorController {

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private ITenantService tenantService;

    @RequestMapping("getEosOperatorServiceByID.do")
    @ResponseBody
    public ServiceResponse getEosOperatorServiceByID(Integer ID, Model model){

        EosOperator eosOperator = eosOperatorService.selectByPrimaryKey(ID);

        return ServiceResponse.createBySuccess(eosOperator);
    }

    //判断用户是否已经登录,并返回最基本信息（当前平台tenantId,tenantName,若用户已经登录,则返回该用户的基本信息userForm
    @RequestMapping("hasLogined.do")
    @ResponseBody
    public ServiceResponse hasLogined(HttpServletRequest request){

        Map<String,Object> resultMap = new HashMap<>();

        // 获取当前登录租户平台信息
        Integer currentTenantId = Constants.tenantId;

        if(currentTenantId == null || currentTenantId == 0){
            currentTenantId = 6;
        }
        Tenant currentTenant = this.tenantService.findById(currentTenantId);

        resultMap.put("tenantId",currentTenantId);
        resultMap.put("tenantName",currentTenant.getTenantName());

        // 如果用户登录则获取当前用户基本信息
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        if (eosoperator != null) {
            List<Integer> operatorIds = new ArrayList<>();
            operatorIds.add(eosoperator.getOperatorId());
            List<BasicUserVo> userForms = this.eosOperatorService.findBasicUserInfoById(operatorIds, currentTenantId);
            if (userForms != null && userForms.size() > 0) {
                resultMap.put("userForm",userForms.get(0));
            }
            Tenant operatorTenant = this.tenantService.findById(eosoperator.getTenantId());
            if (operatorTenant.getWorkingStatus() == null || operatorTenant.getWorkingStatus().equals((short) 0)) {
                resultMap.put("showVersionChange",false);
            } else {
                if (operatorTenant.getIsRedirectToPortalWeb().equals(1) && (currentTenantId == 1000 || currentTenant.getIsRedirectToPortalWeb().equals(1))) {
                    resultMap.put("showVersionChange",true);
                } else {
                    resultMap.put("showVersionChange",false);
                }
            }
            resultMap.put("userTenant",eosoperator.getTenantId());

        }


        return ServiceResponse.createBySuccess(resultMap);
    }


    /**
     * 判断当前操作人员是否处于在线状态
     * @param request
     * @return
     */
    @RequestMapping("ifOnline.do")
    @ResponseBody
    public ServiceResponse ifOnline(HttpServletRequest request){

        Map<String,Object> resultMap = new HashMap<>();
        if (request.getSession().getAttribute(Constants.USERINFO_KEY) != null) {
            EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
            resultMap.put("operatorId",operator.getOperatorId());
        } else {
            resultMap.put("operatorId",-1);
        }
        return ServiceResponse.createBySuccess(resultMap);
    }












}
