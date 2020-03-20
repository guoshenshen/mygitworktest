package com.elearning.controller.pub;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.ClassLive;
import com.elearning.service.pub.IClassLiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/classLive/")
public class ClassLiveController {

    @Autowired
    private IClassLiveService classLiveService;

    @RequestMapping("getClassLiveByID.do")
    @ResponseBody
    public ServiceResponse getClassLiveByID(Long id, Model model){
        ClassLive classLive = classLiveService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(classLive);
    }















}
