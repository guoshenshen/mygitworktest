package com.elearning.dao.info;

import com.elearning.pojo.info.InfoSendMessage;

public interface InfoSendMessageMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(InfoSendMessage record);

    int insertSelective(InfoSendMessage record);

    InfoSendMessage selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(InfoSendMessage record);

    int updateByPrimaryKeyWithBLOBs(InfoSendMessage record);

    int updateByPrimaryKey(InfoSendMessage record);
}