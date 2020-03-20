package com.elearning.controller.mixtraining;

import com.elearning.common.Constants;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.mixtraining.IMtMixTrainSectionFileService;
import com.elearning.service.mixtraining.IMtTrainNewsTemplateService;
import com.elearning.vo.mixtraining.MtTrainNewsTemplateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/mtMixTrainSectionFile/")
public class MtMixTrainSectionFileController {

    @Autowired
    private IMtMixTrainSectionFileService mtMixTrainSectionFileService;

    @IsCheckUserLogin(check = true)
    @RequestMapping("forupload.do")
    public String forupload(MtTrainNewsTemplateForm mtMixTrainNewsTemplateForm,HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        jspName = "mixtraining/mixTrainingUserInfoUpload";

        return jspName;
    }

}
