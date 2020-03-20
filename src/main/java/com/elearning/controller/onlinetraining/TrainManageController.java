package com.elearning.controller.onlinetraining;

import com.elearning.common.Constants;
import com.elearning.common.ResourceType;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.material.Assignment;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.trainNeeds.TnNeedsQuestionnAire;
import com.elearning.service.material.IAssignUserService;
import com.elearning.service.material.IAssignmentService;
import com.elearning.service.mixtraining.IAddrBookService;
import com.elearning.service.trainNeeds.ITnAssignUserService;
import com.elearning.service.trainNeeds.ITnNeedsQuestionnAireService;
import com.elearning.vo.mixtraining.MtMixTrainUserArrangeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/trainManage/")
public class TrainManageController {

    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private ITnNeedsQuestionnAireService tnNeedsQuestionnAireService;

    @Autowired
    private IAssignUserService assignUserService;

    @Autowired
    private ITnAssignUserService tnAssignUserService;

    @IsCheckUserLogin(check = true)
    @RequestMapping("freezeUserRecordUnderTrain.do")
    @ResponseBody
    public ServiceResponse freezeUserRecordUnderTrain(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        Long trainId = Long.parseLong(request.getParameter("trainId"));
        ResourceType trainType=ResourceType.TRAIN;
        try {
            //查询该培训项目包含的作业
            List<Assignment> assignmentList=this.assignmentService.findByResourceId(trainType.getTypeCode(), trainId);

            //查询该培训项目包含的调查问卷
            List<TnNeedsQuestionnAire> surveys=this.tnNeedsQuestionnAireService.findByTrainId(Integer.valueOf(trainId+""));
            String[] operatorIdStrList=request.getParameterValues("operatorIdList");
            List<Integer> operatorIdList=new ArrayList<Integer>();
            for(String operatorIdStr:operatorIdStrList){
                operatorIdList.add(Integer.parseInt(operatorIdStr));
            }
            for(Assignment assign:assignmentList){
                this.assignUserService.freezeAssignUser(assign.getId(), operatorIdList);
            }
            for(TnNeedsQuestionnAire survey:surveys){
                this.tnAssignUserService.freezeAssignUser(survey.getID(), operatorIdList);
            }
            ServiceResponse.createBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
            ServiceResponse.createByError();
        }

        return  ServiceResponse.createBySuccess();

    }



}
