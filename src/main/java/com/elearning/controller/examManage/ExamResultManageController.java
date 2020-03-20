package com.elearning.controller.examManage;

import com.elearning.common.*;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.examManage.IExamExamInfoService;
import com.elearning.service.examManage.IExamResultService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.vo.examManage.ExamOnlinePaperReadingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/examResultManage/")
public class ExamResultManageController {

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IExamResultService examResultService;

    @Autowired
    private IExamExamInfoService examExamInfoService;

    /**
     *  导航到在线阅卷
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("toOnlinePaperReading.do")
    public String toOnlinePaperReading(HttpServletRequest request){

        String jspName = "index";

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
        String eidString = request.getParameter("eid");
        if(MyUtils.isNull(eidString)){
            eidString = request.getAttribute("eid").toString();
            if(MyUtils.isNull(eidString)){
                request.setAttribute("alertString", "选择要批改的考试id不能为空!");
                if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                    jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
                } else{
                    jspName = "forward:../examManage/toExamManageHome.do";
                }
            }
        }else{
            Integer eid = Integer.parseInt(eidString);
            List<ExamOnlinePaperReadingForm> onlineReadingList = this.examResultService.findExamOnlinePaperReadingByExamId(eid);
            request.setAttribute("onlineReadingList", onlineReadingList);

            request.setAttribute("eid", eid);
            if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                jspName = "examManage/examOnlinePaperReadingForTrain_cast";
            } else{
                jspName = "examManage/examOnlinePaperReading_cast";
            }
        }
        return jspName;
    }

    /**
     *  考试管理 -- 成绩管理 ( 成绩查询结果 )
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("examScores.do")
    public String examScores(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        if(examForTrainFlag == null){
            examForTrainFlag = (String)request.getAttribute("examForTrainFlag");
        }
        Object trainId = null;
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            trainId = request.getSession().getAttribute("trainId");//从seesion中获得当前培训ID号
            if(trainId != null){
                TrainWithBLOBs train = this.onlineTrainingService.findById((Integer)trainId);
                request.setAttribute("train", train);
            }
        }

        String eidString = request.getParameter("eid");
        String type = request.getParameter("type");

        if(request.getAttribute("modifyUsualScore") != null){
            type = (String)request.getAttribute("type");
            eidString = (String)request.getAttribute("eid");
        }
        if(null == eidString || "".equalsIgnoreCase(eidString)) {
            eidString = (String)request.getSession().getAttribute("eid");
            type = (String)request.getSession().getAttribute("type");
        }

        //编号
        String empCode = request.getParameter("empCode");
        if(empCode!=null  && empCode!="" && !"".equals(empCode)){
            request.setAttribute("empCode", empCode);
        }

        //姓名
        String empName = request.getParameter("empName");
        if(empName!=null  && empName!="" && !"".equals(empName)){
            request.setAttribute("empName", empName);
        }

        ExamExamInfo examInfo = this.examExamInfoService.selectByPrimaryKey(Integer.parseInt(eidString));
        double percentAge = examInfo.getProportion();
        if(1.0!=percentAge || 1.00!=percentAge){
            double usualPercentAge = 1-percentAge;
            request.setAttribute("usualPercentAge", Tools.getTwoDigitalData(usualPercentAge));
        }

        request.setAttribute("percentAge", Tools.getTwoDigitalData(percentAge));
        request.setAttribute("eid", eidString);
        request.setAttribute("examInfo", examInfo);

        if(null == type || "".equalsIgnoreCase(type)){
            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                jspName = "examManage/examUsualScoresForTrain_cast";
            } else{
                jspName = "examManage/examUsualScores_cast";
            }
        } else{
            if(null != examInfo) {
                request.setAttribute("examType", examInfo.getExamType());
            }
            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                jspName = "examManage/examUsualScoresForTrain_cast";
            } else{
                jspName = "examManage/examUsualScores_cast";
            }
        }

        return jspName;
    }

    /**
     *  该方法未完成=================未走通方法====================
     * @param startIndex
     * @param count
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("searchExamScoreList.do")
    @ResponseBody
    public ServiceResponse searchExamScoreList(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                          @RequestParam(value = "count",defaultValue = "10")Integer count,
                                          HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        Map<String,Object> queryConditionMap = new HashMap<>();
        queryConditionMap.put("startIndex", startIndex);
        queryConditionMap.put("count", count);

        /*String trainIdStr = request.getSession().getAttribute("trainId").toString();
        Integer trainId = Integer.parseInt(trainIdStr);
        queryConditionMap.put("trainId", trainId);*/

        //编号
        String empCode = request.getParameter("empCode");
        if(empCode!=null  && empCode!="" && !"".equals(empCode)){
            queryConditionMap.put("empCode",empCode);
        }

        //姓名
        String empName = request.getParameter("empName");
        if(empName!=null  && empName!="" && !"".equals(empName)){
            queryConditionMap.put("empName",empName);
        }

        String eidString = request.getParameter("eid");
        if(eidString!=null  && eidString!="" && !"".equals(eidString)){
            queryConditionMap.put("examId",Integer.parseInt(eidString));
        }else{
            eidString = (String)request.getSession().getAttribute("eid");
            queryConditionMap.put("examId",Integer.parseInt(eidString));
        }

        return this.examResultService.searchExamScoreList(queryConditionMap);
    }

    /**
     *  平时成绩导入
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("importExamScore.do")
    public String importExamScore(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        Object trainId = null;
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            trainId = request.getSession().getAttribute("trainId");//从seesion中获得当前培训ID号
            if(trainId != null){
                TrainWithBLOBs train = this.onlineTrainingService.findById((Integer)trainId);
                request.setAttribute("train", train);
            }
        }
        String eid = request.getParameter("eid");
        if(null == eid){
            eid = (String)request.getSession().getAttribute("eid");
        }
        request.setAttribute("eid", eid);
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            jspName = "examManage/importExcelForTrain_cast";
        } else{
            jspName = "examManage/importExcel_cast";
        }

        return jspName;
    }



}
