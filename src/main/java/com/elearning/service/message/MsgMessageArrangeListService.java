package com.elearning.service.message;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.common.Tools;
import com.elearning.dao.message.MsgMessageInfoMapper;
import com.elearning.pojo.message.MsgMessageUser;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.pub.Train;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.util.DateTimeUtil;
import com.elearning.vo.message.MsgMessageArrangeUserForm;
import com.elearning.vo.onlinetraining.TrainForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("msgMessageArrangeListService")
public class MsgMessageArrangeListService implements IMsgMessageArrangeListService{

    @Autowired
    private IMsgMessageUserService msgMessageUserService;

    @Autowired
    private IMsgMessageInfoService msgMessageInfoService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse listArrangeUser(Map<String,Object> map,HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        PageHelper.startPage((Integer) map.get("startIndex"),(Integer) map.get("count"));

        //根据查询条件进行查询
        List<MsgMessageUser> msgMessageUserList;
        if(map.get("operatorName")!=null){
            msgMessageUserList = this.msgMessageUserService.queryMessageUserListYesOperatorName(map);
        }else{
            msgMessageUserList = this.msgMessageUserService.queryMessageUserListNoOperatorName(map);
        }

        PageInfo pageInfo = new PageInfo(msgMessageUserList);
        pageInfo.setList(msgMessageUserList);
        msgMessageUserList = pageInfo.getList();

        List<MsgMessageArrangeUserForm> msgMessageUserFormList = new ArrayList<>();

        for(MsgMessageUser msgMessageUser : msgMessageUserList){
            MsgMessageArrangeUserForm msgMessageArrangeUserForm;
            msgMessageArrangeUserForm = this.getMsgMessageArrangeUserForm(msgMessageUser,operator.getTenantId());
            msgMessageUserFormList.add(msgMessageArrangeUserForm);
        }

        request.setAttribute("msgMessageUserFormList", msgMessageUserFormList);

        pageInfo.setList(msgMessageUserFormList);
        return ServiceResponse.createBySuccess(pageInfo);
    }

    public MsgMessageArrangeUserForm getMsgMessageArrangeUserForm(MsgMessageUser msgMessageUser, Integer operatorTenantId){
        MsgMessageArrangeUserForm result=this.getMsgMessageArrangeUserForm(msgMessageUser);
        Integer operatorId=result.getUserId();
        EosorgTEmployee employee=this.eosorgTEmployeeService.findById(operatorId);
        EosorgTOrganization org=this.eosorgTOrganizationService.findById(employee.getOrgID());
        String parentOrgName="";
        String orgName="";
        if(operatorTenantId.equals(1)){
            EosorgTOrganization secondaryOrg=this.eosorgTOrganizationService.findById(this.eosorgTOrganizationService.findOrgId(employee.getOrgID()));
            parentOrgName=secondaryOrg.getOrgName();
        } else{
            parentOrgName=this.eosorgTOrganizationService.getTenantOrgName(org);
        }

        orgName=this.eosorgTOrganizationService.getStandardOrgName(org, operatorTenantId);

        result.setOrgName(orgName);
        result.setParentOrgName(parentOrgName);
        return result;
    }

    public MsgMessageArrangeUserForm getMsgMessageArrangeUserForm(MsgMessageUser msgMessageUser){
        MsgMessageArrangeUserForm msgMessageArrangeUserForm = new MsgMessageArrangeUserForm();
        msgMessageArrangeUserForm.setId(msgMessageUser.getID());
        msgMessageArrangeUserForm.setMsgId(msgMessageUser.getMsgID());
        if(this.msgMessageInfoService.selectByPrimaryKey(msgMessageUser.getMsgID())!=null){
            msgMessageArrangeUserForm.setTitle(this.msgMessageInfoService.selectByPrimaryKey(msgMessageUser.getMsgID()).getTitle());
        }
        if(msgMessageUser.getAttendable()!=null){
            msgMessageArrangeUserForm.setAttendable(msgMessageUser.getAttendable());
        }
        if(msgMessageUser.getComment()!=null){
            msgMessageArrangeUserForm.setComment(msgMessageUser.getComment());
        }
        if(msgMessageUser.getUserID()!=null){
            if(this.eosOperatorService.findById(msgMessageUser.getUserID())!=null){
                msgMessageArrangeUserForm.setUserId(msgMessageUser.getUserID());
                msgMessageArrangeUserForm.setOperatorName(this.eosOperatorService.findById(msgMessageUser.getUserID()).getOperatorName());
            }
        }
        String sendbackTime = "----";
        if(msgMessageUser.getSendbackTime()!=null){
            sendbackTime = DateTimeUtil.dateToStr(msgMessageUser.getSendbackTime(),"yyyy-MM-dd HH:mm:ss");
        }
        msgMessageArrangeUserForm.setSendbackTime(sendbackTime);
        return msgMessageArrangeUserForm;
    }






}
