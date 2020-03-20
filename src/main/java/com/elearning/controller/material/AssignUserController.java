package com.elearning.controller.material;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.material.AssignUser;
import com.elearning.service.material.IAssignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/assignUser/")
public class AssignUserController {

    @Autowired
    private IAssignUserService assignUserService;

    @RequestMapping("getAssignUserByID.do")
    @ResponseBody
    public ServiceResponse getAssignUserByID(Long id, Model model){
        AssignUser assignUser = assignUserService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(assignUser);
    }















}
