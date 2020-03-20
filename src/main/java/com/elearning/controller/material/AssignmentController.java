package com.elearning.controller.material;

import com.elearning.common.Constants;
import com.elearning.common.ResourceType;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.material.Assignment;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.service.material.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/assignment/")
public class AssignmentController {

    @Autowired
    private IAssignmentService assignmentService;


    @RequestMapping("getAssignmentByIdReturnList.do")
    @ResponseBody
    public ServiceResponse getAssignmentByIdReturnList(Long id){

        List<Assignment> assignmentList = assignmentService.getAssignmentByIdReturnList(id);

        return ServiceResponse.createBySuccess(assignmentList);
    }

    /**
     * 作业管理主页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("intoAssignmentFrameForTrain.do")
    public String intoAssignmentFrameForTrain(HttpServletRequest request){
        String jspName = "test/error";
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }

        Integer currentTrainId = (Integer)request.getSession().getAttribute("trainId");
        List<Assignment> assignmentList = this.assignmentService.findByResourceId(ResourceType.TRAIN.getTypeCode(),Long.parseLong(currentTrainId+""));
        request.setAttribute("type", ResourceType.TRAIN.getTypeCode());
        request.setAttribute("resourceId", currentTrainId);
        request.setAttribute("assignmentList", assignmentList);

        jspName = "material/assignFrameForTrain";

        return jspName;
    }

    /**
     * 更新/保存--作业
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateAssignInfo.do")
    @ResponseBody
    public ServiceResponse updateAssignInfo(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.assignmentService.updateAssignInfo(request);
    }

    /**
     * 删除--作业
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteAssignment.do")
    @ResponseBody
    public ServiceResponse deleteAssignment(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.assignmentService.deleteAssignment(request);
    }

    /**
     * 显示某作业下的题目列表信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("showTopicsOfAssign.do")
    @ResponseBody
    public ServiceResponse showTopicsOfAssign(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.assignmentService.showTopicsOfAssign(request);
    }

    /**
     * 批量新建/更新某作业中包含的题目
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateTopic.do")
    @ResponseBody
    public ServiceResponse updateTopic(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.assignmentService.updateTopic(request);
    }

    /**
     * 批量新建/更新某作业中包含的题目
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteTopicByBatch.do")
    @ResponseBody
    public ServiceResponse deleteTopicByBatch(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.assignmentService.deleteTopicByBatch(request);
    }

    /**
     * 加载作业派送对象的答题情况
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("loadAssignUsers.do")
    @ResponseBody
    public ServiceResponse loadAssignUsers(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.assignmentService.loadAssignUsers(request);
    }

    /**
     * 冻结指派给用户的作业
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("freezeUserDistribute.do")
    @ResponseBody
    public ServiceResponse freezeUserDistribute(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.assignmentService.freezeUserDistribute(request);
    }

    /**
     * 根据id检索作业
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getAssignInfo.do")
    @ResponseBody
    public ServiceResponse getAssignInfo(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.assignmentService.getAssignInfo(request);
    }

    /**
     * 邮件派送作业
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("distributeAssignByMail.do")
    @ResponseBody
    public ServiceResponse distributeAssignByMail(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.assignmentService.distributeAssignByMail(request);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("showAssignmentListForStudent.do")
    public String showAssignmentListForStudent(HttpServletRequest request){
        String jspName = "test/error";
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }

        this.assignmentService.showAssignmentListForStudent(request);

        jspName = "material/assignmentItemListForUser";
        return jspName;
    }

    /**
     * 学员查看分配给自身的作业
     * （若学生已经提交作业且作业状态处于已阅，则学生只能查看已提交作业内容，不得对作业进行修改重新提交）
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("intoAssignmentForUser.do")
    public String intoAssignmentForUser(HttpServletRequest request){
        String jspName = "test/error";
        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }

        this.assignmentService.intoAssignmentForUser(request);

        jspName = "material/assignTopicForUser";

        return jspName;
    }

    /**
     * 学员提交作业
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("submitAssignment.do")
    @ResponseBody
    public ServiceResponse submitAssignment(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.assignmentService.submitAssignment(request);
    }


}
