package com.elearning.dao.message;

import com.elearning.pojo.message.MsgMessageUser;

import java.util.List;
import java.util.Map;

public interface MsgMessageUserMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(MsgMessageUser record);

    int insertSelective(MsgMessageUser record);

    MsgMessageUser selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MsgMessageUser record);

    int updateByPrimaryKey(MsgMessageUser record);

    List<MsgMessageUser> queryMessageUserListYesOperatorName(Map<String,Object> map);

    List<MsgMessageUser> queryMessageUserListNoOperatorName(Map<String,Object> map);

    List<MsgMessageUser> findByExample(Map<String,Object> parmMap);

    List<MsgMessageUser> findByMsgId(Integer msgId);


}