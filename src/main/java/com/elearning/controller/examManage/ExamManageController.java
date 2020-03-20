package com.elearning.controller.examManage;

import com.elearning.common.Constants;
import com.elearning.common.MyUtils;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.courseStudy.UserTrain;
import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.courseStudy.IUserTrainService;
import com.elearning.service.examManage.IExamExamInfoService;
import com.elearning.service.examManage.IExamManageService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
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
@RequestMapping("/examManage/")
public class ExamManageController {

    @Autowired
    private IExamManageService examManageService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IUserTrainService userTrainService;

    @Autowired
    private IExamExamInfoService examExamInfoService;

    /**
     *  去查询考试结果页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("toExamManageHome.do")
    public String toExamManageHome(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        //考试类型
        String examType = request.getParameter("examType");
        if(examType!=null  && examType!="" && !"".equals(examType)){
            request.setAttribute("examType", examType);
        }

        //考试名称
        String examTitleContent = request.getParameter("examTitleContent");
        if(examTitleContent!=null  && examTitleContent!="" && !"".equals(examTitleContent)){
            request.setAttribute("examTitleContent", examTitleContent);
        }

        String examForTrainFlag = request.getParameter("examForTrainFlag");

        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            Integer trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());//从seesion中获得当前培训ID号
            if(trainId != null){
                TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
                List<UserTrain> userTrainList= this.userTrainService.getListByTrainId(trainId);
                int joinCount = userTrainList.size();
                request.setAttribute("train", train);
                request.setAttribute("joinCount",joinCount);
            }

            jspName = "examManage/examManageHomeForTrain_cast";
        } else{
            jspName = "examManage/examManageHome_cast";
        }

        return jspName;
    }

    /**
     * 查询考试结果数据
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("searchExamList.do")
    @ResponseBody
    public ServiceResponse searchExamList(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
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
    }

    /**
     *  管理员查看考试信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("viewExamInfoAdmin.do")
    public String viewExamInfoAdmin(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        Object trainId = request.getSession().getAttribute("trainId");//从seesion中获得当前培训ID号
        if(trainId != null){
            trainId = request.getSession().getAttribute("trainId");
            TrainWithBLOBs train = this.onlineTrainingService.findById(Integer.parseInt(trainId.toString()));
            request.setAttribute("train", train);
        }

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        String eidString = request.getParameter("eid");

        if(eidString == null || "".equals(eidString)){
            request.setAttribute("alertString", "出错了，要查看的考试id为空，请刷新页面后选择!");
            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
            } else{
                jspName = "forward:../examManage/toExamManageHome.do";
            }
        }else{
            Integer eid = Integer.valueOf(eidString);
            ExamExamInfo examInfo = this.examExamInfoService.selectByPrimaryKey(eid);
            request.setAttribute("exam", examInfo);
            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                jspName = "examManage/examViewExamInfoAdminForTrain_cast";
            } else{
                jspName = "examManage/examViewExamInfoAdmin_cast";
            }
        }

        return jspName;
    }

    /**
     *  跳转到新建考试
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("toCreateExam.do")
    public String toCreateExam(HttpServletRequest request){

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
        ExamExamInfo examInfo = new ExamExamInfo();
        examInfo.setExamStyle(1);
        examInfo.setExamType(0);
        examInfo.setIsApply(0);
        examInfo.setIsNeedApprove(0);
        examInfo.setIfDisplayAnswer(0);
        examInfo.setIfRepeatExam(0);

        request.setAttribute("examInfo", examInfo);
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            jspName = "examManage/examCreateForTrain_cast";
        } else{
            jspName = "examManage/examCreate_cast";
        }
        return jspName;
    }

    /**
     *  批量删除指定id考试信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("delExamInfoByIds.do")
    public String delExamInfoByIds(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        Object trainId = null;
        if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
            trainId = request.getSession().getAttribute("trainId");//从seesion中获得当前培训ID号
            if(trainId != null){
                TrainWithBLOBs train = this.onlineTrainingService.findById((Integer)trainId);
                request.setAttribute("train", train);
            }
        }
        String[] eids = request.getParameterValues("selectbox");

        if(!MyUtils.isNull(eids)) {
            for(String eidString: eids){
                this.examExamInfoService.delExamInfoById(Integer.valueOf(eidString));
            }
        }else{
            request.setAttribute("alertString", "请选择要删除的考试!");
        }
        if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
            jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
        } else{
            jspName = "forward:../examManage/toExamManageHome.do";
        }

        return jspName;
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("sendExamNoticetoUser.do")
    @ResponseBody
    public ServiceResponse sendExamNoticetoUser(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.examManageService.sendExamNoticetoUser(request);
    }


}
