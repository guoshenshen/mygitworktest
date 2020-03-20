package com.elearning.controller.mixtraining;

import com.elearning.common.Constants;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.Train;
import com.elearning.service.mixtraining.IMtTrainNewsTemplateService;
import com.elearning.vo.mixtraining.MtTrainNewsTemplateForm;
import com.elearning.vo.pub.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/mtTrainNewsTemplate/")
public class MtTrainNewsTemplateController {

    @Autowired
    private IMtTrainNewsTemplateService mtTrainNewsTemplateService;

    @IsCheckUserLogin(check = true)
    @RequestMapping("queryNewsTemplate.do")
    public String queryNewsTemplate(MtTrainNewsTemplateForm mtMixTrainNewsTemplateForm,HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtTrainNewsTemplateService.queryNewsTemplate(mtMixTrainNewsTemplateForm,request);

        jspName = "mixtraining/mixTrainingNewsTemplateHome";

        return jspName;
    }

}
