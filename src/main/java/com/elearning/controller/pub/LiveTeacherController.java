package com.elearning.controller.pub;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.pub.LiveTeacher;
import com.elearning.service.pub.ILiveTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/liveTeacher/")
public class LiveTeacherController {

    @Autowired
    private ILiveTeacherService liveTeacherService;

    @RequestMapping("getLiveTeacherByID.do")
    @ResponseBody
    public ServiceResponse getLiveTeacherByID(Integer id, Model model){
        LiveTeacher liveTeacher = liveTeacherService.selectByPrimaryKey(id);

        return ServiceResponse.createBySuccess(liveTeacher);
    }















}
