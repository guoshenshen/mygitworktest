package com.elearning.controller.message;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.message.MsgMessageInfoWithBLOBs;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.message.IMsgMessageInfoService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.vo.BasicUserForm;
import com.elearning.vo.message.MsgMessageInfoForm;
import com.elearning.vo.pub.BasicOrgForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/msgMessageInfo/")
public class MsgMessageInfoController {

    @Autowired
    private IMsgMessageInfoService msgMessageInfoService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;


    @IsCheckUserLogin(check = true)
    @RequestMapping("foradd.do")
    public String foradd(HttpServletRequest request){
        String jspName = "test/error";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        int trainId=Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        TrainWithBLOBs train=this.onlineTrainingService.findById(trainId);
        MsgMessageInfoForm msgMessageInfoForm = new MsgMessageInfoForm();
        msgMessageInfoForm.setTrainId(trainId);
        request.setAttribute("msgMessageInfoForm", msgMessageInfoForm);

        //获取本次培训的通知列表
        List<MsgMessageInfoWithBLOBs> thisTrainMsgInfoList = this.msgMessageInfoService.findByTrainId(trainId);
        List<MsgMessageInfoForm> thisTrainMsgInfoFormList = this.msgMessageInfoService.getMessageFormList(thisTrainMsgInfoList);
        request.setAttribute("thisTrainMsgInfoFormList", thisTrainMsgInfoFormList);

        request.setAttribute("train", train);
        request.setAttribute("trainName", train.getTrainName());
        jspName="message/messageInfoforadd";

        return jspName;
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("getClassifiedReceivers.do")
    @ResponseBody
    public ServiceResponse getClassifiedReceivers(HttpServletRequest request){

        Integer msgId=Integer.parseInt(request.getParameter("msgId"));
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
            Integer belongTenant=eosoperator.getTenantId();
            Map<String,Object> orgCondtion=new HashMap<>();
            Map<String,Object> operatorCondition=new HashMap<>();
            orgCondtion.put("msgId", msgId);
            operatorCondition.put("msgId", msgId);

            List<BasicOrgForm> orgSelect=this.msgMessageInfoService.getSelectedOrgList(orgCondtion);
            operatorCondition.put("role",0);
            List<BasicUserForm> studentIdList=this.msgMessageInfoService.getSpecifiedUserList(operatorCondition, belongTenant);
            operatorCondition.put("role",1);
            List<BasicUserForm> secretaryIdList=this.msgMessageInfoService.getSpecifiedUserList(operatorCondition, belongTenant);
            operatorCondition.put("role",2);
            List<BasicUserForm> directorIdList=this.msgMessageInfoService.getSpecifiedUserList(operatorCondition, belongTenant);

            resultMap.put("orgSelect",orgSelect);
            resultMap.put("studentList",studentIdList);
            resultMap.put("secretaryList",secretaryIdList);
            resultMap.put("directorList",directorIdList);

        } catch (Exception e) {
            ServiceResponse.createByError();
            e.printStackTrace();
        }

        return ServiceResponse.createBySuccess(resultMap);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("forPreviousTrainingNotice.do")
    public String forPreviousTrainingNotice(HttpServletRequest request){
        String jspName = "test/error";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        //添加查询条件
        if(request.getParameter("attendable")!=null && !request.getParameter("attendable").toString().equals("")){
            request.setAttribute("attendable", request.getParameter("attendable").toString());
        }
        if(request.getParameter("title")!= null){
            request.setAttribute("title", request.getParameter("title").toString());
        }
        if(request.getParameter("operatorName")!= null){
            request.setAttribute("operatorName", request.getParameter("operatorName").toString());
        }

        jspName = "message/messageInfoPreviousTrainingNotice";

        return jspName;
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("listOldMessage.do")
    @ResponseBody
    public ServiceResponse listOldMessage(@RequestParam(value = "startIndex",defaultValue = "1")Integer startIndex,
                                          @RequestParam(value = "count",defaultValue = "10")Integer count,
                                          HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        int trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        TrainWithBLOBs train=this.onlineTrainingService.findById(trainId);

        Integer editorId = 0;
        editorId = eosoperator.getOperatorId();

        Map<String,Object> map = new HashMap<>();

        map.put("editorId", editorId);
        map.put("trainId", trainId);

        map.put("startIndex", startIndex);
        map.put("count", count);

        return this.msgMessageInfoService.listOldMessage(map,request);
    }

    /**
     *更改发布，未发布
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("changeStatus.do")
    @ResponseBody
    public ServiceResponse changeStatus(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.msgMessageInfoService.changeStatus(request);
    }

    /**
     *更改推送，未推送
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("changeRecommendMsg.do")
    @ResponseBody
    public ServiceResponse changeRecommendMsg(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.msgMessageInfoService.changeRecommendMsg(request);
    }


    /**
     * 通知查看弹出页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("messagedetail.do")
    public String messagedetail(HttpServletRequest request){
        String jspName = "test/error";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.msgMessageInfoService.messagedetail(request);

        jspName = "message/messageDetail";

        return jspName;
    }

    /**
     * 创建培训通知
     * @param msgMessageInfoForm
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("add.do")
    public String add(MsgMessageInfoForm msgMessageInfoForm,HttpServletRequest request){
        String jspName = "test/error";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        //???现在只写了业务代码,还没有对表单的重复提交做限制
        int n = this.msgMessageInfoService.add(msgMessageInfoForm,request);

        if(n > 0){
            return "forward:../msgMessageInfo/detail.do";
        }else{
            return "redirect:../msgMessageInfo/foradd.do";
        }
    }

    /**
     * 查看通知详细信息
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("detail.do")
    public String detail(HttpServletRequest request){
        String jspName = "test/error";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        Integer trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        Integer msgId = 0;
        if(request.getParameter("id")!=null){
            msgId = Integer.valueOf(request.getParameter("id").toString());
        } else{
            msgId = Integer.valueOf(request.getAttribute("id").toString());
        }
        MsgMessageInfoWithBLOBs msgMessageInfo = this.msgMessageInfoService.selectByPrimaryKey(msgId);
        MsgMessageInfoForm msgMessageInfoForm= this.msgMessageInfoService.getMsgMessageInfoForm(msgMessageInfo);
        request.setAttribute("msgInfo", msgMessageInfoForm);
        Integer sendable = 1; //标识页面是否出现发送通知邮件和网站发布的图标 1：能够出现
        if(msgMessageInfoForm.getTrainId().intValue()!=trainId){
            sendable=0;
        } else if(msgMessageInfoForm.getStatus()!=null && msgMessageInfoForm.getStatus().intValue()==1092){
            sendable=0;
        }
        request.setAttribute("sendable", sendable);

        jspName="message/messageInfoDetail";

        return jspName;
    }

    /**
     * 删除当前某条培训通知
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteAjax.do")
    @ResponseBody
    public ServiceResponse deleteAjax(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.msgMessageInfoService.deleteAjax(request);

    }

    /**
     * 去修改当前某条培训通知
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("forupdate.do")
    public String forupdate(HttpServletRequest request){
        String jspName = "test/error";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        Integer trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        Integer messageId = Integer.parseInt(request.getParameter("id"));
        MsgMessageInfoWithBLOBs msgMessageInfo = this.msgMessageInfoService.selectByPrimaryKey(messageId);
        MsgMessageInfoForm msgMessageInfoForm= this.msgMessageInfoService.getMsgMessageInfoForm(msgMessageInfo);
        request.setAttribute("msgMessageInfoForm", msgMessageInfoForm);

        jspName="message/messageInfoforupdate_cast";

        return jspName;
    }

    /**
     * 修改当前某条培训通知
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("update.do")
    public String update(MsgMessageInfoForm msgMessageInfoForm,HttpServletRequest request){
        String jspName = "test/error";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        //???-------------------未完成的方法，(后面方法中涉及到文件上传)
        this.msgMessageInfoService.update(msgMessageInfoForm,request);

        return "redirect:../msgMessageInfo/detail.do";

    }

    /**
     * 复制当前某条培训通知
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("copyMsgInfo.do")
    public String copyMsgInfo(HttpServletRequest request){
        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        int trainId=Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        Integer oldMsgId = Integer.valueOf(request.getParameter("id").toString());
        MsgMessageInfoWithBLOBs oldMsgInfo = this.msgMessageInfoService.selectByPrimaryKey(oldMsgId);
        request.setAttribute("oldMsgId", oldMsgId);

        MsgMessageInfoForm msgMessageInfoForm = this.msgMessageInfoService.getMsgMessageInfoForm(oldMsgInfo);
        String newMsgMessageTitle =  "(复制)"+ msgMessageInfoForm.getTitle();
        msgMessageInfoForm.setTitle(newMsgMessageTitle);
        msgMessageInfoForm.setTrainId(trainId);
        request.setAttribute("msgMessageInfoForm", msgMessageInfoForm);

        //获取本次培训的通知列表
        List<MsgMessageInfoWithBLOBs> thisTrainMsgInfoList = this.msgMessageInfoService.findByTrainId(trainId);
        List<MsgMessageInfoForm> thisTrainMsgInfoFormList = this.msgMessageInfoService.getMessageFormList(thisTrainMsgInfoList);
        request.setAttribute("thisTrainMsgInfoFormList", thisTrainMsgInfoFormList);

        jspName = "message/messageInfoforadd";

        return jspName;

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("sendMessageByType.do")
    public String sendMessageByType(HttpServletRequest request){
        String jspName = "test/error";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.msgMessageInfoService.sendMessageByType(request);

        jspName = "redirect:../msgMessageInfo/detail.do";

        return jspName;
    }



}
