package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralOnlineExamRecord;

public interface IntegralOnlineExamRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IntegralOnlineExamRecord record);

    int insertSelective(IntegralOnlineExamRecord record);

    IntegralOnlineExamRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralOnlineExamRecord record);

    int updateByPrimaryKey(IntegralOnlineExamRecord record);
}