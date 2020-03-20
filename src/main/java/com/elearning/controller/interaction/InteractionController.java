package com.elearning.controller.interaction;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.interaction.IInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/interaction/")
public class InteractionController {

    @Autowired
    private IInteractionService interactionService;

    /**
     *  进入学员对外展示空间
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("intoUserZone.do")
    public String intoUserZone(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.interactionService.intoUserZone(request);

        jspName = "zone/userZoneFrame";

        return jspName;
    }

    /**
     * 显示某学员（机构）的基本信息以及该学员的关注、被关注情况
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getBasicFollowingInfo.do")
    @ResponseBody
    public ServiceResponse getBasicFollowingInfo(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.interactionService.getBasicFollowingInfo(request);
    }

    /**
     * 已选学课程
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("listCoursesOfSpecifiedUser.do")
    @ResponseBody
    public ServiceResponse listCoursesOfSpecifiedUser(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.interactionService.listCoursesOfSpecifiedUser(request);
    }

    /**
     * 向当前操作人员显示某名学员可见范围内且学时确认的培训班
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("listTrainsOfSpecifiedUser.do")
    @ResponseBody
    public ServiceResponse listTrainsOfSpecifiedUser(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.interactionService.listTrainsOfSpecifiedUser(request);
    }


}
