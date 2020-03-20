package com.elearning.dao.info;

import com.elearning.pojo.info.InfoReceiveMessage;

public interface InfoReceiveMessageMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(InfoReceiveMessage record);

    int insertSelective(InfoReceiveMessage record);

    InfoReceiveMessage selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(InfoReceiveMessage record);

    int updateByPrimaryKeyWithBLOBs(InfoReceiveMessage record);

    int updateByPrimaryKey(InfoReceiveMessage record);
}