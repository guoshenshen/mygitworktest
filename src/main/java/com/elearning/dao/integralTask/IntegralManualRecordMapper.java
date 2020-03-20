package com.elearning.dao.integralTask;

import com.elearning.pojo.integralTask.IntegralManualRecord;

public interface IntegralManualRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IntegralManualRecord record);

    int insertSelective(IntegralManualRecord record);

    IntegralManualRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralManualRecord record);

    int updateByPrimaryKey(IntegralManualRecord record);
}