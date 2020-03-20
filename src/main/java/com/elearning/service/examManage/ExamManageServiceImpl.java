package com.elearning.service.examManage;

import com.elearning.common.Constants;
import com.elearning.common.MyUtils;
import com.elearning.common.ServiceResponse;
import com.elearning.pojo.examManage.ExamExamInfo;
import com.elearning.pojo.examManage.ExamExamPapers;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("examManageService")
public class ExamManageServiceImpl implements IExamManageService {

    @Autowired
    private IExamExamInfoService examExamInfoService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IExamExamPapersService examExamPapersService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Override
    public ServiceResponse searchExamList(Map<String,Object> queryConditionMap,HttpServletRequest request){

        int rootOrgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        List<Integer> subOrgList = this.eosorgTOrganizationService.getorgidlist(rootOrgId);

        String examType = request.getParameter("examType");
        String examTitle = request.getParameter("examTitleContent");

        PageHelper.startPage((Integer) queryConditionMap.get("startIndex"),(Integer) queryConditionMap.get("count"));

        if((!MyUtils.isNull(examTitle) && !examTitle.equals("")) || (!MyUtils.isNull(examType) && !examType.equals(""))){
            if(examType!=null  && examType!="" && !"".equals(examType)){
                queryConditionMap.put("examType", examType);
            }
            if(examTitle!=null  && examTitle!="" && !"".equals(examTitle)){
                queryConditionMap.put("examTitle", examTitle);
            }
            queryConditionMap.put("subOrgList",subOrgList);

            List<ExamExamInfo> examList = this.examExamInfoService.getListByTrainIdOrExamTitleOrExamType(queryConditionMap);

            PageInfo pageInfo = new PageInfo(examList);
            pageInfo.setList(examList);

            examList = pageInfo.getList();
            for(ExamExamInfo examInfo : examList) {
                //&& 1 == examInfo.getIsPublish().intValue()
                if(!MyUtils.isNull(examInfo.getIsPublish()) && examInfo.getExamType().intValue()== Constants.I_EXAMTYPE_ONLINE.intValue() ) {
                    List<ExamExamPapers> list = this.examExamPapersService.findByExamId(examInfo.getID());
                    if(list.size()==0){
                        continue;
                    }
                    ExamExamPapers examPaper = list.get(0);
                    examInfo.setPaperId(examPaper.getPaperID());
                }
            }
            pageInfo.setList(examList);
            return ServiceResponse.createBySuccess(pageInfo);
        }else{
            queryConditionMap.put("subOrgList",subOrgList);
            List<ExamExamInfo> examList = this.examExamInfoService.listExamInqueryResult(queryConditionMap);

            PageInfo pageInfo = new PageInfo(examList);
            pageInfo.setList(examList);

            examList = pageInfo.getList();

            for(ExamExamInfo examInfo: examList) {
                if(!MyUtils.isNull(examInfo.getIsPublish())) {
                    List<ExamExamPapers> list = this.examExamPapersService.findByExamId(examInfo.getID());
                    if(list.size()==0){
                        continue;
                    }
                    ExamExamPapers examPaper = list.get(0);
                    examInfo.setPaperId(examPaper.getPaperID());
                }
            }
            pageInfo.setList(examList);
            return ServiceResponse.createBySuccess(pageInfo);
        }
    }

    @Override
    public ServiceResponse sendExamNoticetoUser(HttpServletRequest request){

        String[] operatorIdList = request.getParameterValues("operatorId");
        String eidString = request.getParameter("eid");
        Integer eid = Integer.parseInt(eidString);

        ExamExamInfo examInfo = examExamInfoService.selectByPrimaryKey(eid);
        List<Integer> userIdList = new ArrayList<>();
        for(String operatorIdStr : operatorIdList){
            userIdList.add(Integer.valueOf(operatorIdStr));
        }

        try {
            //=================未走通方法====================该方法未走通======
            this.sendEmailNotice(examInfo,request,userIdList);
            return ServiceResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
            String message = "考试邮件通知发送失败";
            return ServiceResponse.createByErrorMessage(message);
        }
    }

    private void sendEmailNotice(ExamExamInfo examInfo, HttpServletRequest request,List examUserList) {
        try{
            //构建MsgMessageInfoForm实例
            /*Locale locale = getLocale(request);
            MessageResources messages = getResources(request);
            MsgMessageInfoForm msgInfo = new MsgMessageInfoForm();
            msgInfo.setTitle(examInfo.getExamTitle());
            msgInfo.setContent("您好！提醒参加考试："+examInfo.getExamTitle()+"，考试时间为："+ Tools.DateMinuteToString(examInfo.getStartTime()) + "~" + Tools.DateMinuteToString(examInfo.getEndTime()));
            msgInfo.setIsEmail(1);

            msgInfo.setValidDate(examInfo.getStartTime().toString());
            msgInfo.setInvalidDate(examInfo.getEndTime().toString());

            eosorgTEmployeeService.sendEmail(examUserList, null, msgInfo);*/
        }catch(Exception ex){
            System.out.println("发送E-mail通知失败 --------------");
            ex.printStackTrace();
        }
    }


}
