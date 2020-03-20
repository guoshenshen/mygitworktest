package com.elearning.controller.systemManage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.systemManage.UserRole;
import com.elearning.service.systemManage.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/userRole/")
public class UserRoleController {

    @Autowired
    private IUserRoleService userRoleService;

    @RequestMapping("getUserRoleByID.do")
    @ResponseBody
    public ServiceResponse getUserRoleByID(Integer ID, Model model){
        UserRole userRole = userRoleService.selectByPrimaryKey(ID);

        return ServiceResponse.createBySuccess(userRole);
    }















}
