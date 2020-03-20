package com.elearning.controller.pub;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.DynamicLogin;
import com.elearning.service.pub.IDynamicLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/dynamicLogin/")
public class DynamicLoginController {

    @Autowired
    private IDynamicLoginService dynamicLoginService;

    @RequestMapping("getDynamicLoginByID.do")
    @ResponseBody
    public ServiceResponse getDynamicLoginByID(Integer id, Model model){
        DynamicLogin dynamicLogin = dynamicLoginService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(dynamicLogin);
    }















}
