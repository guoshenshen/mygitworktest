package com.elearning.controller.material;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.material.AssignTopic;
import com.elearning.service.material.IAssignTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/assignTopic/")
public class AssignTopicController {

    @Autowired
    private IAssignTopicService assignTopicService;

    @RequestMapping("getAssignTopicByID.do")
    @ResponseBody
    public ServiceResponse getAssignTopicByID(Long id, Model model){
        AssignTopic assignTopic = assignTopicService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(assignTopic);
    }















}
