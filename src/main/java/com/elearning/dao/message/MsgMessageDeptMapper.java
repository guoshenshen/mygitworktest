package com.elearning.dao.message;

import com.elearning.pojo.message.MsgMessageDept;

import java.util.List;
import java.util.Map;

public interface MsgMessageDeptMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(MsgMessageDept record);

    int insertSelective(MsgMessageDept record);

    MsgMessageDept selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MsgMessageDept record);

    int updateByPrimaryKey(MsgMessageDept record);

    List<MsgMessageDept> findByExample(Map<String,Object> parmMap);

    List<MsgMessageDept> findByMsgId(Integer msgId);



}