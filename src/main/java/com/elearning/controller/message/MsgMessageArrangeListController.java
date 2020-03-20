package com.elearning.controller.message;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.message.MsgMessageUser;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.Train;
import com.elearning.service.message.IMsgMessageArrangeListService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.vo.message.MsgMessageArrangeUserForm;
import com.elearning.vo.pub.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/msgMessageArrangeList/")
public class MsgMessageArrangeListController {

    @Autowired
    private IMsgMessageArrangeListService msgMessageArrangeListService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @IsCheckUserLogin(check = true)
    @RequestMapping("searchArrangeUser.do")
    public String searchArrangeUser(HttpServletRequest request){
        String jspName = "test/error";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        int trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        Train train=this.onlineTrainingService.findById(trainId);
        request.setAttribute("trainId", trainId);
        request.setAttribute("train", train);


        //添加查询条件
        if(request.getParameter("attendable")!=null && !request.getParameter("attendable").toString().equals("")){
            request.setAttribute("attendable", request.getParameter("attendable").toString());
        }
        if(request.getParameter("title")!= null){
            request.setAttribute("title", request.getParameter("title").toString());
        }
        if(request.getParameter("operatorName")!= null){
            request.setAttribute("operatorName", request.getParameter("operatorName").toString());
        }

        jspName = "message/MsgMessageArrangeDeptUserHome";

        return jspName;
    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("listArrangeUser.do")
    @ResponseBody
    public ServiceResponse listArrangeUser(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                  @RequestParam(value = "count",defaultValue = "10")Integer count,
                                  HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        Map<String,Object> map = new HashMap<>();

        int trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        /*Train train=this.onlineTrainingService.findById(trainId);
        request.setAttribute("trainId", trainId);
        request.setAttribute("train", train);*/

        map.put("startIndex", startIndex);
        map.put("count", count);

        //获得培训计划
        map.put("trainId", trainId);

        //添加查询条件
        if(request.getParameter("attendable")!=null && !request.getParameter("attendable").toString().equals("")){
            //request.setAttribute("attendable", request.getParameter("attendable").toString());
            map.put("attendable",request.getParameter("attendable").toString());
        }
        if(request.getParameter("title")!= null && !request.getParameter("title").toString().equals("")){
            //request.setAttribute("title", request.getParameter("title").toString());
            map.put("title",request.getParameter("title").toString());
        }
        if(request.getParameter("operatorName")!= null && !request.getParameter("operatorName").toString().equals("")){
            //request.setAttribute("operatorName", request.getParameter("operatorName").toString());
            map.put("operatorName",request.getParameter("operatorName").toString());
        }

        return this.msgMessageArrangeListService.listArrangeUser(map,request);
    }


}
