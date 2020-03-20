package com.elearning.service.message;

import com.elearning.pojo.message.MsgMessageUser;

import java.util.List;
import java.util.Map;

public interface IMsgMessageUserService {

    MsgMessageUser selectByPrimaryKey(Integer ID);

    List<MsgMessageUser> queryMessageUserListYesOperatorName(Map<String,Object> map);

    List<MsgMessageUser> queryMessageUserListNoOperatorName(Map<String,Object> map);

    int insertSelective(MsgMessageUser record);

    List<MsgMessageUser> findByExample(Map<String,Object> parmMap);

    List<MsgMessageUser> findByMsgId(Integer msgId);



}
