package com.elearning.controller.examManage;

import com.elearning.common.Constants;
import com.elearning.common.MyUtils;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.examManage.ExamExamReg;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.examManage.IExamExamInfoService;
import com.elearning.service.examManage.IExamExamRegService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/examReg/")
public class ExamRegController {

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IExamExamInfoService examExamInfoService;

    @Autowired
    private IExamExamRegService examExamRegService;

    /**
     *  导航到报名审批页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("toExamRegAudit.do")
    public String toExamRegAudit(HttpServletRequest request){

        String jspName = "logon";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        Object trainId = null;
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            trainId = request.getSession().getAttribute("trainId");//从seesion中获得当前培训ID号
            if(trainId!=null){
                TrainWithBLOBs train = this.onlineTrainingService.findById((Integer)trainId);
                request.setAttribute("train", train);
            }
        }
        String eidString;
        if(request.getParameter("eid") != null){
            eidString = request.getParameter("eid");
        }else{
            eidString = request.getAttribute("eid").toString();
        }

        if(MyUtils.isNull(eidString)){
            request.setAttribute("alertString", "出错了，要报名审批考试id为空!");
            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
            } else{
                jspName = "forward:../examManage/toExamManageHome.do";
            }
        }else{
            Integer eid = Integer.parseInt(eidString);
            ExamExamInfo examInfo = examExamInfoService.selectByPrimaryKey(eid);

            request.setAttribute("eid",eid);
            request.setAttribute("examInfo", examInfo);

            //Pager pager = examRegService.findExamRegListByExamId(eid, pageForm.getPageSize(), pageForm.getPageNo());
            //List<ExamUserRegForm> examRegList = pager.getResultList();
            //request.setAttribute("pager", pager);
            //request.setAttribute("examRegList", examRegList);

            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                jspName = "examManage/examRegAuditForTrain_cast";
            } else{
                jspName = "examManage/examRegAudit_cast";
            }
        }

        return jspName;
    }

    /**
     * 查询报名审批页面数据
     * @param request
     * @return
     */
    /*@IsCheckUserLogin(check = true)
    @RequestMapping("searchExamRegAuditList.do")
    @ResponseBody
    public ServiceResponse searchExamRegAuditList(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                          @RequestParam(value = "count",defaultValue = "10")Integer count,
                                          HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        Map<String,Object> queryConditionMap = new HashMap<>();
        queryConditionMap.put("startIndex", startIndex);
        queryConditionMap.put("count", count);

        String trainIdStr = request.getSession().getAttribute("trainId").toString();
        Integer trainId = Integer.parseInt(trainIdStr);
        queryConditionMap.put("trainId", trainId);

        return this.examManageService.searchExamList(queryConditionMap,request);
    }*/


    /**
     *   查看申请理由
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("toSeeApplyReason.do")
    public String toSeeApplyReason(HttpServletRequest request){

        String jspName = "";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String examRegIdString = request.getParameter("examRegId");
        if(MyUtils.isNull(examRegIdString)){
            request.setAttribute("alertString", "出错了，要查看的申请理由id为 null!");
            jspName = "forward:../examManage/toExamManageHome.do";
        }else{
            Integer examRegId = Integer.parseInt(examRegIdString);
            ExamExamReg examReg = this.examExamRegService.findByExamRegId(examRegId);

            request.setAttribute("examReg", examReg);
            jspName = "examManage/examApplyReasonDetail_cast";
        }

        return jspName;
    }

    /**
     *   批量审核通过
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("auditExamRegByIds.do")
    public String auditExamRegByIds(HttpServletRequest request){

        String jspName = "";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String examForTrainFlag = request.getParameter("examForTrainFlag");

        this.examExamRegService.auditExamRegByIds(request);

        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            jspName = "redirect:../examReg/toExamRegAudit.do?examForTrainFlag=1";
        } else{
            jspName = "redirect:../examReg/toExamRegAudit.do";
        }
        return jspName;
    }


}
