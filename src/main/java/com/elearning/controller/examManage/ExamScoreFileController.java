package com.elearning.controller.examManage;

import com.elearning.common.MyUtils;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/examScoreFile/")
public class ExamScoreFileController {

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    /**
     *   导出成绩
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("exportExcel.do")
    public String exportExcel(HttpServletRequest request){
        String jspName = "";

        //EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        Object trainId = null;
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            trainId = request.getSession().getAttribute("trainId");//从seesion中获得当前培训ID号
            if(trainId != null){
                TrainWithBLOBs train = this.onlineTrainingService.findById((Integer)trainId);
                request.setAttribute("train", train);
            }
        }
        String eidString = request.getParameter("eid");

        request.setAttribute("eid", eidString);
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            jspName = "examManage/exportExcelForTrain_cast";
        } else{
            jspName = "examManage/exportExcel_cast";
        }

        return jspName;

    }



}
