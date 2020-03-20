package com.elearning.controller.examManage;

import com.elearning.common.Constants;
import com.elearning.common.MyUtils;
import com.elearning.common.Tools;
import com.elearning.controller.BaseController;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.examManage.IExamExamInfoService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.vo.examManage.ExamInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;


@Controller
@RequestMapping("/examInfo/")
public class ExamExamInfoController extends BaseController {

    @Autowired
    private IExamExamInfoService examExamInfoService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    //@Autowired
    //private IExamexam onlineTrainingService;

    /**
     *  插入考试信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("examInsert.do")
    public String examInsert(ExamInfoForm examInfoForm ,HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        Object trainId = null;
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            trainId = request.getSession().getAttribute("trainId");//从seesion中获得当前培训ID号
            if(trainId != null){
                TrainWithBLOBs train = this.onlineTrainingService.findById((Integer)trainId);
                request.setAttribute("train", train);
            }
        }
        try{
            ExamExamInfo examInfo = new ExamExamInfo();

            examInfo.setExamTitle(examInfoForm.getExamTitle().trim());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            examInfo.setStartTime(df.parse(examInfoForm.getStartTime()));
            examInfo.setEndTime(df.parse(examInfoForm.getEndTime()));
            examInfo.setTotalTime(examInfoForm.getTotalTime());
            examInfo.setExamType(examInfoForm.getExamType());
            examInfo.setExamStyle(examInfoForm.getExamStyle());
            examInfo.setProportion(examInfoForm.getProportion());
            examInfo.setIsNeedApprove(examInfoForm.getIsNeedApprove());
            examInfo.setIsApply(examInfoForm.getIsApply());
            examInfo.setExamDescription(examInfoForm.getExamDescription());
            examInfo.setOrgId(Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString()));
            examInfo.setIfDisplayAnswer(examInfoForm.getIfDisplayAnswer());
            examInfo.setIfRepeatExam(examInfoForm.getIfRepeatExam());
            examInfo.setIsPublish(0);
            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                examInfo.setTrainId((Integer)trainId);
            }
            examInfo.setTeacher_id(eosoperator.getOperatorId());

            this.examExamInfoService.insertExamInfo(examInfo);
            Integer eidInteger = examInfo.getID();
            this.setSession(request, "eid", eidInteger);

            if(1 == examInfoForm.getExamType()){
                jspName = "redirect:../examUser/toExamUserAssign.do";
            }else{
                if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                    jspName = "redirect:../examPaper/toExamPaperCreate.do?examForTrainFlag=1";
                } else{
                    jspName = "forward:../examPaper/toExamPaperCreate.do";
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("alertString", "日期格式化出错，请联系管理员");
            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
            } else{
                jspName = "forward:../examManage/toExamManageHome.do";
            }
        }

        return jspName;
    }

    /**
     *  跳转到考试信息修改
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("toEditExamInfoById.do")
    public String toEditExamInfoById(HttpServletRequest request){

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
        if(eidString == null || "".equals(eidString)){
            if(getSession(request, "eid") == null){
                request.setAttribute("alertString", "出错了，要修改的考试id为空，请刷新页面后选择!");
                if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                    jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
                } else{
                    jspName = "forward:../examManage/toExamManageHome.do";
                }
            }else{
                Integer eid = Integer.parseInt(getSession(request, "eid").toString());
                ExamExamInfo examInfo = this.examExamInfoService.selectByPrimaryKey(eid);
                request.setAttribute("examInfo", examInfo);
                if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                    jspName = "examManage/examCreateForTrain_cast";
                } else{
                    jspName = "examManage/examCreate_cast";
                }
            }
        }else{
            Integer eid = Integer.valueOf(eidString);
            ExamExamInfo examInfo = this.examExamInfoService.selectByPrimaryKey(eid);
            if(!MyUtils.isNull(examInfo) && 1 == examInfo.getIsPublish()) {
                jspName = "forward:../examUser/toExamUserAssign.do";
            }else{
                request.setAttribute("examInfo", examInfo);
                setSession(request, "eid", eid);
                if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                    jspName = "examManage/examCreateForTrain_cast";
                } else{
                    jspName = "examManage/examCreate_cast";
                }
            }
        }

        return jspName;
    }

    /**
     *  修改考试信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("examUpdate.do")
    public String examUpdate(ExamInfoForm examInfoForm , HttpServletRequest request){

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
        if(eidString == null || "".equals(eidString)){
            if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                request.setAttribute("alertString", "出错了，要修改的考试id为空，请刷新页面后选择!");
                jspName = "redirect:../examManage/toExamManageHome.do?examForTrainFlag=1";
            }else{
                request.setAttribute("alertString", "出错了，要修改的考试id为空，请刷新页面后选择!");
                jspName = "redirect:../examManage/toExamManageHome.do";
            }
        }else{
            try{
                Integer eid = Integer.valueOf(eidString);
                ExamExamInfo examInfo = this.examExamInfoService.selectByPrimaryKey(eid);

                examInfo.setExamTitle(examInfoForm.getExamTitle().trim());

                examInfo.setStartTime(Tools.stringToSeconds(examInfoForm.getStartTime()));
                examInfo.setEndTime(Tools.stringToSeconds(examInfoForm.getEndTime()));
                examInfo.setTotalTime(examInfoForm.getTotalTime());
                examInfo.setExamType(examInfoForm.getExamType());
                examInfo.setExamStyle(examInfoForm.getExamStyle());
                examInfo.setProportion(examInfoForm.getProportion());
                examInfo.setIsNeedApprove(examInfoForm.getIsNeedApprove());
                examInfo.setIsApply(examInfoForm.getIsApply());
                examInfo.setExamDescription(examInfoForm.getExamDescription());
                examInfo.setIfDisplayAnswer(examInfoForm.getIfDisplayAnswer());
                examInfo.setIfRepeatExam(examInfoForm.getIfRepeatExam());

                this.examExamInfoService.updateExamInfo(examInfo);

                request.setAttribute("eid", examInfo.getID());

                if(1==examInfoForm.getExamType()){
                    if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                        jspName = "redirect:../examUser/toExamUserAssign.do?examForTrainFlag=1";
                    } else{
                        jspName = "redirect:../examUser/toExamUserAssign.do";
                    }
                }else{
                    if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                        jspName = "redirect:../examPaper/toExamPaperCreate.do?examForTrainFlag=1";
                    } else{
                        jspName = "redirect:../examPaper/toExamPaperCreate.do";
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("alertString", "修改考试信息出错，请联系管理员");
                jspName = "redirect:../examManage/toExamManageHome.do";
            }
        }

        return jspName;
    }

    /**
     *  考试发布
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("publishExam.do")
    public String publishExam(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }
        String examForTrainFlag = request.getParameter("examForTrainFlag");

        this.examExamInfoService.publishExam(request);

        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            jspName = "redirect:../examManage/toExamManageHome.do?examForTrainFlag=1";
        } else{
            jspName = "redirect:../examManage/toExamManageHome.do";
        }

        return jspName;
    }


}
