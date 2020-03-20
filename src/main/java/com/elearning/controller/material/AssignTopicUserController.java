package com.elearning.controller.material;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.material.AssignTopicUser;
import com.elearning.service.material.IAssignTopicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/assignTopicUser/")
public class AssignTopicUserController {

    @Autowired
    private IAssignTopicUserService assignTopicUserService;

    @RequestMapping("getAssignTopicUserByID.do")
    @ResponseBody
    public ServiceResponse getAssignTopicUserByID(Long id, Model model){
        AssignTopicUser assignTopicUser = assignTopicUserService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(assignTopicUser);
    }















}
