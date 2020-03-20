package com.elearning.dao.message;

import com.elearning.pojo.message.MsgMessageAttachFile;

public interface MsgMessageAttachFileMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(MsgMessageAttachFile record);

    int insertSelective(MsgMessageAttachFile record);

    MsgMessageAttachFile selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(MsgMessageAttachFile record);

    int updateByPrimaryKey(MsgMessageAttachFile record);
}