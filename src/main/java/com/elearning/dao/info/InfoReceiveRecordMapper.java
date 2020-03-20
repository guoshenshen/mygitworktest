package com.elearning.dao.info;

import com.elearning.pojo.info.InfoReceiveRecord;

public interface InfoReceiveRecordMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(InfoReceiveRecord record);

    int insertSelective(InfoReceiveRecord record);

    InfoReceiveRecord selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(InfoReceiveRecord record);

    int updateByPrimaryKey(InfoReceiveRecord record);
}