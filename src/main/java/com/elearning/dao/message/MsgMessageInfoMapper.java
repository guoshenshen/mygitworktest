package com.elearning.dao.message;

import com.elearning.pojo.message.MsgMessageInfo;
import com.elearning.pojo.message.MsgMessageInfoWithBLOBs;

import java.util.List;
import java.util.Map;

public interface MsgMessageInfoMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(MsgMessageInfoWithBLOBs record);

    int insertSelective(MsgMessageInfoWithBLOBs record);

    MsgMessageInfoWithBLOBs selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MsgMessageInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MsgMessageInfoWithBLOBs record);

    int updateByPrimaryKey(MsgMessageInfo record);

    List<MsgMessageInfoWithBLOBs> findByTrainId(Integer trainId);

    List<MsgMessageInfoWithBLOBs> queryMessageList(Map<String,Object> map);

    List<MsgMessageInfoWithBLOBs> getListByEditorId(Map<String,Object> map);




}