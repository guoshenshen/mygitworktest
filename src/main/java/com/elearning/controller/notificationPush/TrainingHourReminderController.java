package com.elearning.controller.notificationPush;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.notificationPush.ITrainingHourReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/trainingHourReminder/")
public class TrainingHourReminderController {

    @Autowired
    private ITrainingHourReminderService trainingHourReminderService;


    /**
     * 获取登录用户学习总时长，剩余需要学习时长
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getTrainingHour.do")
    @ResponseBody
    public ServiceResponse getTrainingHour(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.trainingHourReminderService.getTrainingHour(request);
    }

}
