package com.elearning.controller.examManage;

import com.elearning.common.Constants;
import com.elearning.common.MyUtils;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.controller.BaseController;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.examManage.ExamExamPapers;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.examManage.IExamExamPapersService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/examPaper/")
public class ExamPaperController extends BaseController {

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IExamExamPapersService examExamPapersService;

    /**
     *  导航到创建试卷
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("toExamPaperCreate.do")
    public String toExamPaperCreate(HttpServletRequest request){

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

        String sEid = this.getSession(request, "eid").toString();
        if(MyUtils.isNull(sEid)) {
            request.setAttribute("alertString", "创建试卷出错，eid 为空!");
            if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
            } else{
                jspName = "forward:../examManage/toExamManageHome.do";
            }
        }else{
            request.setAttribute("eid", sEid);

            Integer iEid = Integer.parseInt(sEid);
            ExamExamPapers examPaper = this.examExamPapersService.findByExamIdReturnExamExamPapers(iEid);
            request.setAttribute("examPaper", examPaper);

            //考试类型
            String searchKey = request.getParameter("searchKey");
            if(searchKey!=null  && searchKey!="" && !"".equals(searchKey)){
                request.setAttribute("searchKey", searchKey);
            }

            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                jspName = "examManage/examPaperCreateForTrain_cast";
            } else{
                jspName = "examManage/examPaperCreate_cast";
            }
        }

        return jspName;
    }

    /**
     * 查询试卷数据
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("searchPaperList.do")
    @ResponseBody
    public ServiceResponse searchPaperList(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                          @RequestParam(value = "count",defaultValue = "10")Integer count,
                                          HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        Map<String,Object> queryConditionMap = new HashMap<>();
        queryConditionMap.put("startIndex", startIndex);
        queryConditionMap.put("count", count);

        String searchKey = request.getParameter("searchKey");
        if(searchKey!=null  && searchKey!="" && !"".equals(searchKey)){
            queryConditionMap.put("paperTitle",searchKey);
        }

        queryConditionMap.put("operatorId",eosoperator.getOperatorId());
        queryConditionMap.put("isValidPaper",1);

        return this.examExamPapersService.searchPaperList(queryConditionMap);
    }

    /**
     *  插入试卷信息
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

        String paperCreateType = request.getParameter("paperCreateType");
        String sEid = getSession(request, "eid").toString();

        if(MyUtils.isNull(paperCreateType) || MyUtils.isNull(sEid)) {
            request.setAttribute("alertString", "创建试卷出错，paperCreateType 或 eid 为空!");
            if(!MyUtils.isNull(examForTrainFlag) && examForTrainFlag.endsWith("1")){
                jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
            } else{
                jspName = "forward:../examManage/toExamManageHome.do";
            }
        } else {
            if(paperCreateType.equals("0")){
                //指定试卷流程
                String sPaperId = request.getParameter("paperId");
                if(MyUtils.isNull(sPaperId)){
                    request.setAttribute("alertString", "创建试卷出错，指定的 paperId 为空!");
                    if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                        jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
                    } else{
                        jspName = "forward:../examManage/toExamManageHome.do";
                    }
                }else{
                    Integer iEid = Integer.parseInt(sEid);
                    Integer iPid = Integer.parseInt(sPaperId);
                    ExamExamPapers examPaper = new ExamExamPapers();
                    if(this.examExamPapersService.findByExamIdReturnExamExamPapers(iEid) != null){
                        examPaper = this.examExamPapersService.findByExamIdReturnExamExamPapers(iEid);
                        examPaper.setPaperID(iPid);
                        this.examExamPapersService.update(examPaper);
                    }else{
                        examPaper.setExamID(iEid);
                        examPaper.setPaperID(iPid);
                        this.examExamPapersService.insert(examPaper);
                    }

                    request.setAttribute("eid", sEid);
                    if(!MyUtils.isNull(examForTrainFlag)&&examForTrainFlag.endsWith("1")){
                        jspName = "forward:../examManage/toExamManageHome.do?examForTrainFlag=1";
                    } else{
                        jspName = "forward:../examUser/toExamUserAssign.do";
                    }
                }
            }else{
                //创建试卷流程
            }
        }

        return jspName;
    }



}
