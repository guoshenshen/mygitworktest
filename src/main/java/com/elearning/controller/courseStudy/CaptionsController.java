package com.elearning.controller.courseStudy;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.courseStudy.Captions;
import com.elearning.service.courseStudy.ICaptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/captions/")
public class CaptionsController {

    @Autowired
    private ICaptionsService captionsService;

    @RequestMapping("getCaptionsByCaptionsIdReturnList.do")
    @ResponseBody
    public ServiceResponse getCaptionsByCaptionsIdReturnList(Long captionsId, Model model){
        List<Captions> captionsList = captionsService.getCaptionsByCaptionsIdReturnList(captionsId);

        return ServiceResponse.createBySuccess(captionsList);
    }















}
