package com.elearning.controller.examManage;

import com.elearning.common.Constants;
import com.elearning.common.MyUtils;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.controller.BaseController;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.courseStudy.UserTrain;
import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.examManage.ExamExamUser;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.courseStudy.IUserTrainService;
import com.elearning.service.examManage.IExamExamDeptService;
import com.elearning.service.examManage.IExamExamInfoService;
import com.elearning.service.examManage.IExamExamUserService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.vo.examManage.ExamDeptForm;
import com.elearning.vo.examManage.ExamUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
@RequestMapping("/examUser/")
public class ExamUserController extends BaseController{

    @Autowired
    private IExamExamUserService examExamUserService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IExamExamInfoService examExamInfoService;

    @Autowired
    private IExamExamDeptService examExamDeptService;

    @Autowired
    private IUserTrainService userTrainService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    /**
     *   取消指派
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("removeExamUser.do")
    @ResponseBody
    public ServiceResponse removeExamUser(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.examExamUserService.removeExamUser(request);

    }

    /**
     *   邮件派送
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("insertExamUser.do")
    @ResponseBody
    public ServiceResponse insertExamUser(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.examExamUserService.insertExamUser(request);
    }

    /**
     *   导航到人员安排
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("toExamUserAssign.do")
    public String toExamUserAssign(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        Object trainId = null;
        if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
            //从seesion中获得当前培训ID号
            trainId = request.getSession().getAttribute("trainId");
            if(trainId != null){
                TrainWithBLOBs train = this.onlineTrainingService.findById((Integer)trainId);
                request.setAttribute("train", train);
            }
        }

        String eidString = null;
        if(null != request.getParameter("eid")) {
            eidString = request.getParameter("eid");
            this.setSession(request,"eid",eidString);
        } else{
            eidString = this.getSession(request, "eid").toString();
        }
        if(MyUtils.isNull(eidString)) {
            request.setAttribute("alertString", "导航到人员安排出错，eid 为空!");
            if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
            } else{
                jspName = "forward:../examManage/toExamManageHome.do";
            }
        }else {
            Integer eid = Integer.parseInt(eidString);

            ExamExamInfo examInfo = examExamInfoService.selectByPrimaryKey(eid);
            List<ExamDeptForm> examDeptList = this.examExamDeptService.queryExamDeptByExamId(eid);
            List<ExamUserForm> examUserList = this.examExamUserService.queryExamUserByExamId(eid);

            request.setAttribute("examInfo", examInfo);
            request.setAttribute("examDeptList", examDeptList);
            request.setAttribute("examUserList", examUserList);

            if(1==examInfo.getIsPublish().intValue()){
                request.setAttribute("isPublishFlag", "1");
            }
            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                jspName = "forward:../examUser/insert.do?examForTrainFlag=1";
            } else{
                jspName = "examManage/examUserAssign_cast";
            }
        }

        return jspName;
    }

    /**
     *   导航到创建试卷
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("toPrevious.do")
    public String toPrevious(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String examForTrainFlag = request.getParameter("examForTrainFlag");
        Object trainId = null;
        if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
            trainId = request.getSession().getAttribute("trainId");//从seesion中获得当前培训ID号
            if(trainId!=null){
                TrainWithBLOBs train = this.onlineTrainingService.findById((Integer)trainId);
                request.setAttribute("train", train);
            }
        }

        String sEid = getSession(request, "eid").toString();
        if(MyUtils.isNull(sEid)) {
            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                request.setAttribute("alertString", "eid 为空!");
                jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
            }else{
                request.setAttribute("alertString", "创建试卷出错，eid 为空!");
                jspName = "forward:../examManage/toExamManageHome.do";
            }
        }else{
            Integer eid = Integer.valueOf(sEid);
            ExamExamInfo examInfo = examExamInfoService.selectByPrimaryKey(eid);

            if(1 == examInfo.getExamType()){
                if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                    jspName = "forward:../examInfo/toEditExamInfoById.do?examForTrainFlag=1";
                } else{
                    jspName = "forward:../examInfo/toEditExamInfoById.do";
                }
            }else{
                if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                    jspName = "forward:../examPaper/toExamPaperCreate.do?examForTrainFlag=1";
                } else{
                    jspName = "forward:../examPaper/toExamPaperCreate.do";
                }
            }
        }

        return jspName;
    }

    /**
     *  安排考试给部门和人员
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("insert.do")
    public String insert(HttpServletRequest request){

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

        String eidString = getSession(request, "eid").toString();

        if(MyUtils.isNull(eidString)) {
            if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                request.setAttribute("alertString", "eid不能为空!");
                //forward = mapping.findForward("nullSomePropertyForTrain");
                //
                jspName = "";
            } else{
                request.setAttribute("alertString", "指定考试 部门/人员 出错，eid 为空!");
                //forward = mapping.findForward("nullSomeProperty");
                //
                jspName = "";
            }
        }else {
            if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                List<EosOperator> userList = new ArrayList();
                List<ExamExamUser> examUserListToAdd = new ArrayList<>();
                if(null!=request.getAttribute("alertString") && !"".equals(request.getAttribute("alertString"))){
                    request.setAttribute("alertString",request.getAttribute("alertString"));
                }
                List<UserTrain> userTrainList= this.userTrainService.getListByTrainId((Integer)trainId);
                Iterator<UserTrain> it = userTrainList.iterator();
                while(it.hasNext()){
                    int operatorId = (it.next()).getOperatorID();
                    EosOperator eosoper = this.eosOperatorService.findById(operatorId);
                    userList.add(eosoper);

                    ExamExamUser examUser=new ExamExamUser();
                    examUser.setExam_id(Integer.parseInt(eidString));
                    examUser.setOperatorId(eosoper.getOperatorId());
                    //添加考试人员
                    examUserListToAdd.add(examUser);
                }
                //examUserService.saveExamUserList(examUserListToAdd);
                //updateExamInfoJoinCountAndFinishCountPeople(Integer.parseInt(eidString));
                request.setAttribute("userList", userList);
                //forward = mapping.findForward("examUserDeptInsertSuccessForTrain");
                //
                jspName = "";
            }else{

                Integer eid = Integer.parseInt(eidString);
                //List<EosOperator> userList = examUserService.findAllUserIncludeInDeptList(eid);

                if(null!=request.getAttribute("alertString") && !"".equals(request.getAttribute("alertString"))){
                    request.setAttribute("alertString",request.getAttribute("alertString"));
                }
                //request.setAttribute("userList", userList);

                //forward = mapping.findForward("examUserDeptInsertSuccess");
                //
                jspName = "";
            }
        }

        return jspName;
    }


}
