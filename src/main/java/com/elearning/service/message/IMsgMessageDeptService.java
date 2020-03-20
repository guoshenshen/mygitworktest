package com.elearning.service.message;

import com.elearning.pojo.message.MsgMessageDept;

import java.util.List;
import java.util.Map;

public interface IMsgMessageDeptService {

    MsgMessageDept selectByPrimaryKey(Integer ID);

    int insertSelective(MsgMessageDept record);

    List<MsgMessageDept> findByExm(Map<String,Object> parmMap);

    List<MsgMessageDept> findByMsgId(Integer msgId);





}
