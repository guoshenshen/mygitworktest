package com.elearning.controller.systemManage;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.systemManage.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/6/24 10:03
 */
@Controller
@RequestMapping("/tenant/")
public class TenantController {

    @Autowired
    private ITenantService tenantService;

    @RequestMapping("add.do")
    public String addTenant(Tenant tenant){
        int result = 0;
        try {
            result = tenantService.addTenant(tenant);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result > 0 ){
            return "test/success";
        }else {
            return "test/error";
        }
    }


    @RequestMapping("select.do")
    @ResponseBody
    public ServiceResponse selectTenant(Integer tenantId){
        if(tenantId == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return ServiceResponse.createBySuccess(tenantService.findById(tenantId));
    }

    @RequestMapping("all.do")
    @ResponseBody
    public ServiceResponse all(@RequestParam(value = "num",defaultValue = "1") Integer num ,
                                @RequestParam(value = "size",defaultValue = "10") Integer size){

        return tenantService.all(num, size);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("findTenantName.do")
    @ResponseBody
    public ServiceResponse findTenantName(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.tenantService.findTenantName(request);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("findTenantId.do")
    @ResponseBody
    public ServiceResponse findTenantId(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.tenantService.findTenantId(request);
    }





}
