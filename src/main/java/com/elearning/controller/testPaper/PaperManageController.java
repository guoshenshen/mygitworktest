package com.elearning.controller.testPaper;

import com.elearning.common.Constants;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.testPaper.IPaperManageService;
import com.elearning.vo.testPaper.TpaPaperStrategyQuesTypeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/paperManage/")
public class PaperManageController {

    @Autowired
    private IPaperManageService paperManageService;

    /**
     *  预览
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("previewTestPaper.do")
    public String previewTestPaper(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        List<TpaPaperStrategyQuesTypeForm> tpaPaperStrategyquestypeFormList = this.paperManageService.previewTestPaper(request);

        if(tpaPaperStrategyquestypeFormList != null){
            request.setAttribute("tpaPaperStrategyquestypeFormList",tpaPaperStrategyquestypeFormList);
        }else{
            request.setAttribute("paperManageErrorMessage","试卷不存在!");
        }

        jspName = "testPaper/testPaperPreviewPaper";

        return jspName;
    }

}
