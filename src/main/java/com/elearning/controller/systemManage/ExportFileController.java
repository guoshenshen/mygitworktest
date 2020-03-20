package com.elearning.controller.systemManage;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.systemManage.Log;
import com.elearning.service.systemManage.IExportFileService;
import com.elearning.service.systemManage.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/exportFile/")
public class ExportFileController {

    @Autowired
    private IExportFileService exportFileService;

    @RequestMapping("export.do")
    @ResponseBody
    public ServiceResponse export(HttpServletRequest request){

        return exportFileService.export(request);
    }

}
