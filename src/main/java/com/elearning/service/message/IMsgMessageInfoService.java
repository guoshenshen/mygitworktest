package com.elearning.service.message;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.message.MsgMessageInfoWithBLOBs;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.vo.BasicUserForm;
import com.elearning.vo.message.MsgMessageInfoForm;
import com.elearning.vo.pub.BasicOrgForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IMsgMessageInfoService {

    MsgMessageInfoWithBLOBs selectByPrimaryKey(Integer ID);

    void updateEnrollTime(TrainWithBLOBs trainToUpdate);

    List<MsgMessageInfoWithBLOBs> findByTrainId(Integer trainId);

    List<MsgMessageInfoForm> getMessageFormList(List<MsgMessageInfoWithBLOBs> messageList);

    List<BasicOrgForm> getSelectedOrgList(Map<String,Object> condition);

    List<BasicUserForm> getSpecifiedUserList(Map<String,Object> condition, Integer tenantId);

    ServiceResponse listOldMessage(Map<String,Object> map, HttpServletRequest request);

    ServiceResponse changeStatus(HttpServletRequest request);

    ServiceResponse changeRecommendMsg(HttpServletRequest request);

    int add(MsgMessageInfoForm msgMessageInfoForm,HttpServletRequest request);

    MsgMessageInfoForm getMsgMessageInfoForm(MsgMessageInfoWithBLOBs msgMessageInfo);

    ServiceResponse deleteAjax(HttpServletRequest request);

    void update(MsgMessageInfoForm msgMessageInfoForm,HttpServletRequest request);

    void sendMessageByType(HttpServletRequest request);

    void messagedetail(HttpServletRequest request);

    int deleteByPrimaryKey(Integer ID);


}
