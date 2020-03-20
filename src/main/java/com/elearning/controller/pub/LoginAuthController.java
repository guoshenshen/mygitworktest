package com.elearning.controller.pub;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.LoginAuth;
import com.elearning.service.pub.ILoginAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/loginAuth/")
public class LoginAuthController {

    @Autowired
    private ILoginAuthService loginAuthService;

    /**
     * 根据ID查询某一条信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("getLoginAuthByID.do")
    @ResponseBody
    public ServiceResponse getLoginAuthByID(Integer id, Model model){

        LoginAuth loginAuth = loginAuthService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(loginAuth);
    }















}
