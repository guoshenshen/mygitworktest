package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralOnlineStudyRecord;

public interface IntegralOnlineStudyRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IntegralOnlineStudyRecord record);

    int insertSelective(IntegralOnlineStudyRecord record);

    IntegralOnlineStudyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralOnlineStudyRecord record);

    int updateByPrimaryKey(IntegralOnlineStudyRecord record);
}