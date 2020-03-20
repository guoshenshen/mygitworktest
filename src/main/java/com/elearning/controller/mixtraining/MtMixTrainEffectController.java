package com.elearning.controller.mixtraining;

import com.elearning.common.Constants;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.courseStudy.TrainSummaryWithBLOBs;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.courseStudy.ITrainSummaryService;
import com.elearning.service.mixtraining.IMtMixTrainEffectService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.vo.CourseStudy.TrainSummaryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/mtMixTrainEffect/")
public class MtMixTrainEffectController {

    @Autowired
    private ITrainSummaryService trainSummaryService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IMtMixTrainEffectService mtMixTrainEffectService;

    /**
     * 培训成效查询主页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("forupdate.do")
    public String forupdate(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }
        int train_id = Integer.valueOf(request.getSession().getAttribute("trainId").toString());
        int operatorId = eosoperator.getOperatorId();

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("train_id",train_id);
        parmMap.put("operatorId",operatorId);
        parmMap.put("isTrainEffect",1);

        List<TrainSummaryWithBLOBs> trainSummaryList = this.trainSummaryService.findByExample(parmMap);

        if(trainSummaryList.size()>0){
            TrainSummaryWithBLOBs trainSummary = trainSummaryList.get(0);
            TrainSummaryForm trainSummaryForm = this.trainSummaryService.getTrainSummaryForm(trainSummary);
            request.setAttribute("trainSummaryForm", trainSummaryForm);
            jspName = "mixtraining/mixTrainingEffectforupdate";
        }else{
            jspName = "forward:../mtMixTrainEffect/foradd.do";
        }
        return jspName;
    }

    /**
     * 去新增培训成效页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("foradd.do")
    public String foradd(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }
        int train_id = Integer.valueOf(request.getSession().getAttribute("trainId").toString());

        int operatorId = eosoperator.getOperatorId();
        String operatorName = eosoperator.getOperatorName();

        TrainSummaryForm trainSummaryForm = new TrainSummaryForm();
        trainSummaryForm.setOperatorId(operatorId);
        trainSummaryForm.setOperatorName(operatorName);
        trainSummaryForm.setUserName(operatorName);

        if(this.eosorgTEmployeeService.findById(operatorId) != null){
            int orgid = this.eosorgTEmployeeService.findById(operatorId).getOrgID();
            if(eosorgTOrganizationService.findById(orgid) != null){
                String orgName = eosorgTOrganizationService.findById(orgid).getOrgName();
                trainSummaryForm.setOrgName(orgName);
            }
        }
        trainSummaryForm.setTrainId(train_id);

        request.setAttribute("trainSummaryForm", trainSummaryForm);

        jspName = "mixtraining/mixTrainingEffectforadd";

        return jspName;
    }

    /**
     * 新增培训成效
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("add.do")
    public String add(TrainSummaryForm trainSummaryForm,HttpServletRequest request){
        String jspName = "test/error";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }
        int n = this.mtMixTrainEffectService.add(trainSummaryForm,request);
        if(n > 0){
            jspName = "mixtraining/mixTrainingEffectDetail";
        }
        return jspName;
    }

    /**
     * 培训成效详情页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("detail.do")
    public String detail(HttpServletRequest request){
        String jspName = "test/error";
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }

        int train_id = Integer.valueOf(request.getSession().getAttribute("trainId").toString());
        int operatorId = operator.getOperatorId();

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("train_id",train_id);
        parmMap.put("operatorId",operatorId);
        parmMap.put("isTrainEffect",1);

        List<TrainSummaryWithBLOBs> trainSummaryList = this.trainSummaryService.findByExample(parmMap);
        if(trainSummaryList.size()>0){
            TrainSummaryWithBLOBs trainSummary = trainSummaryList.get(0);
            TrainSummaryForm trainSummaryForm = this.trainSummaryService.getTrainSummaryForm(trainSummary);
            request.setAttribute("trainSummaryForm", trainSummaryForm);
        }
        jspName = "mixtraining/mixTrainingEffectDetail";

        return jspName;
    }

    /**
     * 更改培训成效详情
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("update.do")
    public String update(TrainSummaryForm trainSummaryForm, HttpServletRequest request){
        String jspName = "test/error";
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }
        int n = this.mtMixTrainEffectService.update(trainSummaryForm,request);
        if(n > 0){
            jspName = "mixtraining/mixTrainingEffectDetail";
        }
        return jspName;
    }

    /**
     * 删除培训成效详情
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("delete.do")
    public String delete(TrainSummaryForm trainSummaryForm, HttpServletRequest request){
        String jspName = "test/error";
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }
        int n = this.mtMixTrainEffectService.delete(trainSummaryForm,request);
        if(n > 0){
            jspName = "redirect:../mtMixTrainEffect/foradd.do";
        }
        return jspName;
    }

}
