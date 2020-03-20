package com.elearning.controller.courseStudy;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.courseStudy.TrainSummaryWithBLOBs;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.courseStudy.ITrainSummaryService;
import com.elearning.vo.CourseStudy.TrainSummaryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/trainSummary/")
public class TrainSummaryController {

    @Autowired
    private ITrainSummaryService trainSummaryService;

    /**
     * 培训总结详情
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("detail.do")
    public String detail(HttpServletRequest request){

        int summary_id = Integer.parseInt(request.getParameter("summary_id"));

        TrainSummaryWithBLOBs trainSummary = this.trainSummaryService.selectByPrimaryKey(summary_id);
        TrainSummaryForm trainSummaryForm = this.trainSummaryService.getTrainSummaryForm(trainSummary);
        request.setAttribute("trainSummaryForm", trainSummaryForm);

        return "mixtraining/trainSummarydetail";
    }

    /**
     * 培训班管理--预览--培训总结
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getTrainingSummary.do")
    public String getTrainingSummary(HttpServletRequest request){

        String jspName = "test/error";
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }

        Map<String,Object> resultMap = this.trainSummaryService.getTrainingSummary(request);

        if((resultMap.get("summaryFlag")!=null && !"".equals(resultMap.get("summaryFlag"))) || (!(Boolean) resultMap.get("userJoinTrainFlag"))){
            jspName = "courseStudy/otherTrainSummaryList";
        } else{
            jspName = "courseStudy/trainSummaryList";
        }

        return jspName;
    }

    /**
     *  提交或更新总结
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("saveOrupdateSubmit.do")
    @ResponseBody
    public ServiceResponse saveOrupdateSubmit(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.trainSummaryService.saveOrupdateSubmit(request);
    }

    /**
     * 删除总结
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteSummary.do")
    @ResponseBody
    public ServiceResponse deleteSummary(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.trainSummaryService.deleteSummary(request);
    }

    /**
     * 展示线上班总结页
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("showTrainSummary.do")
    @ResponseBody
    public ServiceResponse showTrainSummary(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.trainSummaryService.showTrainSummary(request);
    }













}
