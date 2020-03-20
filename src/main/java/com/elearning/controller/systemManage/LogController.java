package com.elearning.controller.systemManage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.systemManage.Log;
import com.elearning.service.systemManage.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/log/")
public class LogController {

    @Autowired
    private ILogService logService;

    @RequestMapping("getLogByLogid.do")
    @ResponseBody
    public ServiceResponse getLogByLogid(Integer logid, Model model){
        Log log = logService.selectByPrimaryKey(logid);

        return ServiceResponse.createBySuccess(log);
    }















}
