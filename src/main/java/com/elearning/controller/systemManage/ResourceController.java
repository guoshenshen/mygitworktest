package com.elearning.controller.systemManage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.systemManage.Resource;
import com.elearning.service.systemManage.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/resource/")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @RequestMapping("getResourceByID.do")
    @ResponseBody
    public ServiceResponse getResourceByID(Integer ID, Model model){
        Resource resource = resourceService.selectByPrimaryKey(ID);

        return ServiceResponse.createBySuccess(resource);
    }















}
